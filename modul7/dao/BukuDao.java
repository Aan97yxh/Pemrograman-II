package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import model.Buku;

public interface BukuDao {
    List<Buku> getAll() throws SQLException;
    Optional<Buku> findById(int id) throws SQLException;
    List<Buku> searchByTitleOrAuthor(String q) throws SQLException;
    int insert(Buku buku) throws SQLException; // returns generated id
    void update(Buku buku) throws SQLException;
    void delete(int id) throws SQLException;
}
