package StudentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel backgroundLabel;
    private ImageIcon backgroundImage;

    public LoginForm() {
        setTitle("Login");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Load Background Image
        backgroundImage = new ImageIcon("src/images/login_bg.jpg"); // Adjust path
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        updateBackground();
        add(backgroundLabel);

        // Username Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(100, 100, 100, 30);
        usernameLabel.setForeground(Color.WHITE);
        backgroundLabel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(200, 100, 200, 30);
        backgroundLabel.add(usernameField);

        // Password Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 150, 100, 30);
        passwordLabel.setForeground(Color.WHITE);
        backgroundLabel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 150, 200, 30);
        backgroundLabel.add(passwordField);

        // Login Button (Rounded Corners)
        loginButton = new JButton("Login");
        loginButton.setBounds(175, 200, 150, 35);
        loginButton.setBackground(new Color(30, 144, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setContentAreaFilled(false);
        loginButton.setOpaque(true);
        backgroundLabel.add(loginButton);

        // Login Action
        loginButton.addActionListener(e -> login());

        // Resize Listener to Adjust Background Dynamically
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateBackground();
            }
        });

        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "S86900@harma");
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Role role = Role.valueOf(rs.getString("role").toUpperCase()); // Convert role from DB to enum
                User loggedInUser = new User(username, password, role);

                JOptionPane.showMessageDialog(null, "Login Successful!");
                Dashboard dashboard = new Dashboard(loggedInUser);
                dashboard.setVisible(true); // Ensure Dashboard is visible
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }

            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBackground() {
        Image img = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        backgroundLabel.setIcon(new ImageIcon(img));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
    }
}
