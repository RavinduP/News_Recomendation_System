package org.example.ood_cw.utils;

public class TestConnection {
    public static void main(String[] args) {
        try {
            DatabaseConnection.getConnection();
            System.out.println("Connection Successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
