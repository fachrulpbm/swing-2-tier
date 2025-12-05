package worker.mahasiswa;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import model.Mahasiswa;
import service.MahasiswaService;
import view.MahasiswaFrame;

public class LoadMahasiswaWorker extends SwingWorker<List<Mahasiswa>, Void> {
    private final MahasiswaFrame frame;
    private final MahasiswaService mahasiswaService;

    public LoadMahasiswaWorker(MahasiswaFrame frame, MahasiswaService mahasiswaService) {
        this.frame = frame;
        this.mahasiswaService = mahasiswaService;
        frame.getProgressBar().setIndeterminate(true);
        frame.getProgressBar().setString("Loading mahasiswa data...");
    }

    @Override
    protected List<Mahasiswa> doInBackground() throws Exception {
        return mahasiswaService.getAllMahasiswa();
    }

    @Override
    protected void done() {
        frame.getProgressBar().setIndeterminate(false);
        try {
            List<Mahasiswa> result = get();
            frame.getProgressBar().setString(result.size() + " records loaded");
        } catch (Exception e) {
            frame.getProgressBar().setString("Failed to load data");
            JOptionPane.showMessageDialog(frame,
                    "Error loading data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}