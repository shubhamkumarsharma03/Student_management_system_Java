package StudentManagement;

import javax.swing.*;
import java.awt.*;

public class ProfileGUI extends JFrame {
    public ProfileGUI() {
        setTitle("User Profile");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField("John Doe");

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField("25");

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField("john.doe@example.com");

        JLabel accessLabel = new JLabel("Access Level:");
        JTextField accessField = new JTextField("Admin");
        accessField.setEditable(false);

        JButton editButton = new JButton("Edit Profile");
        JButton saveButton = new JButton("Save Changes");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);

        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(emailLabel);
        add(emailField);
        add(accessLabel);
        add(accessField);
        add(new JLabel());  // Empty space
        add(buttonPanel);

        editButton.addActionListener(e -> {
            nameField.setEditable(true);
            ageField.setEditable(true);
            emailField.setEditable(true);
        });

        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Profile Updated Successfully!");
            nameField.setEditable(false);
            ageField.setEditable(false);
            emailField.setEditable(false);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProfileGUI().setVisible(true));
    }
}
