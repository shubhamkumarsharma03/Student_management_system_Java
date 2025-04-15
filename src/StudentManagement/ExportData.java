package StudentManagement;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;

public class ExportData {
    public void exportToExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Destination to Save File");
        fileChooser.setSelectedFile(new File("student_data.csv"));

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            fetchAndWriteData(fileToSave.getAbsolutePath());
        }
    }

    private void fetchAndWriteData(String filePath) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "S86900@harma");
             FileWriter writer = new FileWriter(filePath)) {

            String query = "SELECT name, age, course FROM students"; // Adjust table/column names
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            writer.write("Name, Age, Course\n"); // CSV Header

            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String course = rs.getString("course");
                writer.write(name + ", " + age + ", " + course + "\n");
            }

            JOptionPane.showMessageDialog(null, "Data exported successfully to:\n" + filePath);
            rs.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error exporting data!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
