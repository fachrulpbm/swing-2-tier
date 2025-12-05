package view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import view.tablemodel.MahasiswaTableModel;

public class MahasiswaFrame extends JFrame {

    private final JTextField searchField = new JTextField(30);
    private final JButton addButton = new JButton("Add New");
    private final JButton refreshButton = new JButton("Refresh");
    private final JButton deleteButton = new JButton("Delete");
    private final JLabel totalRecordsLabel = new JLabel("0 Records");

    private final JTable mahasiswaTable = new JTable();
    private final MahasiswaTableModel mahasiswaTableModel = new MahasiswaTableModel();
    private final JProgressBar progressBar = new JProgressBar();

    public MahasiswaFrame() {
        initializeUI();        
    }

    private void initializeUI(){
        setTitle("EduCore - Mahasiswa Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new MigLayout("fill, insets 20", "[grow]", "[]20[]20[]10[grow][][]"));

        mahasiswaTable.setModel(mahasiswaTableModel);
        mahasiswaTable.setRowHeight(40);
        progressBar.setStringPainted(true);

        add(new JLabel("Mahasiswa List"), "wrap");
        add(createSearchPanel(), "wrap, growx");
        add(createButtonPanel(), "wrap, growx");
        add(new JScrollPane(mahasiswaTable), "grow, wrap");
        add(progressBar, "growx, h 30!, wrap");
        add(totalRecordsLabel, "right");

        pack();
        setMinimumSize(new Dimension(1000, 600));
        setLocationRelativeTo(null);
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.add(new JLabel("Search:"));
        panel.add(searchField, "growx");
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.add(addButton);
        panel.add(refreshButton);
        panel.add(deleteButton);
        return panel;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JTable getMahasiswaTable() {
        return mahasiswaTable;
    }

    public MahasiswaTableModel getMahasiswaTableModel() {
        return mahasiswaTableModel;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JLabel getTotalRecordsLabel() {
        return totalRecordsLabel;
    }

}