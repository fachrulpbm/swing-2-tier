package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import dao.MahasiswaDao;
import model.Mahasiswa;

public class MahasiswaDaoMySql implements MahasiswaDao {

    @Override
    public void insert(Mahasiswa mahasiswa) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO mahasiswas (nim, nama, jurusan) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, mahasiswa.getNim());
            statement.setString(2, mahasiswa.getNama());
            statement.setString(3, mahasiswa.getJurusan());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Mahasiswa mahasiswa) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "UPDATE mahasiswas SET nim = ?, nama = ?, jurusan = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, mahasiswa.getNim());
            statement.setString(2, mahasiswa.getNama());
            statement.setString(3, mahasiswa.getJurusan());
            statement.setInt(4, mahasiswa.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "DELETE FROM mahasiswas WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Mahasiswa> findAll() {
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM mahasiswas ORDER BY id");
            while (resultSet.next()) {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setId(resultSet.getInt("id"));
                mahasiswa.setNim(resultSet.getString("nim"));
                mahasiswa.setNama(resultSet.getString("nama"));
                mahasiswa.setJurusan(resultSet.getString("jurusan"));
                mahasiswaList.add(mahasiswa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswaList;
    }
}
