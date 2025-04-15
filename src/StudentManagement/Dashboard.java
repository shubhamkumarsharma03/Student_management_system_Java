package StudentManagement;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    public Dashboard(User user) {
        setTitle("Dashboard - " + user.getRole());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("src/images/dashboard_bg.jpg")));
        setLayout(new FlowLayout());

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername() + " (" + user.getRole() + ")");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(welcomeLabel);

        if (user.getRole() == Role.ADMIN) {
            JButton addStudentButton = new JButton("Add Student");
            JButton viewStudentsButton = new JButton("View Students");
            JButton addTeacherButton = new JButton("Add Teacher");
            JButton viewTeachersButton = new JButton("View Teachers");
            JButton generateReportsButton = new JButton("Generate Reports");
            JButton settingsButton = new JButton("Settings");
            JButton profileButton = new JButton("Profile");
            JButton notificationsButton = new JButton("Notifications");
            JButton exportDataButton = new JButton("Export Data");
            JTextField searchField = new JTextField(20);

            addStudentButton.addActionListener(e -> new AddStudentGUI().setVisible(true));
            viewStudentsButton.addActionListener(e -> new ViewStudentsGUI().setVisible(true));
            addTeacherButton.addActionListener(e -> new AddTeacherGUI().setVisible(true));
            viewTeachersButton.addActionListener(e -> new ViewTeachersGUI().setVisible(true));
            generateReportsButton.addActionListener(e -> new ReportGenerator().generateReport());
            settingsButton.addActionListener(e -> new SettingsGUI().setVisible(true));
            profileButton.addActionListener(e -> new ProfileGUI().setVisible(true));
            notificationsButton.addActionListener(e -> new NotificationsGUI().setVisible(true));
            exportDataButton.addActionListener(e -> new ExportData().exportToExcel());

            searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                public void insertUpdate(javax.swing.event.DocumentEvent e) { searchStudents(searchField.getText()); }
                public void removeUpdate(javax.swing.event.DocumentEvent e) { searchStudents(searchField.getText()); }
                public void changedUpdate(javax.swing.event.DocumentEvent e) {}
            });

            add(addStudentButton);
            add(viewStudentsButton);
            add(addTeacherButton);
            add(viewTeachersButton);
            add(generateReportsButton);
            add(settingsButton);
            add(profileButton);
            add(notificationsButton);
            add(exportDataButton);
            add(new JLabel("Search Students:"));
            add(searchField);
        }
    }

    private void searchStudents(String query) {
        System.out.println("Searching: " + query);
    }
}