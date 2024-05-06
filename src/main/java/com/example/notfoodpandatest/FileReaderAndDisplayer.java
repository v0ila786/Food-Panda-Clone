package com.example.notfoodpandatest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class FileReaderAndDisplayer {
    public static void main(String[] args) {

        //Read Rider File
        URL RiderFileURL = FileReaderAndDisplayer.class.getResource("/Not Foodpanda Riders.txt");
        if (RiderFileURL != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(RiderFileURL.openStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n");

        //Read User File
        URL UserFileURL = FileReaderAndDisplayer.class.getResource("/Not Foodpanda Users.txt");
        if (UserFileURL != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(UserFileURL.openStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n");
        //Read Order History File
        URL OrderHistoryFileURL = FileReaderAndDisplayer.class.getResource("/Order History.txt");
        if (OrderHistoryFileURL != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(OrderHistoryFileURL.openStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFirstName(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            if (line != null) {
                String[] userDetails = line.split(" ");
                return userDetails[0];
            } else {
                System.out.println("File is empty.");
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return null;
    }
}





