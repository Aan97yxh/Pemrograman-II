package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dao.PenjualanDao;
import model.Penjualan;
import util.DatabaseHelper;

public class PenjualanDaoImpl implements PenjualanDao {

    @Override
    public List<Penjualan> getAll() throws SQLException {
        String sql = "SELECT penjualan_id, jumlah, total_harga, tanggal, pelanggan_id, buku_id FROM penjualan ORDER BY penjualan_id";
        List<Penjualan> list = new ArrayList<>();
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    @Override
    public Optional<Penjualan> findById(int id) throws SQLException {
        String sql = "SELECT penjualan_id, jumlah, total_harga, tanggal, pelanggan_id, buku_id FROM penjualan WHERE penjualan_id = ?";
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
    public int insert(Penjualan penjualan) throws SQLException {
        String selectStokSql = "SELECT stok FROM buku WHERE buku_id = ? FOR UPDATE";
        String updateStokSql = "UPDATE buku SET stok = stok - ? WHERE buku_id = ?";
        String insertPenjualanSql = "INSERT INTO penjualan (jumlah, total_harga, tanggal, pelanggan_id, buku_id) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            conn.setAutoCommit(false);

            // 1) check stok (FOR UPDATE to lock row)
            try (PreparedStatement ps = conn.prepareStatement(selectStokSql)) {
                ps.setInt(1, penjualan.getBukuId());
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("Buku tidak ditemukan (id=" + penjualan.getBukuId() + ")");
                    }
                    int stok = rs.getInt("stok");
                    if (stok < penjualan.getJumlah()) {
                        throw new SQLException("Stok tidak cukup. Stok saat ini: " + stok);
                    }
                }
            }

            // 2) update stok
            try (PreparedStatement ps = conn.prepareStatement(updateStokSql)) {
                ps.setInt(1, penjualan.getJumlah());
                ps.setInt(2, penjualan.getBukuId());
                ps.executeUpdate();
            }

            // 3) insert penjualan
            try (PreparedStatement ps = conn.prepareStatement(insertPenjualanSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, penjualan.getJumlah());
                ps.setBigDecimal(2, penjualan.getTotalHarga());
                ps.setDate(3, penjualan.getTanggal());
                if (penjualan.getPelangganId() == null) {
                    ps.setNull(4, Types.INTEGER);
                } else {
                    ps.setInt(4, penjualan.getPelangganId());
                }
                ps.setInt(5, penjualan.getBukuId());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        int id = keys.getInt(1);
                        penjualan.setPenjualanId(id);
                        conn.commit();
                        return id;
                    } else {
                        throw new SQLException("Gagal mengambil generated key penjualan");
                    }
                }
            }

        } catch (SQLException ex) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException e) { /* ignore */ }
            }
            throw ex;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { /* ignore */ }
            }
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM penjualan WHERE penjualan_id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Penjualan mapRow(ResultSet rs) throws SQLException {
        Penjualan p = new Penjualan();
        p.setPenjualanId(rs.getInt("penjualan_id"));
        p.setJumlah(rs.getInt("jumlah"));
        p.setTotalHarga(rs.getBigDecimal("total_harga"));
        p.setTanggal(rs.getDate("tanggal"));
        int pelangganId = rs.getInt("pelanggan_id");
        if (rs.wasNull()) {
			p.setPelangganId(null);
		} else {
			p.setPelangganId(pelangganId);
		}
        p.setBukuId(rs.getInt("buku_id"));
        return p;
    }
}
