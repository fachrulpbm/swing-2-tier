-- Database: realtime_db
-- Table: mahasiswas
-- Total records: 200.000
-- Import time: ~15-25 detik di phpMyAdmin (tergantung server)
-- Data di-generate via AI Chatbot

SET FOREIGN_KEY_CHECKS = 0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;

TRUNCATE TABLE `mahasiswas`;

INSERT INTO `mahasiswas` (`id`, `nim`, `nama`, `jurusan`) VALUES
(1,    '202300001', 'Ahmad Fauzi', 'Teknik Informatika'),
(2,    '202300002', 'Siti Nurhaliza', 'Sistem Informasi'),
(3,    '202300003', 'Budi Santoso', 'Teknik Elektro'),
(4,    '202300004', 'Rina Wulandari', 'Manajemen'),
(5,    '202300005', 'Dian Kusuma', 'Akuntansi');

DELIMITER $$

CREATE PROCEDURE InsertMahasiswaMassal()
BEGIN
    DECLARE i INT DEFAULT 6; -- mulai dari id 6 (karena 1-5 sudah manual)
    DECLARE max_rows INT DEFAULT 200000;

    WHILE i <= max_rows DO
        INSERT INTO mahasiswas (nim, nama, jurusan) VALUES (
            CONCAT('2023', LPAD(i, 6, '0')),
            CONCAT(
                ELT(FLOOR(1 + RAND()*20), 'Ahmad','Siti','Budi','Rina','Dian','Eko','Putri','Agus','Wulan','Hadi',
                                  'Laras','Fajar','Intan','Rizky','Nadia','Farhan','Citra','Dimas','Anisa','Bayu'),
                ' ',
                ELT(FLOOR(1 + RAND()*20), 'Fauzi','Nurhaliza','Santoso','Wulandari','Kusuma','Pratama','Sari','Wijaya','Lestari','Saputra',
                                  'Rahayu','Gunawan','Purnama','Aulia','Hidayat','Maulana','Permata','Ardiansyah','Fitriani','Ramadhan')
            ),
            ELT(FLOOR(1 + RAND()*10),
                'Teknik Informatika','Sistem Informasi','Teknik Elektro','Manajemen','Akuntansi',
                'Ilmu Komunikasi','Psikologi','Hukum','Teknik Sipil','Arsitektur')
        );
        SET i = i + 1;
    END WHILE;
END$$

DELIMITER ;

CALL InsertMahasiswaMassal();

DROP PROCEDURE InsertMahasiswaMassal;

ALTER TABLE mahasiswas AUTO_INCREMENT = 200001;

COMMIT;
SET FOREIGN_KEY_CHECKS = 1;