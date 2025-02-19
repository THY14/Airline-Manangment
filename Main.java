package USER;

import java.util.Scanner;

import USER.Passenger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating a Passenger object
        Passenger passenger1 = new Passenger("A12345678", "mypassword123", 101, 
                                             "John", "Doe", "555-1234", "john.doe@example.com", 
                                             "Male", "USA", "1990-01-01");

        System.out.println("Welcome to the Airline Management System");
        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Access Personal Information");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Registration
                    System.out.print("Enter a username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter a password: ");
                    String regPassword = scanner.nextLine();
                    passenger1.registerUser(regUsername, regPassword);
                    break;

                case 2:
                    // Login
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    if (passenger1.loginUser(loginUsername, loginPassword)) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                    break;

                case 3:
                    // Access protected information
                    System.out.print("Enter your password to access details: ");
                    String accessPassword = scanner.nextLine();
                    
                    System.out.println("\nPassenger Details:");
                    System.out.println("Full Name: " + passenger1.getFirstName(accessPassword) + " " + passenger1.getLastName(accessPassword));
                    System.out.println("Phone: " + passenger1.getTel(accessPassword));
                    System.out.println("Email: " + passenger1.getEmail(accessPassword));
                    System.out.println("Passport Number: " + passenger1.getPassportNumbers(accessPassword));
                    break;

                case 4:
                    System.out.println("Exiting system. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
