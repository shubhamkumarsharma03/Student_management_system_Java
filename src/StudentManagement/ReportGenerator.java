package StudentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;

public class ReportGenerator extends JFrame {
    private JTextArea reportArea;
    private JButton generateButton, exportButton;

    public ReportGenerator() {
        setTitle("Report Generator");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Report Text Area
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        generateButton = new JButton("Generate Report");
        exportButton = new JButton("Export to File");

        buttonPanel.add(generateButton);
        buttonPanel.add(exportButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        generateButton.addActionListener((ActionEvent e) -> generateReport());
        exportButton.addActionListener((ActionEvent e) -> exportReport());
    }

    public void generateReport() {
        // Sample Report Data (Can be fetched from Database)
        String report = "STUDENT REPORT\n"
                + "------------------------------\n"
                + "Total Students: 50\n"
                + "Pass: 45\n"
                + "Fail: 5\n"
                + "Average Marks: 78%\n"
                + "------------------------------\n";

        reportArea.setText(report);
    }

    public void exportReport() {
        try {
            FileWriter writer = new FileWriter("Student_Report.txt");
            writer.write(reportArea.getText());
            writer.close();
            JOptionPane.showMessageDialog(this, "Report Exported Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error Exporting Report!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReportGenerator().setVisible(true));
    }
}
