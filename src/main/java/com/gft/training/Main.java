package com.gft.training;

import com.gft.training.exception.NoUserException;
import com.gft.training.exception.UserValidationException;
import com.gft.training.model.User;
import com.gft.training.service.UserService;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Hello, World!");
        UserService userService = new UserService();
        userService.logMessage();

        while (true) {
            System.out.println("\n=== User Management ===");
            System.out.println("1. New user");
            System.out.println("2. Search by user ID");
            System.out.println("3. List all users");
            System.out.println("4. Delete user");
            System.out.println("5. Exit");
            System.out.print("Select your option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Read next line

            switch (option) {
                case 1:
                    addUser(userService);
                    break;
                case 2:
                    findUserById(userService);
                    break;
                case 3:
                    listAllUsers(userService);
                    break;
                case 4:
                    deleteUser(userService);
                    break;
                case 5:
                    System.out.println("\nBye!");
                    return;
                default:
                    System.out.println("\nThis option is not available");
            }
        }
    }

    private static void addUser(UserService userService) {

        System.out.print("Enter user name: ");
        String name = scanner.nextLine();

        System.out.print("Enter user age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Read next line

        try {
            userService.addUser(name, age);
        } catch (UserValidationException e) {
            // TODO Auto-generated catch block
            System.out.println("Error adding user: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\nUser successfully added: " + name + " (age: " + age + ")");
    }

    private static void findUserById(UserService userService) {
        System.out.print("Enter user id to search: ");
        int id = scanner.nextInt();

        User user = userService.getUser(id);

        System.out.println("\nUser found: " + user.toString());
    }

    private static void listAllUsers(UserService userService) {

        userService.listUsers();
    }

    private static void deleteUser(UserService userService) {
        System.out.print("Enter user id to delete: ");
        int id = scanner.nextInt();
        try {
            userService.deleteUser(id);
        } catch (NoUserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}