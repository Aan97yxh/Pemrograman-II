package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import model.Pelanggan;

public interface PelangganDao {
    List<Pelanggan> getAll() throws SQLException;
    Optional<Pelanggan> findById(int id) throws SQLException;
    int insert(Pelanggan pelanggan) throws SQLException; // returns generated id
    void update(Pelanggan pelanggan) throws SQLException;
    void delete(int id) throws SQLException;
}
