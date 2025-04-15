package StudentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentDashboard extends JFrame {
    private User student;
    private JLabel backgroundLabel;
    private ImageIcon backgroundImage;

    public StudentDashboard(User student) {
        this.student = student;
        setTitle("Student Dashboard - " + student.getUsername());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Load Background Image
        backgroundImage = new ImageIcon("src/images/student_dashboard_bg.jpg");
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        updateBackground();
        add(backgroundLabel);

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome, " + student.getUsername());
        welcomeLabel.setBounds(300, 20, 300, 30);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        backgroundLabel.add(welcomeLabel);

        // Profile Button
        JButton profileButton = createStyledButton("View Profile", 300, 80);
        profileButton.addActionListener(e -> showProfile());

        // Enrolled Courses Button
        JButton coursesButton = createStyledButton("My Courses", 300, 140);
        coursesButton.addActionListener(e -> showEnrolledCourses());

        // Attendance Button
        JButton attendanceButton = createStyledButton("Check Attendance", 300, 200);
        attendanceButton.addActionListener(e -> checkAttendance());

        // Grades Button
        JButton gradesButton = createStyledButton("View Grades", 300, 260);
        gradesButton.addActionListener(e -> viewGrades());

        // Notifications Button
        JButton notificationsButton = createStyledButton("Notifications", 300, 320);
        notificationsButton.addActionListener(e -> showNotifications());

        // Logout Button
        JButton logoutButton = createStyledButton("Logout", 300, 380);
        logoutButton.setBackground(Color.RED);
        logoutButton.addActionListener(e -> logout());

        backgroundLabel.add(profileButton);
        backgroundLabel.add(coursesButton);
        backgroundLabel.add(attendanceButton);
        backgroundLabel.add(gradesButton);
        backgroundLabel.add(notificationsButton);
        backgroundLabel.add(logoutButton);

        // Resize Listener to Adjust Background Dynamically
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateBackground();
            }
        });

        setVisible(true);
    }

    private JButton createStyledButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 200, 40);
        button.setBackground(new Color(30, 144, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    private void showProfile() {
        JOptionPane.showMessageDialog(this, "Name: " + student.getUsername() + "\nEmail: example@student.com\nAge: 20", "Profile", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showEnrolledCourses() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "S86900@harma")) {
            String query = "SELECT course_name FROM enrollments WHERE student_name=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, student.getUsername());
            ResultSet rs = pst.executeQuery();

            StringBuilder courses = new StringBuilder("Enrolled Courses:\n");
            while (rs.next()) {
                courses.append(rs.getString("course_name")).append("\n");
            }
            JOptionPane.showMessageDialog(this, courses.toString(), "My Courses", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching courses!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkAttendance() {
        JOptionPane.showMessageDialog(this, "Attendance Details Coming Soon!", "Attendance", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewGrades() {
        JOptionPane.showMessageDialog(this, "Grades Feature Coming Soon!", "Grades", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showNotifications() {
        JOptionPane.showMessageDialog(this, "No New Notifications!", "Notifications", JOptionPane.INFORMATION_MESSAGE);
    }

    private void logout() {
        JOptionPane.showMessageDialog(this, "Logging Out...");
        dispose();
        new HomeScreen();
    }

    private void updateBackground() {
        Image img = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        backgroundLabel.setIcon(new ImageIcon(img));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
    }
}

