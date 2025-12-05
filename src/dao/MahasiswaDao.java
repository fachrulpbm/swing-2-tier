package dao;

import java.util.List;

import model.Mahasiswa;

public interface MahasiswaDao {
    void insert(Mahasiswa mahasiswa);

    void update(Mahasiswa mahasiswa);

    void delete(int id);

    List<Mahasiswa> findAll();
}