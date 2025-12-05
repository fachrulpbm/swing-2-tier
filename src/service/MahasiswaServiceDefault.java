package service;

import java.util.List;

import dao.MahasiswaDao;
import dao.mysql.MahasiswaDaoMySql;
import model.Mahasiswa;

public class MahasiswaServiceDefault implements MahasiswaService {
    private final MahasiswaDao mahasiswaDao = new MahasiswaDaoMySql();

    @Override
    public void createMahasiswa(Mahasiswa mahasiswa) {
        mahasiswaDao.insert(mahasiswa);
    }

    @Override
    public void updateMahasiswa(Mahasiswa mahasiswa) {
        mahasiswaDao.update(mahasiswa);
    }

    @Override
    public void deleteMahasiswa(Mahasiswa mahasiswa) {
        mahasiswaDao.delete(mahasiswa.getId());
    }

    @Override
    public List<Mahasiswa> getAllMahasiswa() {
        return mahasiswaDao.findAll();
    }
}