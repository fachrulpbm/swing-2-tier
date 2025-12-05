package service;

import java.util.List;

import model.Mahasiswa;

public interface MahasiswaService {
    void createMahasiswa(Mahasiswa mahasiswa);

    void updateMahasiswa(Mahasiswa mahasiswa);

    void deleteMahasiswa(Mahasiswa mahasiswa);

    List<Mahasiswa> getAllMahasiswa();
}