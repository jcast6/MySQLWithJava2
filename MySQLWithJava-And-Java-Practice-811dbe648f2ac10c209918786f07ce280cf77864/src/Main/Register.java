package Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register extends JFrame {

    private JLabel pageHeading;
    private JLabel id;
    private JLabel fnameLabel;
    private JLabel lnameLabel;
    private JLabel departmentLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;

    private final JTextField idField;
    private final JTextField firstNameField;
    private final JTextField lastNameField;
    private final JTextField departmentField;
    private final JTextField emailField;
    private final JPasswordField passwordField;
   private final JButton registerButton;
    private Container container;

    public Register() {
        setSize(650, 650);
        setResizable(true);
        setTitle("Register form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        container = getContentPane();
        container.setLayout(null);

        pageHeading = new JLabel("New Employee Registration Form");
        pageHeading.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        pageHeading.setSize(300, 30);
        pageHeading.setLocation(250, 30);
        container.add(pageHeading);

        id = new JLabel("ID:");
        id.setSize(100, 20);
        id.setLocation(100, 75);
        container.add(id);

        idField = new JTextField();
        idField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        idField.setSize(190, 20);
        idField.setLocation(180, 75);
        container.add(idField);

        //focus listener to see if field matches required text
        idField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String input = idField.getText();
                String pattern = "^[0-9]{3}$"; // ID should be 3 characters long and only contain letters and digits
                if (!input.matches(pattern)) {
                    JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid ID with 3 characters, consisting of letters and digits only.");
                    idField.requestFocus();
                }
            }
        });

        fnameLabel = new JLabel("First Name:");
        fnameLabel.setSize(100, 20);
        fnameLabel.setLocation(100, 120);
        container.add(fnameLabel);

        firstNameField = new JTextField();
        firstNameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        firstNameField.setSize(190, 20);
        firstNameField.setLocation(180, 120);
        container.add(firstNameField);

        lnameLabel = new JLabel("Last Name:");
        lnameLabel.setSize(100, 20);
        lnameLabel.setLocation(100, 150);
        container.add(lnameLabel);

        lastNameField = new JTextField();
        lastNameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lastNameField.setSize(190, 20);
        lastNameField.setLocation(180,150);
        container.add(lastNameField);

        departmentLabel = new JLabel("Department:");
        departmentLabel.setSize(100, 20);
        departmentLabel.setLocation(100, 180);
        container.add(departmentLabel);

        departmentField = new JTextField();
        departmentField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        departmentField.setSize(190, 20);
        departmentField.setLocation(180, 180);
        container.add(departmentField);

        emailLabel = new JLabel("Email:");
        emailLabel.setSize(100, 20);
        emailLabel.setLocation(100, 210);
        container.add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        emailField.setSize(190, 20);
        emailField.setLocation(180,210);
        container.add(emailField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setSize(100, 20);
        passwordLabel.setLocation(100, 240);
        container.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        passwordField.setSize(190, 20);
        passwordField.setLocation(180,240);
        container.add(passwordField);

        // Button to register new emp
        registerButton = new JButton("Register New Employee");
        registerButton.setBounds(350, 395, 165, 55);
        // Add an action listener to the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the user input
                String userId = idField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String department = departmentField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Check if all the fields are filled
                if (userId.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || department.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
                    return;
                }

                int choice = JOptionPane.showOptionDialog(null, "Registration Successful!", "Registration",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Login", "Exit" }, null);

                if (choice == 0) {
                    // Create a new instance of the sign in window
                    Db_Sign_In signIn = new Db_Sign_In();
                    signIn.setVisible(true);
                    dispose(); // Close the current registration window
                } else if (choice == 1) {
                    System.exit(0);
                }

                // Insert the user into the database
                try {
                    PreparedStatement statement = Connect_to_DB.getConnection().prepareStatement("INSERT INTO employee(user_id, first_name, last_name, department, email, password) VALUES (?, ?, ?, ?, ?, SHA2(?, 256))");
                    statement.setString(1, userId);
                    statement.setString(2, firstName);
                    statement.setString(3, lastName);
                    statement.setString(4, department);
                    statement.setString(5, email);
                    statement.setString(6, password);
                    statement.executeUpdate();

                    PreparedStatement statement1 = Connect_to_DB.getConnection().prepareStatement("INSERT INTO emp_login(emp_f_name, emp_l_name, emp_email, emp_password) VALUES (?, ?, ?, SHA2(?, 256))");

                    statement1.setString(1, firstName);
                    statement1.setString(2, lastName);
                    statement1.setString(3, email);
                    statement1.setString(4, password);
                    statement1.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        container.add(registerButton);

    }
}



