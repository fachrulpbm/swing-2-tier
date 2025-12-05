package controller;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Mahasiswa;
import service.MahasiswaService;
import service.MahasiswaServiceDefault;
import view.MahasiswaDialog;
import view.MahasiswaFrame;
import worker.mahasiswa.DeleteMahasiswaWorker;
import worker.mahasiswa.LoadMahasiswaWorker;
import worker.mahasiswa.SaveMahasiswaWorker;
import worker.mahasiswa.UpdateMahasiswaWorker;

public class MahasiswaController {
    private final MahasiswaFrame frame;
    private final MahasiswaService mahasiswaService = new MahasiswaServiceDefault();

    private List<Mahasiswa> allMahasiswa = new ArrayList<>();
    private List<Mahasiswa> displayedMahasiswa = new ArrayList<>();

    public MahasiswaController(MahasiswaFrame frame) {
        this.frame = frame;
        setupEventListeners();
        loadAllMahasiswa();
    }

    private void setupEventListeners() {
        frame.getAddButton().addActionListener(e -> openMahasiswaDialog(null));
        frame.getRefreshButton().addActionListener(e -> loadAllMahasiswa());
        frame.getDeleteButton().addActionListener(e -> deleteSelectedMahasiswa());

        frame.getMahasiswaTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = frame.getMahasiswaTable().getSelectedRow();
                    if (selectedRow >= 0) {
                        openMahasiswaDialog(displayedMahasiswa.get(selectedRow));
                    }
                }
            }
        });

        frame.getSearchField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                applySearchFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                applySearchFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                applySearchFilter();
            }

            private void applySearchFilter() {
                String keyword = frame.getSearchField().getText().toLowerCase().trim();
                displayedMahasiswa = new ArrayList<>();
                for (Mahasiswa mahasiswa : allMahasiswa) {
                    if (mahasiswa.getNim().toLowerCase().contains(keyword) ||
                            mahasiswa.getNama().toLowerCase().contains(keyword) ||
                            (mahasiswa.getJurusan() != null
                                    && mahasiswa.getJurusan().toLowerCase().contains(keyword))) {
                        displayedMahasiswa.add(mahasiswa);
                    }
                }
                frame.getMahasiswaTableModel().setMahasiswaList(displayedMahasiswa);
                updateTotalRecordsLabel();
            }
        });
    }

    private void openMahasiswaDialog(Mahasiswa mahasiswaToEdit) {
        MahasiswaDialog dialog;

        if (mahasiswaToEdit == null) {
            dialog = new MahasiswaDialog(frame);
        } else {
            dialog = new MahasiswaDialog(frame, mahasiswaToEdit);
        }
        
        dialog.getSaveButton().addActionListener(e -> {
            Mahasiswa mahasiswa = dialog.getMahasiswa();
            SwingWorker<Void, Void> worker;

            if (mahasiswaToEdit == null) {
                worker = new SaveMahasiswaWorker(frame, mahasiswaService, mahasiswa);
            } else {
                worker = new UpdateMahasiswaWorker(frame, mahasiswaService, mahasiswa);
            }

            worker.addPropertyChangeListener(evt -> {
                if (SwingWorker.StateValue.DONE.equals(evt.getNewValue())) {
                    dialog.dispose();
                    loadAllMahasiswa();
                }
            });
            worker.execute();
        });
        dialog.setVisible(true);
    }

    private void deleteSelectedMahasiswa() {
        int selectedRow = frame.getMahasiswaTable().getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(frame, "Please select a record to delete.");
            return;
        }
        Mahasiswa mahasiswa = displayedMahasiswa.get(selectedRow);
        int confirm = JOptionPane.showConfirmDialog(frame,
                "Delete mahasiswa: " + mahasiswa.getNim() + " - " + mahasiswa.getNama() + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            DeleteMahasiswaWorker worker = new DeleteMahasiswaWorker(frame, mahasiswaService, mahasiswa);
            worker.addPropertyChangeListener(evt -> {
                if (SwingWorker.StateValue.DONE.equals(evt.getNewValue())) {
                    loadAllMahasiswa();
                }
            });
            worker.execute();
        }
    }

    private void loadAllMahasiswa() {
        frame.getProgressBar().setIndeterminate(true);
        frame.getProgressBar().setString("Loading data...");

        LoadMahasiswaWorker worker = new LoadMahasiswaWorker(frame, mahasiswaService);
        worker.addPropertyChangeListener(evt -> {
            if (SwingWorker.StateValue.DONE.equals(evt.getNewValue())) {
                try {
                    allMahasiswa = worker.get();
                    displayedMahasiswa = new ArrayList<>(allMahasiswa);
                    frame.getMahasiswaTableModel().setMahasiswaList(displayedMahasiswa);
                    updateTotalRecordsLabel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Failed to load data.");
                } finally {
                    frame.getProgressBar().setIndeterminate(false);
                    frame.getProgressBar().setString("Ready");
                }
            }
        });
        worker.execute();
    }

    private void updateTotalRecordsLabel() {
        frame.getTotalRecordsLabel().setText(displayedMahasiswa.size() + " Records");
    }
}