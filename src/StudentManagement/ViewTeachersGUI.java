package StudentManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewTeachersGUI extends JFrame {
    private JTable teacherTable;
    private DefaultTableModel tableModel;

    public ViewTeachersGUI() {
        setTitle("View Teachers");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table Setup
        String[] columnNames = {"Name", "Subject", "Email", "Contact"};
        tableModel = new DefaultTableModel(columnNames, 0);
        teacherTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(teacherTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load data from database
        loadTeachers();
    }

    private void loadTeachers() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "S86900@harma");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM teachers");

            tableModel.setRowCount(0); // Clear existing data
            while (rs.next()) {
                String name = rs.getString("name");
                String subject = rs.getString("subject");
                String email = rs.getString("email");
                String contact = rs.getString("contact");
                tableModel.addRow(new Object[]{name, subject, email, contact});
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewTeachersGUI().setVisible(true));
    }
}
