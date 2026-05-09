import javax.swing.*;
import java.awt.*;

public class RegistrationFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JPasswordField confirmPassField;
    private JButton registerButton;
    private JButton backButton;

    public RegistrationFrame() {
        setTitle("Library System - Registration");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titleLabel = new JLabel("Librarian Registration", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        userField = new JTextField(15);
        mainPanel.add(userField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passField = new JPasswordField(15);
        mainPanel.add(passField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Confirm Password:"), gbc);
        gbc.gridx = 1;
        confirmPassField = new JPasswordField(15);
        mainPanel.add(confirmPassField, gbc);

        JPanel btnPanel = new JPanel();
        registerButton = new JButton("Register");
        backButton = new JButton("Back to Login");
        btnPanel.add(registerButton);
        btnPanel.add(backButton);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 5, 5, 5);
        mainPanel.add(btnPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);

        registerButton.addActionListener(e -> handleRegistration());
        backButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void handleRegistration() {
        String username = userField.getText();
        String password = new String(passField.getPassword());
        String confirmPassword = new String(confirmPassField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LibraryManager.addUser(new User(username, password));
        JOptionPane.showMessageDialog(this, "Registration Successful! You can now login.");
        dispose();
        new LoginFrame().setVisible(true);
    }
}
