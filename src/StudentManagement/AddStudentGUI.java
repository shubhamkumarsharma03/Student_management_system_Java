package StudentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;

public class AddStudentGUI extends JFrame {
    private JTextField nameField, ageField, emailField, courseField;
    private JButton submitButton;


    public AddStudentGUI() {
        setTitle("Add Student");
        setSize(300, 300);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Age:"));
        ageField = new JTextField();
        add(ageField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Course:"));
        courseField = new JTextField();
        add(courseField);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveStudent();
            }
        });
        add(submitButton);

        setVisible(true);
    }

    private void saveStudent() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String email = emailField.getText();
        String course = courseField.getText();


        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO students (name, age, email, course) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, email);
            stmt.setString(4, course);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student Added Successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new AddStudentGUI();
    }
}

