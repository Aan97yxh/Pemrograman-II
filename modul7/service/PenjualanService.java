package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import dao.PenjualanDao;
import dao.impl.PenjualanDaoImpl;
import model.Penjualan;

public class PenjualanService {

    private final PenjualanDao penjualanDao = new PenjualanDaoImpl();

    public List<Penjualan> getAll() throws SQLException {
        return penjualanDao.getAll();
    }

    public Optional<Penjualan> findById(int id) throws SQLException {
        return penjualanDao.findById(id);
    }


    public int insert(Penjualan p) throws SQLException {
        return penjualanDao.insert(p);
    }

    public void delete(int id) throws SQLException {
        penjualanDao.delete(id);
    }
}
