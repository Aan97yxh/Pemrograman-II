package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import dao.BukuDao;
import dao.impl.BukuDaoImpl;
import model.Buku;

public class BukuService {

    private final BukuDao bukuDao = new BukuDaoImpl();

    public List<Buku> getAll() throws SQLException {
        return bukuDao.getAll();
    }

    public Optional<Buku> findById(int id) throws SQLException {
        return bukuDao.findById(id);
    }

    public List<Buku> searchByTitleOrAuthor(String q) throws SQLException {
        return bukuDao.searchByTitleOrAuthor(q);
    }


    public int insert(Buku buku) throws SQLException {
        return bukuDao.insert(buku);
    }

    public void update(Buku buku) throws SQLException {
        bukuDao.update(buku);
    }

    public void delete(int id) throws SQLException {
        bukuDao.delete(id);
    }
}
