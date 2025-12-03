package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import model.Penjualan;

public interface PenjualanDao {
    List<Penjualan> getAll() throws SQLException;
    Optional<Penjualan> findById(int id) throws SQLException;
    int insert(Penjualan penjualan) throws SQLException;
    void delete(int id) throws SQLException;
}
