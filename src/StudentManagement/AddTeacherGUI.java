package StudentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddTeacherGUI extends JFrame {
    private JTextField nameField, subjectField, emailField, contactField;
    private JButton addButton, cancelButton;

    public AddTeacherGUI() {
        setTitle("Add Teacher");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Form Fields
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Subject:"));
        subjectField = new JTextField();
        add(subjectField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Contact:"));
        contactField = new JTextField();
        add(contactField);

        // Buttons
        addButton = new JButton("Add Teacher");
        cancelButton = new JButton("Cancel");

        add(addButton);
        add(cancelButton);

        // Button Actions
        addButton.addActionListener((ActionEvent e) -> saveTeacher());
        cancelButton.addActionListener(e -> dispose());
    }

    private void saveTeacher() {
        String name = nameField.getText();
        String subject = subjectField.getText();
        String email = emailField.getText();
        String contact = contactField.getText();

        if (name.isEmpty() || subject.isEmpty() || email.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "S86900@harma");
            String sql = "INSERT INTO teachers (name, subject, email, contact) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, subject);
            stmt.setString(3, email);
            stmt.setString(4, contact);
            stmt.executeUpdate();
            conn.close();

            JOptionPane.showMessageDialog(this, "Teacher Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        subjectField.setText("");
        emailField.setText("");
        contactField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddTeacherGUI().setVisible(true));
    }
}
