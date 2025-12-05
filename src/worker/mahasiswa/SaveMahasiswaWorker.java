package worker.mahasiswa;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import model.Mahasiswa;
import service.MahasiswaService;
import view.MahasiswaFrame;

public class SaveMahasiswaWorker extends SwingWorker<Void, Void> {
    private final MahasiswaFrame frame;
    private final MahasiswaService mahasiswaService;
    private final Mahasiswa mahasiswa;

    public SaveMahasiswaWorker(MahasiswaFrame frame, MahasiswaService mahasiswaService, Mahasiswa mahasiswa) {
        this.frame = frame;
        this.mahasiswaService = mahasiswaService;
        this.mahasiswa = mahasiswa;
        frame.getProgressBar().setIndeterminate(true);
        frame.getProgressBar().setString("Saving new mahasiswa...");
    }

    @Override
    protected Void doInBackground() throws Exception {
        mahasiswaService.createMahasiswa(mahasiswa);
        return null;
    }

    @Override
    protected void done() {
        frame.getProgressBar().setIndeterminate(false);
        try {
            get(); // To catch any exception
            frame.getProgressBar().setString("Mahasiswa saved successfully");
            JOptionPane.showMessageDialog(frame,
                    "New mahasiswa record has been saved.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            frame.getProgressBar().setString("Failed to save mahasiswa");
            JOptionPane.showMessageDialog(frame,
                    "Error saving data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}