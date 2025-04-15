package StudentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificationsGUI extends JFrame {
    private DefaultListModel<String> notificationListModel;
    private JList<String> notificationList;
    private JButton clearButton, markReadButton;

    public NotificationsGUI() {
        setTitle("Notifications");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sample Notifications
        notificationListModel = new DefaultListModel<>();
        notificationListModel.addElement("Assignment Deadline: 5th April " + getCurrentTime());
        notificationListModel.addElement("New Message from Admin " + getCurrentTime());
        notificationListModel.addElement("Exam Schedule Updated " + getCurrentTime());

        // Notification List
        notificationList = new JList<>(notificationListModel);
        JScrollPane scrollPane = new JScrollPane(notificationList);
        add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        markReadButton = new JButton("Mark as Read");
        clearButton = new JButton("Clear All");

        buttonPanel.add(markReadButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        markReadButton.addActionListener((ActionEvent e) -> markNotificationAsRead());
        clearButton.addActionListener((ActionEvent e) -> clearNotifications());
    }

    private void markNotificationAsRead() {
        int selectedIndex = notificationList.getSelectedIndex();
        if (selectedIndex != -1) {
            notificationListModel.remove(selectedIndex);
            JOptionPane.showMessageDialog(this, "Notification Marked as Read", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a notification to mark as read", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearNotifications() {
        notificationListModel.clear();
        JOptionPane.showMessageDialog(this, "All Notifications Cleared", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return LocalDateTime.now().format(formatter);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NotificationsGUI().setVisible(true));
    }
}
