import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import java.awt.Component;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;


public class MainFrame {

   private JFrame frmApartmentManagementSystem;
   private JTextField textField;
   private JPasswordField passwordField;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               MainFrame window = new MainFrame();
               window.frmApartmentManagementSystem.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public MainFrame() {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() 
   {
      frmApartmentManagementSystem = new JFrame();
      frmApartmentManagementSystem.setTitle("Sign In - KaoZariv Apartment Database - CS 157A");
      frmApartmentManagementSystem.setBounds(100, 100, 495, 239);
      frmApartmentManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frmApartmentManagementSystem.getContentPane().setLayout(null);
      
      Button button = new Button("Sign-up");
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
         }
      });
      button.setBounds(121, 118, 158, 42);
      frmApartmentManagementSystem.getContentPane().add(button);
      
      textField = new JTextField();
      textField.setBounds(121, 36, 331, 25);
      frmApartmentManagementSystem.getContentPane().add(textField);
      textField.setColumns(10);
      
      JLabel lblUsername = new JLabel("Username");
      lblUsername.setBounds(45, 41, 66, 14);
      frmApartmentManagementSystem.getContentPane().add(lblUsername);
      
      passwordField = new JPasswordField();
      passwordField.setBounds(121, 72, 331, 25);
      frmApartmentManagementSystem.getContentPane().add(passwordField);
      
      JLabel lblPassword = new JLabel("Password");
      lblPassword.setBounds(45, 77, 46, 14);
      frmApartmentManagementSystem.getContentPane().add(lblPassword);
      
      JLabel lblInvalidUsernamepasswordCombination = new JLabel("Invalid Username/Password Combination!");
      lblInvalidUsernamepasswordCombination.setFont(new Font("Arial Black", Font.PLAIN, 11));
      lblInvalidUsernamepasswordCombination.setForeground(Color.RED);
      lblInvalidUsernamepasswordCombination.setBounds(161, 166, 291, 25);
      frmApartmentManagementSystem.getContentPane().add(lblInvalidUsernamepasswordCombination);
      
      Button button_1 = new Button("Sign-in");      
      button_1.setBounds(294, 118, 158, 42);
      button_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
         }
      });
      
      frmApartmentManagementSystem.getContentPane().add(button_1);
   }
}
