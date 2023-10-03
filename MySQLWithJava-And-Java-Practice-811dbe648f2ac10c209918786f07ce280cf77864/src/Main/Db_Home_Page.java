package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.print.attribute.standard.Media;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Db_Home_Page extends JFrame {

    JPanel contentPane;
    JButton logoutButton;
    JButton stockButton;
    JButton emp_directory;
    JButton orderForm;

    public Db_Home_Page() {

    }


    //The frame for the home page.
    public Db_Home_Page(String user) {
        setSize(1000, 1000);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //the company stock page
        stockButton = new JButton("Check Stock");
        stockButton.setBackground(UIManager.getColor("Button.disabledForeground"));
        stockButton.setForeground(Color.WHITE);
        stockButton.setBackground(new Color(56, 100, 100));
        stockButton.setFont(new Font("Arial", Font.BOLD, 30));
        stockButton.setBounds(75, 100, 250, 95);
        //stockButton.setBackground(Color.GREEN); //Can change color if needed
        stockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int b = JOptionPane.showConfirmDialog(stockButton, "Click to yes open stock page.", "Check Stock?", JOptionPane.YES_NO_OPTION);

                if (b == JOptionPane.YES_OPTION) {
                    dispose();
                    //only to check if working, prints in terminal
                    System.out.println("Options are 'Yes' = 0, 'No' = 1,");
                    System.out.println("Values Clicked: " + b);
                    System.out.println("Stock viewed.");

                    StockPage newStockPageView = new StockPage(user);
                    newStockPageView.setTitle("Stock Page");
                    newStockPageView.setVisible(true);
                    JOptionPane.showMessageDialog(stockButton, "Welcome to the stock page!!");

                } else if (b == JOptionPane.NO_OPTION) {

                    //only to check if working
                    System.out.println("Options are 'Yes' = 0, 'No' = 1");
                    System.out.println("Values Clicked: " + b);
                    System.out.println("Stock not checked...");
                    JOptionPane.showMessageDialog(stockButton, "Select an option");
                    //return;
                }
            }
        });
        contentPane.add(stockButton);


        //The emp_directory button
        emp_directory = new JButton("Employee Directory");
        //emp_directory.setBackground(Color.GREEN); //can change color if needed
        emp_directory.setBounds(75, 200, 250, 95);
        emp_directory.setForeground(Color.WHITE);
        emp_directory.setBackground(new Color(56, 100, 100));
        emp_directory.setFont(new Font("Arial", Font.BOLD, 30));
        emp_directory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int c = JOptionPane.showConfirmDialog(emp_directory, "Do you want to see Employee Directory?", "Confirmation window", JOptionPane.YES_NO_OPTION);

                if (c == JOptionPane.YES_OPTION) {

                    //only to check if working
                    System.out.println("Options are 'Yes' = 0, 'No' = 1");
                    System.out.println("Values Clicked: " + c);
                    System.out.println("Opening Directory.");

                } else if (c == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(emp_directory, "Select an option");

                }
            }
        });
        contentPane.add(emp_directory);

        //The emp_directory button
        orderForm = new JButton("Create Order");
        //emp_directory.setBackground(Color.GREEN); //can change color if needed
        orderForm.setBounds(75, 300, 250, 95);
        orderForm.setForeground(Color.WHITE);
        orderForm.setBackground(new Color(56, 100, 100));
        orderForm.setFont(new Font("Arial", Font.BOLD, 30));
        orderForm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int c = JOptionPane.showConfirmDialog(orderForm, "Do you want to see create an order form?", "Confirmation window", JOptionPane.YES_NO_OPTION);

                if (c == JOptionPane.YES_OPTION) {

                    OrderForm orderForm = new OrderForm(user);
                    orderForm.setVisible(true);
                    dispose();
                    //only to check if working
                    System.out.println("Options are 'Yes' = 0, 'No' = 1");
                    System.out.println("Values Clicked: " + c);
                    System.out.println("Opening Order Form.");

                } else if (c == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(orderForm, "Select an option");

                }
            }
        });
        contentPane.add(orderForm);

        //The logout button.
        logoutButton = new JButton("Logout");
        //logoutButton.setBackground(Color.GREEN); //can change color if needed
        logoutButton.setBounds(75, 600, 250, 95);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(56, 100, 100));
        logoutButton.setFont(new Font("Arial", Font.BOLD, 30));
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int a = JOptionPane.showConfirmDialog(logoutButton, "Are you sure you want to logout?", "Confirm logout", JOptionPane.YES_NO_OPTION);

                if (a == JOptionPane.YES_OPTION) {
                    // Create a new instance of the sign in window
                    Db_Sign_In backToLogIn = new Db_Sign_In();
                    backToLogIn.setVisible(true);
                    dispose(); // Close the current registration window

                    //only to check if working
                    System.out.println("Options are 'Yes' = 0, 'No' = 1");
                    System.out.println("Values Clicked: " + a);
                    System.out.println("GoodBye.");

                } else if (a == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(logoutButton, "Glad you stayed!");
                }
            }
        });
        contentPane.add(logoutButton);

    }
}
