package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dao.PelangganDao;
import model.Pelanggan;
import util.DatabaseHelper;

public class PelangganDaoImpl implements PelangganDao {

    @Override
    public List<Pelanggan> getAll() throws SQLException {
        String sql = "SELECT pelanggan_id, nama, email, telepon FROM pelanggan ORDER BY pelanggan_id";
        List<Pelanggan> list = new ArrayList<>();
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Pelanggan p = mapRow(rs);
                list.add(p);
            }
        }
        return list;
    }

    @Override
    public Optional<Pelanggan> findById(int id) throws SQLException {
        String sql = "SELECT pelanggan_id, nama, email, telepon FROM pelanggan WHERE pelanggan_id = ?";
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
    public int insert(Pelanggan pelanggan) throws SQLException {
        String sql = "INSERT INTO pelanggan (nama, email, telepon) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, pelanggan.getNama());
            ps.setString(2, pelanggan.getEmail());
            ps.setString(3, pelanggan.getTelepon());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    pelanggan.setPelangganId(id);
                    return id;
                }
            }
        }
        return -1;
    }

    @Override
    public void update(Pelanggan pelanggan) throws SQLException {
        String sql = "UPDATE pelanggan SET nama = ?, email = ?, telepon = ? WHERE pelanggan_id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pelanggan.getNama());
            ps.setString(2, pelanggan.getEmail());
            ps.setString(3, pelanggan.getTelepon());
            ps.setInt(4, pelanggan.getPelangganId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM pelanggan WHERE pelanggan_id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Pelanggan mapRow(ResultSet rs) throws SQLException {
        Pelanggan p = new Pelanggan();
        p.setPelangganId(rs.getInt("pelanggan_id"));
        p.setNama(rs.getString("nama"));
        p.setEmail(rs.getString("email"));
        p.setTelepon(rs.getString("telepon"));
        return p;
    }
}
