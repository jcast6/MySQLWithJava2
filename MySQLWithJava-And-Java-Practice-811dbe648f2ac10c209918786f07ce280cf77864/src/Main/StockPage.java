package Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StockPage extends JFrame {
    JPanel contentPane;
    JButton stockButton1;
    JButton stockButton2;
    DefaultTableModel table1;
    DefaultTableModel table2;
    Statement st1;
    ResultSet rs1;
    ResultSet rs2;
    JButton backButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StockPage frame = new StockPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StockPage() {

    }

    //The frame for the stock page.
    public StockPage(String user) {
        setBounds(500, 200, 1015, 600);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //this button will display all the items in the store
        stockButton1 = new JButton("All Items");
        stockButton1.setBounds(100, 15, 195, 120);
        stockButton1.setFont(new Font("Arial", Font.BOLD, 15));
        stockButton1.setForeground(Color.WHITE);
        stockButton1.setBackground(new Color(56, 100, 100));
        stockButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(stockButton1, "Do you want to see all the items?", "Inventory", JOptionPane.YES_NO_OPTION);

                if (a == JOptionPane.YES_OPTION) {
                    try {


                        // Statement is the object used to execute a SQL statement and returns the result.
                        // This line creates the statement "st".
                        st1 = Connect_to_DB.getConnection().createStatement();

                        // ResultSet is a table of data representing a database result set.
                        // It is created by executing a SQL query.
                        // Here we create the ResultSet, rs, followed by st.executeQuery.
                        rs1 = st1.executeQuery("select * from stock_inventory;");

                        String columns[] = {"SKU", "product_name"};
                        String data[][] = new String[200][5];

                        int i = 0;

                        while (rs1.next()) {
                            String sku = rs1.getString("SKU");
                            String prod_name = rs1.getString("product_name");
                            data[i][0] = sku;
                            data[i][1] = prod_name;
                            i++;
                        }

                        table1 = new DefaultTableModel(data, columns);
                        JTable jtable1 = new JTable(table1);
                        jtable1.setShowGrid(true);
                        jtable1.setShowVerticalLines(true);
                        JScrollPane pane = new JScrollPane(jtable1);
                        JFrame f = new JFrame("Populate the table from database");
                        JPanel panel = new JPanel();
                        panel.add(pane);
                        f.add(panel);
                        f.setSize(500, 300);
                        f.setVisible(true);

                        /**
                         *
                         * This will be displayed in the console not gui.
                         System.out.println("Stock: ");
                         while (rs1.next()) {
                         // Here we create variables according to the table column names, and
                         // the appropriate data type. rs.getInt/String("column_name_in_database");
                         String sku = rs1.getString("SKU");
                         String product_name = rs1.getString("product_name");


                         //Format the output.
                         System.out.format("%s, %s\n", sku, product_name);

                         }
                         *
                         */
                    } catch (SQLException ec) {

                        //The printStackTrace() method is used to handle exceptions and errors.
                        ec.printStackTrace();

                    }
                    //only to check if working
                    System.out.println("Options are 'Yes' = 0, 'No' = 1,");
                    System.out.println("Values Clicked: " + a);
                    System.out.println("All stock displayed.");
                    contentPane.setVisible(true);

                } else if (a == JOptionPane.NO_OPTION) {

                    //only to check if working
                    System.out.println("Options are 'Yes' = 0, 'No' = 1");
                    System.out.println("Values Clicked: " + a);
                    System.out.println("Goodbye!");
                    contentPane.setVisible(true);

                }
            }
        });
        contentPane.add(stockButton1);

        // This button will display all the items with amount available
        stockButton2 = new JButton("Items with count");
        stockButton2.setBounds(400, 15, 195, 120);
        stockButton2.setBackground(UIManager.getColor("Button.disabledForeground"));
        stockButton2.setForeground(Color.WHITE);
        stockButton2.setBackground(new Color(56, 100, 100));
        stockButton2.setFont(new Font("Arial", Font.BOLD, 15));
        stockButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(stockButton2, "Display all items with amount?", "Inventory", JOptionPane.YES_NO_OPTION);

                if (a == JOptionPane.YES_OPTION) {
                    try {

                        // Statement is the object used to execute a SQL statement and returns the result.
                        // This line creates the statement "st" and we get a connection through the Connect_to_DB.java class.
                        st1 = Connect_to_DB.getConnection().createStatement();

                        // ResultSet is a table of data representing a database result set.
                        // It is created by executing a SQL query.
                        // Here we create the ResultSet, rs, followed by st.executeQuery.
                        rs2 = st1.executeQuery("select * from products_with_amounts");

                        String columns[] = {"SKU", "product_name", "amount"};
                        String data[][] = new String[200][5];

                        int i = 0;

                        while (rs2.next()) {
                            String sku = rs2.getString("SKU");
                            String prod_name = rs2.getString("product_name");
                            String amount = rs2.getString("amount");
                            data[i][0] = sku + "";
                            data[i][1] = prod_name;
                            data[i][2] = amount;
                            i++;
                        }

                        table2 = new DefaultTableModel(data, columns);
                        JTable jTable2 = new JTable(table2);
                        jTable2.setShowGrid(true);
                        jTable2.setShowVerticalLines(true);
                        JScrollPane pane = new JScrollPane(jTable2);
                        JFrame f = new JFrame("Products with current amounts");
                        JPanel panel = new JPanel();
                        panel.add(pane);
                        f.add(panel);
                        f.setSize(500, 300);
                        f.setVisible(true);

                        /**
                         *
                         * This will be displayed in the console not gui.
                         System.out.println("Stock: ");
                         while (rs1.next()) {
                         // Here we create variables according to the table column names, and
                         // the appropriate data type. rs.getInt/String("column_name_in_database");
                         String sku = rs1.getString("SKU");
                         String product_name = rs1.getString("product_name");


                         //Format the output.
                         System.out.format("%s, %s\n", sku, product_name);

                         }
                         */

                    } catch (SQLException ec) {

                        //The printStackTrace() method is used to handle exceptions and errors.
                        ec.printStackTrace();

                    }
                    //only to check if working
                    System.out.println("Options are 'Yes' = 0, 'No' = 1,");
                    System.out.println("Values Clicked: " + a);
                    System.out.println("All stock displayed.");
                    contentPane.setVisible(true);

                } else if (a == JOptionPane.NO_OPTION) {

                    //only to check if working
                    System.out.println("Options are 'Yes' = 0, 'No' = 1");
                    System.out.println("Values Clicked: " + a);
                    System.out.println("Goodbye!");
                    contentPane.setVisible(true);

                }
            }
        });
        contentPane.add(stockButton2);

        // Create the "Add New Item" button
        JButton addStockItem = new JButton("Add New Item");
        addStockItem.setBounds(100, 150, 195, 120);
        addStockItem.setBackground(UIManager.getColor("Button.disabledForeground"));
        addStockItem.setForeground(Color.WHITE);
        addStockItem.setBackground(new Color(56, 100, 100));
        addStockItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show a confirmation dialog box with "Yes" and "No" options
                int result = JOptionPane.showConfirmDialog(contentPane, "Add new item?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // The user clicked "Yes", so show a dialog box to enter the item details
                    JTextField itemNameField = new JTextField();
                    JTextField skuNumberField = new JTextField();
                    JTextField amountField = new JTextField();
                    Object[] fields = {
                            "Item Name:", itemNameField,
                            "SKU Number:", skuNumberField,
                            "Amount:", amountField
                    };
                    int input = JOptionPane.showConfirmDialog(contentPane, fields, "Enter item details", JOptionPane.OK_CANCEL_OPTION);

                    if (input == JOptionPane.OK_OPTION) {
                        // The user entered item details and clicked "OK", so process the input here
                        String itemName = itemNameField.getText();
                        String skuNumber = skuNumberField.getText();
                        int amount = Integer.parseInt(amountField.getText());

                        // Add the item details to the "product_with_amounts" table
                        try {

                            PreparedStatement statement = Connect_to_DB.getConnection().prepareStatement("INSERT INTO products_with_amounts (SKU, product_name, amount) VALUES (?, ?, ?)");
                            statement.setString(1, itemName);
                            statement.setString(2, skuNumber);
                            statement.setInt(3, amount);
                            statement.executeUpdate();

                            PreparedStatement statement1 = Connect_to_DB.getConnection().prepareStatement("INSERT INTO stock_inventory(SKU, product_name) VALUES (?, ?)");
                            statement1.setString(1, itemName);
                            statement1.setString(2, skuNumber);
                            statement1.executeUpdate();

                            // Reload the data for the "All Items" table
                            String q = "SELECT * FROM stock_inventory";
                            String q1 = "SELECT * FROM products_with_amounts";
                            refreshTableData(table1, statement1, q);
                            refreshTableData(table2, statement, q1);

                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());

                        }
                    }
                }
            }
        });
        contentPane.add(addStockItem);

        // Add an action listener to the register button
        backButton = new JButton("Previous Page");
        backButton.setBounds(10, 450, 150, 50);
        backButton.setBackground(UIManager.getColor("Button.disabledForeground"));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(56, 100, 100));
        backButton.setFont(new Font("Arial", Font.TYPE1_FONT, 10));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int choice = JOptionPane.showOptionDialog(null, "Go to Previous Page?", "Confirm",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Previous Page", "Exit"}, null);

                if (choice == 0) {
                    // Create a new instance of the sign in window
                    Db_Home_Page backToHome = new Db_Home_Page(user);
                    backToHome.setVisible(true);
                    dispose(); // Close the current registration window
                } else if (choice == 1) {
                    System.exit(0);
                }
            }
        });
        contentPane.add(backButton);
    }

    public void refreshTableData(DefaultTableModel tableModel, PreparedStatement statement, String q) {
        try {
            // Execute the query to retrieve the data
            ResultSet rs = statement.executeQuery(q);

            // Check if tableModel is null
            if (tableModel == null) {
                tableModel = new DefaultTableModel();
            }

            // Remove all existing rows from the table model
            tableModel.setRowCount(0);

            // Loop through the result set and add each row to the table model
            while (rs.next()) {
                Object[] row = new Object[tableModel.getColumnCount()];
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    row[i] = rs.getObject(i + 1);
                }
                tableModel.addRow(row);
            }

            // Close the result set
            rs.close();
        } catch (SQLException ex) {
            // Handle any exceptions
            ex.printStackTrace();
        }
    }
}
