package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dao.BukuDao;
import model.Buku;
import util.DatabaseHelper;

public class BukuDaoImpl implements BukuDao {

    @Override
    public List<Buku> getAll() throws SQLException {
        String sql = "SELECT buku_id, judul, penulis, harga, stok FROM buku ORDER BY buku_id";
        List<Buku> list = new ArrayList<>();
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Buku b = mapRow(rs);
                list.add(b);
            }
        }
        return list;
    }

    @Override
    public Optional<Buku> findById(int id) throws SQLException {
        String sql = "SELECT buku_id, judul, penulis, harga, stok FROM buku WHERE buku_id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Buku> searchByTitleOrAuthor(String q) throws SQLException {
        String sql = "SELECT buku_id, judul, penulis, harga, stok FROM buku WHERE judul LIKE ? OR penulis LIKE ? ORDER BY buku_id";
        List<Buku> list = new ArrayList<>();
        String pattern = "%" + q + "%";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }

    @Override
    public int insert(Buku buku) throws SQLException {
        String sql = "INSERT INTO buku (judul, penulis, harga, stok) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, buku.getJudul());
            ps.setString(2, buku.getPenulis());
            ps.setBigDecimal(3, buku.getHarga());
            ps.setInt(4, buku.getStok());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    buku.setBukuId(id);
                    return id;
                }
            }
        }
        return -1;
    }

    @Override
    public void update(Buku buku) throws SQLException {
        String sql = "UPDATE buku SET judul = ?, penulis = ?, harga = ?, stok = ? WHERE buku_id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, buku.getJudul());
            ps.setString(2, buku.getPenulis());
            ps.setBigDecimal(3, buku.getHarga());
            ps.setInt(4, buku.getStok());
            ps.setInt(5, buku.getBukuId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM buku WHERE buku_id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Buku mapRow(ResultSet rs) throws SQLException {
        Buku b = new Buku();
        b.setBukuId(rs.getInt("buku_id"));
        b.setJudul(rs.getString("judul"));
        b.setPenulis(rs.getString("penulis"));
        b.setHarga(rs.getBigDecimal("harga"));
        b.setStok(rs.getInt("stok"));
        return b;
    }
}
