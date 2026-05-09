// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
   private JTextField userField;
   private JPasswordField passField;
   private JButton loginButton;

   public LoginFrame() {
      this.setTitle("Library System - Login");
      this.setSize(400, 250);
      this.setDefaultCloseOperation(3);
      this.setLocationRelativeTo((Component)null);
      this.setLayout(new BorderLayout());
      JPanel var1 = new JPanel(new GridBagLayout());
      var1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      GridBagConstraints var2 = new GridBagConstraints();
      var2.fill = 2;
      var2.insets = new Insets(5, 5, 5, 5);
      JLabel var3 = new JLabel("Librarian Login", 0);
      var3.setFont(new Font("Arial", 1, 20));
      this.add(var3, "North");
      var2.gridx = 0;
      var2.gridy = 0;
      var1.add(new JLabel("Username:"), var2);
      var2.gridx = 1;
      this.userField = new JTextField(15);
      var1.add(this.userField, var2);
      var2.gridx = 0;
      var2.gridy = 1;
      var1.add(new JLabel("Password:"), var2);
      var2.gridx = 1;
      this.passField = new JPasswordField(15);
      var1.add(this.passField, var2);
      var2.gridx = 0;
      var2.gridy = 2;
      var2.gridwidth = 2;
      var2.insets = new Insets(15, 5, 5, 5);
      JPanel var4 = new JPanel();
      this.loginButton = new JButton("Login");
      JButton var5 = new JButton("Register");
      this.loginButton.setFocusPainted(false);
      var5.setFocusPainted(false);
      var4.add(this.loginButton);
      var4.add(var5);
      var1.add(var4, var2);
      this.add(var1, "Center");
      this.loginButton.addActionListener(new ActionListener() {
         {
            Objects.requireNonNull(LoginFrame.this);
         }

         public void actionPerformed(ActionEvent var1) {
            LoginFrame.this.handleLogin();
         }
      });
      var5.addActionListener((var1x) -> {
         this.dispose();
         (new RegistrationFrame()).setVisible(true);
      });
      this.passField.addActionListener((var1x) -> this.handleLogin());
   }

   private void handleLogin() {
      String var1 = this.userField.getText();
      String var2 = new String(this.passField.getPassword());
      if (LibraryManager.authenticate(var1, var2)) {
         JOptionPane.showMessageDialog(this, "Login Successful!");
         this.dispose();
         (new DashboardFrame()).setVisible(true);
      } else {
         JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Login Error", 0);
      }

   }
}
