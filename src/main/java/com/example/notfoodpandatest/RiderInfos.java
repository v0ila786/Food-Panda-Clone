package com.example.notfoodpandatest;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class RiderInfos extends javax.swing.JFrame {

    public RiderInfos() {
        initComponents();
        setSize(1070,600);
        setResizable(false);
        setLocationRelativeTo(null);
        displayOrderInfo(); // Display the extracted order information in the table
    }

    public List<String> ExtractRiderInfo() {
        List<String> riderInfo = new ArrayList<>();
        String filePath = "/Users/amal/IdeaProjects/NotFoodpandaTest/src/main/resources/Not Foodpanda Riders.txt"; // Replace with the actual file path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line: " + line);
                String[] fields = line.split(", ");

                String name = fields[0];
                String email = fields[1];
                String phone = fields[2];
                String location = fields[3];

                // Add the extracted email, restaurant, bill, and order number to the list
                riderInfo.add(name);
                riderInfo.add(email);
                riderInfo.add(phone);
                riderInfo.add(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return riderInfo;
    }

    // Method to display the extracted order information in the table
    public void displayOrderInfo() {
        DefaultTableModel model = (DefaultTableModel) Table.getModel();
        model.setRowCount(0); // Clear the table before populating it again

        int defaultHeight = 50; // Set the default height for the table
        List<String> riderInfo = ExtractRiderInfo();

        for (int i = 0; i < riderInfo.size(); i += 4) {
            String name = riderInfo.get(i);
            String email = riderInfo.get(i + 1);
            String phone = riderInfo.get(i + 2);
            String location = riderInfo.get(i + 3);

            model.addRow(new Object[]{name, email, phone, location});

            if (i == 0) {
                jScrollPane1.setBounds(30, 130, 690, defaultHeight);
            } else {
                defaultHeight += 30;
                jScrollPane1.setBounds(30, 130, 690, defaultHeight);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // Generated Code
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        Table.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Name", "Email", "Phone Number", "Location"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.setRowHeight(30);
        jScrollPane1.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(0).setResizable(false);
            Table.getColumnModel().getColumn(1).setResizable(false);
            Table.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 130, 690, 50);


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 450, 290, 150);
        jPanel1.setBackground(new Color(42,42,42));

        jLabel1.setIcon(new javax.swing.ImageIcon("/Users/amal/Downloads/pastOrdersPage.png")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-4, 0, 1070, 602);

        BackButton.setText("jButton1");
        getContentPane().add(BackButton);
        BackButton.setBounds(839, 30, 200, 80);
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                adminpanel AdminPanel = new adminpanel();
                AdminPanel.setVisible(true);
            }
        });

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RiderInfo riderInfo = new RiderInfo();
                riderInfo.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BackButton;
    private javax.swing.JTable Table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration
}