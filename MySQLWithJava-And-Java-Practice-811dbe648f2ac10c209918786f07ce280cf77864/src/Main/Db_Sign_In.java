package Main;
/*This application is created to practice java and MySql, and learn about java
  and MySql.

  This is the first page of a store inventory page. The employee has to log in by
  their employee email and password. This is followed by the Db_Home_Page.java.


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Db_Sign_In extends JFrame{

    private final JTextField textF;
    private final JPasswordField pwField;
    private final JButton loginButton;
    private final JButton registerPage;
    JLabel label;
    JPanel contentPane;



    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Db_Sign_In frame = new Db_Sign_In();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //Creating the frame.
    public Db_Sign_In() {
        setSize(1000, 1000);
        setResizable(true);
        setTitle("Juan and Sons Inc.");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creating the page title.
        JLabel loginLb = new JLabel("Employees Login");
        loginLb.setFont(new Font("Times New Roman", Font.BOLD, 30));
        loginLb.setBounds(430, 15, 1000,  100);
        loginLb.setForeground(Color.BLACK);
        contentPane.add(loginLb);

        //Creating the email label.
        JLabel usernameLabel = new JLabel("Email:");
        usernameLabel.setBounds(372, 170, 195, 50);
        usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        usernameLabel.setBackground(Color.BLACK);
        usernameLabel.setForeground(Color.BLACK);
        contentPane.add(usernameLabel);

        //The username/email text field.
        textF = new JTextField();
        textF.setBounds(480, 170, 280, 68);
        textF.setFont(new Font("Times New Roman", Font.ITALIC, 40));
        textF.setColumns(10);
        contentPane.add(textF);

        //The password label
        JLabel pwLabel = new JLabel("Password: ");
        pwLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        pwLabel.setBounds(345, 286, 195, 50);
        pwLabel.setBackground(Color.BLACK);
        pwLabel.setForeground(Color.BLACK);
        contentPane.add(pwLabel);

        //The password field
        pwField = new JPasswordField();
        pwField.setBounds(481, 286, 281, 68);
        pwField.setFont(new Font("Times New Roman", Font.ITALIC, 40));
        contentPane.add(pwField);

        // Button to register new emp
        registerPage = new JButton("Register New Employee");
        registerPage.setBounds(350, 395, 165, 55);

        contentPane.add(registerPage);

        registerPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Register.java class
                Register register = new Register();
                register.setVisible(true);
                dispose(); // Close the current login window
            }
        });


        //The login button
        loginButton = new JButton("Login");
        loginButton.setBounds(550, 395, 165, 55);
        loginButton.addActionListener(e -> {
            String email = textF.getText();
            String pw = pwField.getText();


            // Try{}catch{} is used to connect to the database ,'getting_data', through a localhost and
            // retrieve data from a table.
            try {

                PreparedStatement pst = Connect_to_DB.getConnection().prepareStatement("Select emp_email, emp_password from emp_login where emp_email=? and emp_password= SHA2(?, 256)");
                pst.setString(1, email);
                pst.setString(2, pw);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    dispose();
                    Db_Home_Page newUser = new Db_Home_Page(email);
                    newUser.setTitle("Database Homepage!");
                    newUser.setVisible(true);
                    JOptionPane.showMessageDialog(loginButton, "Login successful!");

                } else {
                    JOptionPane.showMessageDialog(loginButton, "Login not successful! " +
                            "\nCheck email or password!");
                }
            } catch (SQLException er) {
                er.printStackTrace();

            }
        });

        contentPane.add(loginButton);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);


    }
}
*/

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Db_Sign_In extends JFrame {

    private final JTextField textF;
    private final JPasswordField pwField;
    private final JButton loginButton;
    private final JButton registerPage;
    JLabel label;
    JPanel contentPane;

    // Custom method to fetch text from JTextField
    private String getText1(JTextField textField) {
        return textField.getText();
    }

    // Custom method to fetch text from JPasswordField
    private String getText2(JPasswordField pwField) {
        return new String(pwField.getPassword());
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Db_Sign_In frame = new Db_Sign_In();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Db_Sign_In() {
        setSize(1000, 1000);
        setResizable(true);
        setTitle("Juan and Sons Inc.");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add logo image
        ImageIcon originalLogoIcon = new ImageIcon("C:\\Users\\pimpd\\IdeaProjects\\MySQLWithJava2\\MySQLWithJava-And-Java-Practice-811dbe648f2ac10c209918786f07ce280cf77864\\logo.png");
        int squareSize = 190; // The desired size of the square logo
        Image scaledImage = originalLogoIcon.getImage().getScaledInstance(squareSize, -1, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(160, 80, squareSize, squareSize);
        contentPane.add(logoLabel);

        // Add logo shadow
        int shadowSize = 190;
        int shadowOffset = 100;
        JLabel shadowLabel = new JLabel();
        shadowLabel.setBounds(200, 100, shadowSize, shadowSize);
        shadowLabel.setBackground(new Color(50, 100, 100, 50)); // Semi-transparent black color
        shadowLabel.setOpaque(true);
        contentPane.add(shadowLabel);


        // Modified "Employees Login" label
        JLabel loginLb = new JLabel("Employee Login");
        loginLb.setFont(new Font("Arial", Font.BOLD, 36));
        loginLb.setForeground(new Color(50, 50, 50));
        loginLb.setBounds(400, 80, 300, 50);
        contentPane.add(loginLb);

        // Shadow effect for "Employees Login" label
        JLabel shadowLb = new JLabel("Employee Login");
        shadowLb.setFont(new Font("Arial", Font.BOLD, 36));
        shadowLb.setForeground(new Color(50, 100, 100, 50));
        shadowLb.setBounds(410, 95, 300, 50);
        contentPane.add(shadowLb);



        JLabel usernameLabel = new JLabel("Email:");
        usernameLabel.setBounds(372, 170, 195, 50);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        usernameLabel.setBackground(Color.BLACK);
        usernameLabel.setForeground(Color.BLACK);
        contentPane.add(usernameLabel);

        textF = new JTextField();
        textF.setBounds(480, 170, 280, 68);
        textF.setFont(new Font("Arial", Font.ITALIC, 20));
        textF.setColumns(10);
        contentPane.add(textF);

        JLabel pwLabel = new JLabel("Password: ");
        pwLabel.setFont(new Font("Arial", Font.BOLD, 25));
        pwLabel.setBounds(345, 286, 195, 50);
        pwLabel.setBackground(Color.BLACK);
        pwLabel.setForeground(Color.BLACK);
        contentPane.add(pwLabel);

        pwField = new JPasswordField();
        pwField.setBounds(481, 286, 281, 68);
        pwField.setFont(new Font("Arial", Font.ITALIC, 20));
        contentPane.add(pwField);

        // Button to register new emp
        registerPage = new JButton("Register New Employee");
        registerPage.setBounds(350, 395, 165, 55);
        registerPage.setFont(new Font("Arial", Font.BOLD, 20));
        registerPage.setForeground(Color.WHITE);
        registerPage.setBackground(new Color(56, 100, 100));
        registerPage.setFocusPainted(false);
        registerPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Register.java class
                Register register = new Register();
                register.setVisible(true);
                dispose(); // Close the current login window
            }
        });
        contentPane.add(registerPage);

        //The login button
        loginButton = new JButton("Login");
        loginButton.setBounds(550, 395, 165, 55);
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(56, 100, 100));
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(e -> {
            String email = getText1(textF);
            String pw = getText2(pwField);

            // Try{}catch{} is used to connect to the database ,'getting_data', through a localhost and
            // retrieve data from a table.
            try {
                PreparedStatement pst = Connect_to_DB.getConnection().prepareStatement("Select emp_email, emp_password from emp_login where emp_email=? and emp_password= SHA2(?, 256)");
                pst.setString(1, email);
                pst.setString(2, pw);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    dispose();
                    Db_Home_Page newUser = new Db_Home_Page(email);
                    newUser.setTitle("Database Homepage!");
                    newUser.setVisible(true);
                    JOptionPane.showMessageDialog(loginButton, "Login successful!");

                } else {
                    JOptionPane.showMessageDialog(loginButton, "Login not successful! " +
                            "\nCheck email or password!");
                }
            } catch (SQLException er) {
                er.printStackTrace();
            }
        });
        contentPane.add(loginButton);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }
}