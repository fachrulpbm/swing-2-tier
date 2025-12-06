package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

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
        setTitle("EduCore - Management Mahasiswa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new MigLayout("fill, insets 20", "[grow]", "[]10[]10[grow]10[]10[]"));

        mahasiswaTable.setModel(mahasiswaTableModel);
        progressBar.setStringPainted(true);

        add(new JLabel("List Mahasiswa"), "wrap, span 2");
        add(createSearchPanel(), "growx, w 80%");
        add(createButtonPanel(), "wrap, right, w 20%");
        add(new JScrollPane(mahasiswaTable), "grow, wrap, span 2");
        add(progressBar, "growx, h 20!, wrap, span 2");
        add(totalRecordsLabel, "right, span 2");

        pack();
        setMinimumSize(new Dimension(1000, 600));
        setLocationRelativeTo(null);
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new MigLayout(""));
        panel.add(new JLabel("Search:"));
        panel.add(searchField, "growx");
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new MigLayout("right"));
        
        addButton.setBackground(UIManager.getColor("Button.default.background"));
        addButton.setForeground(UIManager.getColor("Button.default.foreground"));
        addButton.setFont(addButton.getFont().deriveFont(Font.BOLD));
        
        panel.add(deleteButton);
        panel.add(refreshButton);
        panel.add(addButton);
        
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