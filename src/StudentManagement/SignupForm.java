package StudentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignupForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleDropdown;
    private JButton signUpButton;
    private JLabel backgroundLabel;
    private ImageIcon backgroundImage;

    public SignupForm() {
        setTitle("Sign Up");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Load Background Image
        backgroundImage = new ImageIcon("src/images/signup_bg.jpg");
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        updateBackground();
        add(backgroundLabel);

        // Signup Form Title
        JLabel signupTitle = new JLabel("SIGN UP");
        signupTitle.setBounds(200, 20, 200, 30);
        signupTitle.setForeground(Color.YELLOW);
        signupTitle.setFont(new Font("Arial", Font.BOLD, 20));
        backgroundLabel.add(signupTitle);

        // Labels
        JLabel usernameLabel = createLabel("Username:", 80, 80);
        JLabel passwordLabel = createLabel("Password:", 80, 130);
        JLabel roleLabel = createLabel("Role:", 80, 180);

        backgroundLabel.add(usernameLabel);
        backgroundLabel.add(passwordLabel);
        backgroundLabel.add(roleLabel);

        // Username Field
        usernameField = new JTextField();
        usernameField.setBounds(180, 80, 200, 30);
        backgroundLabel.add(usernameField);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(180, 130, 200, 30);
        backgroundLabel.add(passwordField);

        // Role Dropdown
        String[] roles = {"STUDENT", "TEACHER", "ADMIN"};
        roleDropdown = new JComboBox<>(roles);
        roleDropdown.setBounds(180, 180, 200, 30);
        backgroundLabel.add(roleDropdown);

        // Sign-Up Button
        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(175, 240, 150, 35);
        signUpButton.setBackground(new Color(34, 139, 34));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);
        signUpButton.setBorder(BorderFactory.createEmptyBorder());
        signUpButton.setContentAreaFilled(false);
        signUpButton.setOpaque(true);
        backgroundLabel.add(signUpButton);

        // Sign-Up Action
        signUpButton.addActionListener(e -> signUp());

        // Resize Listener to Adjust Background Dynamically
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateBackground();
            }
        });

        setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 100, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private void signUp() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String role = (String) roleDropdown.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conn = null;
        PreparedStatement userPst = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "S86900@harma");
            conn.setAutoCommit(false); // Begin transaction

            // Insert into users table
            String userQuery = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            userPst = conn.prepareStatement(userQuery);
            userPst.setString(1, username);
            userPst.setString(2, password);
            userPst.setString(3, role);
            userPst.executeUpdate();

            conn.commit(); // Commit transaction
            JOptionPane.showMessageDialog(this, "Sign Up Successful!");
            new HomeScreen(); // Redirect to HomeScreen
            dispose();

        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction in case of error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error!", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (userPst != null) userPst.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void updateBackground() {
        Image img = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        backgroundLabel.setIcon(new ImageIcon(img));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
    }
}
