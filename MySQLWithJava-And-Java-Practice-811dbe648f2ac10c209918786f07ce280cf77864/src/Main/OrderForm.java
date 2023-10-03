package Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class OrderForm extends JFrame {
    private JComboBox<String> itemDropdown = new JComboBox<>();
    private JTextField amountField = new JTextField(20);
    private JTable itemsTable = new JTable(new DefaultTableModel(new Object[]{"Product SKU", "Product Name", "Amount Ordered"}, 0));
    private JButton addItemButton = new JButton("Add Item");
    private JTextField invoiceField = new JTextField(20);
    private JTextField storeIdField = new JTextField(20);
    private JTextField salesmanField = new JTextField(20);
    private JButton submitButton = new JButton("Submit");
    private JLabel totalItemsLabel = new JLabel("Total items for order: ");
    private int totalItems = 0;

    private JButton previousPageButton = new JButton("Previous Page");
    public OrderForm(String user) {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Invoice #"));
        add(invoiceField);
        add(new JLabel("Salesman"));
        add(salesmanField);
        add(new JLabel("Store ID"));
        add(storeIdField);

        loadItemsFromDatabase();

        add(new JLabel("Item"));
        add(itemDropdown);
        add(new JLabel("Amount"));
        add(amountField);

        add(new JScrollPane(itemsTable));
        add(addItemButton);
        add(totalItemsLabel);
        add(submitButton);

        add(new JLabel("View Invoice"));
        JTextField viewInvoiceField = new JTextField(20);
        add(viewInvoiceField);
        JButton viewInvoiceButton = new JButton("View Invoice");
        add(viewInvoiceButton);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sku = (String) itemDropdown.getSelectedItem();
                String amount = amountField.getText();

                // Fetch the product name based on the SKU from the stock_inventory table
                try {
                    Connection connection = Connect_to_DB.getConnection();
                    String productName = fetchProductName(connection, sku);

                    // Add the new item to the table
                    ((DefaultTableModel) itemsTable.getModel()).addRow(new Object[]{sku, productName, amount});

                    // Close the connection
                    connection.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred while fetching the product name.");
                    ex.printStackTrace();
                }

                // Update the totalItems count and label
                totalItems++;
                totalItemsLabel.setText("Total items for order: " + totalItems);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                try {
                    connection = Connect_to_DB.getConnection();

                    // Disable auto-commit mode
                    connection.setAutoCommit(false);

                    PreparedStatement orderStatement = connection.prepareStatement(
                            "INSERT INTO orders (invoice_id, store_id, salesman) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                    );
                    orderStatement.setInt(1, Integer.parseInt(invoiceField.getText()));
                    orderStatement.setInt(2, Integer.parseInt(storeIdField.getText()));
                    orderStatement.setString(3, salesmanField.getText());
                    orderStatement.executeUpdate();

                    ResultSet generatedKeys = orderStatement.getGeneratedKeys();
                    int invoiceId = 0;
                    if (generatedKeys.next()) {
                        invoiceId = generatedKeys.getInt(1);
                    }

                    PreparedStatement orderItemsStatement = connection.prepareStatement(
                            "INSERT INTO order_items (invoice_id, product_sku, product_description, amount_ordered) VALUES (?, ?, ?, ?)"
                    );

                    for (int i = 0; i < itemsTable.getRowCount(); i++) {
                        String sku = (String) itemsTable.getValueAt(i, 0);
                        String productName = fetchProductName(connection, sku); // You'll need to fetch the product name based on the SKU from the stock_inventory table
                        String amountString = (String) itemsTable.getValueAt(i, 2);

                        int amountOrdered = 0;
                        if (amountString != null && !amountString.isEmpty()) {
                            amountOrdered = Integer.parseInt(amountString);
                        }

                        orderItemsStatement.setInt(1, invoiceId);
                        orderItemsStatement.setString(2, sku);
                        orderItemsStatement.setString(3, productName);
                        orderItemsStatement.setInt(4, amountOrdered);
                        orderItemsStatement.executeUpdate();
                    }

                    // Commit the transaction
                    connection.commit();

                    // Display the items from the order in the database
                    PreparedStatement displayStatement = connection.prepareStatement(
                            "SELECT * FROM invoices WHERE invoice_id = ?"
                    );
                    displayStatement.setInt(1, invoiceId);
                    ResultSet resultSet = displayStatement.executeQuery();

                    // Create a new window to display the items
                    JFrame displayFrame = new JFrame("Items for Order #" + invoiceId);

                    // Create the table model with the updated data
                    DefaultTableModel displayTableModel = buildTableModel(resultSet);

                    // Create the table using the new table model
                    JTable displayTable = new JTable(displayTableModel);

                    // Add the table to the frame
                    displayFrame.add(new JScrollPane(displayTable));

                    // Set up the frame and make it visible
                    displayFrame.pack();
                    displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    displayFrame.setVisible(true);

                    JOptionPane.showMessageDialog(null, "Order saved successfully.");
                    ((DefaultTableModel) itemsTable.getModel()).setRowCount(0);
                    invoiceField.setText("");
                    storeIdField.setText("");
                    salesmanField.setText("");

                    // Reset the totalItems count and label
                    totalItems = 0;
                    totalItemsLabel.setText("Total items for order: " + totalItems);
                } catch (SQLException ex) {
                    if (connection != null) {
                        try {
                            // If there is an error then rollback the changes.
                            connection.rollback();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    JOptionPane.showMessageDialog(null, "An error occurred while saving the order.");
                    ex.printStackTrace();
                } finally {
                    if (connection != null) {
                        try {
                            // Re-enable auto-commit mode
                            connection.setAutoCommit(true);
                            // Close the connection
                            connection.close();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });


        viewInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = Connect_to_DB.getConnection();
                    displayInvoice(connection, Integer.parseInt(viewInvoiceField.getText()));
                    connection.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred while loading the invoice.");
                    ex.printStackTrace();
                }
            }
        });

        //previous Page button
        add(previousPageButton);
        previousPageButton.addActionListener(new ActionListener() {
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
    }


    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // Get column names
        int columnCount = metaData.getColumnCount();
        Vector<String> columnNames = new Vector<>();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // Get table data
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            for (int column = 1; column <= columnCount; column++) {
                row.add(rs.getObject(column));
            }
            data.add(row);
        }

        // Create a copy of the data before closing the ResultSet
        List<Object[]> rowData = new ArrayList<>();
        for (Vector<Object> row : data) {
            rowData.add(row.toArray(new Object[0]));
        }

        // Close the ResultSet after retrieving data

        // Create the DefaultTableModel using the copied data
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (Object[] row : rowData) {
            tableModel.addRow(row);
        }

        return tableModel;
    }

    private void displayInvoice(Connection connection, int invoiceId) throws SQLException {
        PreparedStatement displayStatement = connection.prepareStatement(
                "SELECT * FROM order_items WHERE invoice_id = ?"
        );
        displayStatement.setInt(1, invoiceId);
        ResultSet resultSet = displayStatement.executeQuery();

        if (resultSet.isBeforeFirst()) {  // Changed from resultSet.next()
            // Create a new window to display the items
            JFrame displayFrame = new JFrame("Items for Invoice #" + invoiceId);

            // Create the table model with the updated data
            DefaultTableModel displayTableModel = buildTableModel(resultSet);

            // Create the table using the new table model
            JTable displayTable = new JTable(displayTableModel);

            // Add the table to the frame
            displayFrame.add(new JScrollPane(displayTable));

            // Set up the frame and make it visible
            displayFrame.pack();
            displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            displayFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No invoice found with the provided ID.");
        }
    }



    private String fetchProductName(Connection connection, String sku) throws SQLException {
        String productName = "";
        PreparedStatement statement = connection.prepareStatement("SELECT product_name FROM stock_inventory WHERE sku = ?");
        statement.setString(1, sku);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            productName = resultSet.getString("product_name");
        }
        return productName;
    }

    private void loadItemsFromDatabase() {
        try {
            Connection connection = Connect_to_DB.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT sku, product_name FROM stock_inventory");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                itemDropdown.addItem(resultSet.getString("sku") + " - " + resultSet.getString("product_name"));
            }

            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "An error occurred while loading items.");
            ex.printStackTrace();
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OrderForm("User1").setVisible(true);
            }
        });
    }
}