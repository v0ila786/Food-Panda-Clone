package com.example.notfoodpandatest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

//USER PAST ORDERS
public class PastOrders extends javax.swing.JFrame {
    private javax.swing.JTable jTable1;
    private DefaultTableModel model;


    public PastOrders() {
        initComponents();
        setSize(1070, 610);
        setResizable(false);
        setLocationRelativeTo(null);
        displayOrderInfo(); // Display the extracted order information in the table
    }

    // Method to extract order info from the text file
    public List<String> extractOrderInfo() {
        List<String> orderInfo = new ArrayList<>();
        String filePath = "/Users/amal/IdeaProjects/NotFoodpandaTest/src/main/resources/Order History.txt"; // Replace with the actual file path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line: " + line);
                String[] fields = line.split(", ");

                String email = fields[0];
                String restaurant = fields[1];
                String bill = fields[3];
                String orderNo = fields[4];

                // Add the extracted email, restaurant, bill, and order number to the list
                orderInfo.add(email);
                orderInfo.add(restaurant);
                orderInfo.add(bill);
                orderInfo.add(orderNo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return orderInfo;
    }

    // Method to display the extracted order information in the table
    public void displayOrderInfo() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear the table before populating it again

        int defaultHeight = 50; // Set the default height for the table
        List<String> orderInfo = extractOrderInfo();

        for (int i = 0; i < orderInfo.size(); i += 4) {
            String email = orderInfo.get(i);
            String restaurant = orderInfo.get(i + 1);
            String bill = orderInfo.get(i + 2);
            String orderNo = orderInfo.get(i + 3);

            model.addRow(new Object[]{restaurant, bill, orderNo});

            if (i == 0) {
                jScrollPane1.setBounds(30, 130, 690, defaultHeight);
            } else {
                defaultHeight += 30;
                jScrollPane1.setBounds(30, 130, 690, defaultHeight);
            }
        }
    }

    // Generated Code
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        complaintsTextBox = new javax.swing.JTextField();
        //private javax.swing.JTextField complaintsTextBox;
        jLabel1 = new javax.swing.JLabel();

        submitButton1 = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RestaurantsPage restaurantsPage = new RestaurantsPage();
                restaurantsPage.setVisible(true);
            }
        });
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        submitButton1 .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the user's complaint from the text area
                String complaint = complaintsTextBox.getText();

                // Append the complaint to the text file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("complaints.txt", true))) {
                    writer.write(complaint);
                    writer.newLine();
                    writer.flush();
                    JOptionPane.showMessageDialog(null, "Complaint submitted successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    // Clear the text area after submission
                    complaintsTextBox.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to submit complaint.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jTable1.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.GreyInline"));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String [] {
                        "Restaurant", "Bill", "Order Number"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 140, 620, 280);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Past Orders.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1090, 610);
        getContentPane().add(submitButton1);
        submitButton1.setBounds(30, 520, 250, 60);
        getContentPane().add(BackButton);
        BackButton.setBounds(830, 40, 220, 80);

        getContentPane().add(complaintsTextBox);
        complaintsTextBox.setBounds(30, 470, 250, 30);
        pack();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Set look and feel to the system's default
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                PastOrders pastOrders = new PastOrders();
                pastOrders.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify
    private javax.swing.JButton BackButton;
    private javax.swing.JTextField complaintsTextBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton submitButton1;
    // End of variables declaration
}
