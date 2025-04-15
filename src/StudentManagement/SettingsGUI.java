package StudentManagement;

import javax.swing.*;
import java.awt.*;

public class SettingsGUI extends JFrame {
    public SettingsGUI() {
        setTitle("Settings");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel passwordLabel = new JLabel("Change Password:");
        JPasswordField passwordField = new JPasswordField();

        JLabel notificationLabel = new JLabel("Enable Notifications:");
        JCheckBox notificationCheckBox = new JCheckBox();
        notificationCheckBox.setSelected(true);

        JLabel themeLabel = new JLabel("Theme:");
        String[] themes = {"Light Mode", "Dark Mode"};
        JComboBox<String> themeComboBox = new JComboBox<>(themes);

        JButton saveButton = new JButton("Save Settings");

        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Settings Saved Successfully!");
        });

        add(passwordLabel);
        add(passwordField);
        add(notificationLabel);
        add(notificationCheckBox);
        add(themeLabel);
        add(themeComboBox);
        add(new JLabel()); // Empty space
        add(saveButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SettingsGUI().setVisible(true));
    }
}
