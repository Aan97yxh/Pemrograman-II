package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import dao.PelangganDao;
import dao.impl.PelangganDaoImpl;
import model.Pelanggan;

public class PelangganService {

    private final PelangganDao pelangganDao = new PelangganDaoImpl();

    public List<Pelanggan> getAll() throws SQLException {
        return pelangganDao.getAll();
    }

    public Optional<Pelanggan> findById(int id) throws SQLException {
        return pelangganDao.findById(id);
    }

    public int insert(Pelanggan p) throws SQLException {
        return pelangganDao.insert(p);
    }

    public void update(Pelanggan p) throws SQLException {
        pelangganDao.update(p);
    }

    public void delete(int id) throws SQLException {
        pelangganDao.delete(id);
    }
}
