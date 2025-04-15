package StudentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {
    private JLabel imageLabel;
    private ImageIcon originalIcon;

    public HomeScreen() {
        setTitle("Home Screen");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Load the background image
        originalIcon = new ImageIcon("src/images/background.jpg"); // Adjust path if needed
        if (originalIcon.getIconWidth() == -1) {
            System.out.println("Image not found! Check the path.");
        }

        // JLabel to display the image
        imageLabel = new JLabel();
        updateImageSize(); // Scale image initially
        add(imageLabel, BorderLayout.CENTER);

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to Student Management System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.WHITE);
        add(welcomeLabel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setOpaque(false);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> openLogin());

        // Sign Up Button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 14));
        signUpButton.setBackground(Color.GREEN);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.addActionListener(e -> openSignUp());

        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to panel
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method to update image size dynamically
    private void updateImageSize() {
        int width = getWidth();
        int height = getHeight();
        Image img = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
    }

    // Open Login Window
    private void openLogin() {
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }

    // Open Sign-Up Window
    private void openSignUp() {
        SignupForm signUpForm = new SignupForm();
        signUpForm.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomeScreen::new);
    }
}
