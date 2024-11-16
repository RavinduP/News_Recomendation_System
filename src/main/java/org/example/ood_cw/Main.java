package org.example.ood_cw;

import org.example.ood_cw.services.userservice;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        userservice userService = new userservice();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to the News Recommendation System!");

        while (!exit) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("3. test");
            System.out.print("Enter your choice: ");


            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Register a new account:");
                    System.out.print("Enter UserId: ");
                    String userId = scanner.nextLine();
                    System.out.print("Enter UserName: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();

                    String registerMessage = userService.registerUser(userId, userName, password);
                    System.out.println(registerMessage);
                    break;

                case 2:
                    System.out.println("Login to your account:");
                    System.out.print("Enter UserId: ");
                    String loginUserId = scanner.nextLine();
                    System.out.print("Enter UserName: ");
                    String loginUserName = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String loginPassword = scanner.nextLine();

                    boolean isLoggedIn = userService.loginUser(loginUserId, loginUserName, loginPassword);
                    if (isLoggedIn) {
                        System.out.println("Login successful! Welcome back, " + loginUserName);
                    } else {
                        System.out.println("Login failed! Please check your credentials.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
