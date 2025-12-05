import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme;

import controller.MahasiswaController;
import view.MahasiswaFrame;

public class EduCoreApp{

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatGruvboxDarkHardIJTheme());
        } catch (Exception ex) {
            System.err.println("Gagal mengatur tema FlatLaf");
        }

        SwingUtilities.invokeLater(() -> {
            MahasiswaFrame frame = new MahasiswaFrame();
            new MahasiswaController(frame);
            frame.setVisible(true);
        });
    }

}

