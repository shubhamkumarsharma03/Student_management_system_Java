package StudentManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewStudentsGUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton updateButton, deleteButton, exportButton;

    public ViewStudentsGUI() {
        setTitle("Student Records");
        setSize(600, 400);
        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        table = new JTable(model);
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Email");
        model.addColumn("Course");

        loadStudentData();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        exportButton = new JButton("Export to CSV");

        updateButton.addActionListener(e -> updateStudentRecord());
        deleteButton.addActionListener(e -> deleteStudentRecord());
        exportButton.addActionListener(e -> ExportToCSV.exportData());

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exportButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadStudentData() {
        model.setRowCount(0); // Clear table before reloading

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email"),
                        rs.getString("course")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "⚠ Database Error: " + e.getMessage());
        }
    }

    // DELETE STUDENT FUNCTION
    private void deleteStudentRecord() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "❌ Please select a student to delete!");
            return;
        }

        int studentId = (int) table.getValueAt(selectedRow, 0);

        try (Connection conn = DBConnection.getConnection()) {
            String query = "DELETE FROM students WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "✅ Student Deleted Successfully!");
                loadStudentData(); // Refresh table
            } else {
                JOptionPane.showMessageDialog(this, "⚠ No student found with that ID!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "⚠ Database Error: " + e.getMessage());
        }
    }

    // UPDATE STUDENT FUNCTION
    private void updateStudentRecord() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "❌ Please select a student to update!");
            return;
        }

        int studentId = (int) table.getValueAt(selectedRow, 0);
        String newName = JOptionPane.showInputDialog("Enter new name:", table.getValueAt(selectedRow, 1));
        int newAge = Integer.parseInt(JOptionPane.showInputDialog("Enter new age:", table.getValueAt(selectedRow, 2)));
        String newEmail = JOptionPane.showInputDialog("Enter new email:", table.getValueAt(selectedRow, 3));
        String newCourse = JOptionPane.showInputDialog("Enter new course:", table.getValueAt(selectedRow, 4));

        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE students SET name=?, age=?, email=?, course=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newName);
            stmt.setInt(2, newAge);
            stmt.setString(3, newEmail);
            stmt.setString(4, newCourse);
            stmt.setInt(5, studentId);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "✅ Student Updated Successfully!");
                loadStudentData(); // Refresh table
            } else {
                JOptionPane.showMessageDialog(this, "⚠ No student found with that ID!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "⚠ Database Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ViewStudentsGUI();
    }
}
