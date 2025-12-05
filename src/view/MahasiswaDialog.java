package view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Mahasiswa;
import net.miginfocom.swing.MigLayout;

public class MahasiswaDialog extends JDialog {

    private final JTextField nimField = new JTextField(25);
    private final JTextField namaField = new JTextField(25);
    private final JTextField jurusanField = new JTextField(25);
    private final JButton saveButton = new JButton("Save");
    private final JButton cancelButton = new JButton("Cancel");

    private Mahasiswa mahasiswa;

    public MahasiswaDialog(JFrame owner) {
        super(owner, "Add New Mahasiswa", true);
        this.mahasiswa = new Mahasiswa();
        setupComponents();
    }

    public MahasiswaDialog(JFrame owner, Mahasiswa mahasiswaToEdit) {
        super(owner, "Edit Mahasiswa", true);
        this.mahasiswa = mahasiswaToEdit;
        setupComponents();
        
        // Isi field dengan data lama
        nimField.setText(mahasiswaToEdit.getNim());
        namaField.setText(mahasiswaToEdit.getNama());
        jurusanField.setText(mahasiswaToEdit.getJurusan());
    }

    private void setupComponents() {
        setLayout(new MigLayout("fill, insets 30", "[right]20[grow]"));
        add(new JLabel("NIM"), "");
        add(nimField, "growx, wrap");
        add(new JLabel("Nama Lengkap"), "");
        add(namaField, "growx, wrap");
        add(new JLabel("Jurusan"), "");
        add(jurusanField, "growx, wrap");

        JPanel buttonPanel = new JPanel(new MigLayout("insets 0", "[]10[]"));
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, "span, right");

        pack();
        setMinimumSize(new Dimension(500, 400));
        setLocationRelativeTo(getOwner());
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public Mahasiswa getMahasiswa() {
        mahasiswa.setNim(nimField.getText().trim());
        mahasiswa.setNama(namaField.getText().trim());
        mahasiswa.setJurusan(jurusanField.getText().trim());
        return mahasiswa;
    }
}