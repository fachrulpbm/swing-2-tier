package view.tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Mahasiswa;

public class MahasiswaTableModel extends AbstractTableModel {
    private List<Mahasiswa> mahasiswaList = new ArrayList<>();
    private final String[] columnNames = { "ID", "NIM", "Nama", "Jurusan" };

    public void setMahasiswaList(List<Mahasiswa> mahasiswaList) {
        this.mahasiswaList = mahasiswaList;
        fireTableDataChanged();
    }

    public Mahasiswa getMahasiswaAt(int rowIndex) {
        return mahasiswaList.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return mahasiswaList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mahasiswa mahasiswa = mahasiswaList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> mahasiswa.getId();
            case 1 -> mahasiswa.getNim();
            case 2 -> mahasiswa.getNama();
            case 3 -> mahasiswa.getJurusan();
            default -> null;
        };
    }
}
