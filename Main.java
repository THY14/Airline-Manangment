import USER.ConcretePassenger;
import USER.Employee;
import USER.Person;
import USER.Flight;
import USER.Booking;
import USER.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Person loggedInUser = null;
    private static String currentPassword = null;
    private static String currentUsername = null;

    public static void main(String[] args) {
        while (true) {
            if (loggedInUser == null) {
                displayLoginMenu();
                int choice = getUserChoice();
                try {
                    switch (choice) {
                        case 1: login(); break;
                        case 2: registerPassenger(); break;
                        case 3: registerEmployee(); break;
                        case 0: System.out.println("Exiting..."); return;
                        default: System.out.println("Invalid choice. Try again.");
                    }
                } catch (SQLException e) {
                    System.err.println("Database error: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.err.println("Input error: " + e.getMessage());
                }
            } else if ("PASSENGER".equals(loggedInUser.getRole())) {
                passengerMenu();
            } else if ("EMPLOYEE".equals(loggedInUser.getRole())) {
                employeeMenu();
            }
        }
    }

    // Login and Initial Menu
    private static void displayLoginMenu() {
        System.out.println("\n=== Airline Management System ===");
        System.out.println("1. Login");
        System.out.println("2. Register as Passenger");
        System.out.println("3. Register as Employee");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void login() throws SQLException {
        System.out.print("Enter email: "); String email = scanner.nextLine();
        System.out.print("Enter password: "); String password = scanner.nextLine();

        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                String username = rs.getString("username");
                if ("PASSENGER".equals(role)) {
                    loggedInUser = new ConcretePassenger(
                            rs.getString("passport_number"), rs.getString("firstname"), rs.getString("lastname"),
                            rs.getString("tel"), email, rs.getString("gender"), rs.getString("nationality"),
                            rs.getString("dob"), username, password);
                } else if ("EMPLOYEE".equals(role)) {
                    loggedInUser = new Employee(
                            "Unknown", "Unknown", "Unknown", 0, rs.getString("firstname"),
                            rs.getString("lastname"), rs.getString("tel"), email, rs.getString("gender"),
                            rs.getString("nationality"), rs.getString("dob"), username, password);
                }
                currentPassword = password;
                currentUsername = username;
                System.out.println("Logged in as " + role + ": " + loggedInUser.getFirstname() + " " + loggedInUser.getLastname());
            } else {
                System.out.println("Invalid credentials.");
            }
        }
    }

    private static void registerPassenger() throws SQLException {
        System.out.print("Enter passport number: "); String passport = scanner.nextLine();
        System.out.print("Enter first name: "); String fname = scanner.nextLine();
        System.out.print("Enter last name: "); String lname = scanner.nextLine();
        System.out.print("Enter phone (10 digits): "); String tel = scanner.nextLine();
        System.out.print("Enter email: "); String email = scanner.nextLine();
        System.out.print("Enter gender (Male/Female/Other): "); String gender = scanner.nextLine();
        System.out.print("Enter nationality: "); String nationality = scanner.nextLine();
        System.out.print("Enter date of birth (YYYY-MM-DD): "); String dob = scanner.nextLine();
        System.out.print("Enter username: "); String username = scanner.nextLine();
        System.out.print("Enter password: "); String password = scanner.nextLine();

        ConcretePassenger passenger = new ConcretePassenger(passport, fname, lname, tel, email, gender, nationality, dob, username, password);
        passenger.saveToDatabase();
        System.out.println("Passenger registered successfully! Please log in.");
    }

    private static void registerEmployee() throws SQLException {
        System.out.print("Enter department: "); String dept = scanner.nextLine();
        System.out.print("Enter hire date (YYYY-MM-DD): "); String hireDate = scanner.nextLine();
        System.out.print("Enter position: "); String position = scanner.nextLine();
        System.out.print("Enter salary: "); double salary = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter first name: "); String fname = scanner.nextLine();
        System.out.print("Enter last name: "); String lname = scanner.nextLine();
        System.out.print("Enter phone (10 digits): "); String tel = scanner.nextLine();
        System.out.print("Enter email: "); String email = scanner.nextLine();
        System.out.print("Enter gender (Male/Female/Other): "); String gender = scanner.nextLine();
        System.out.print("Enter nationality: "); String nationality = scanner.nextLine();
        System.out.print("Enter date of birth (YYYY-MM-DD): "); String dob = scanner.nextLine();
        System.out.print("Enter username: "); String username = scanner.nextLine();
        System.out.print("Enter password: "); String password = scanner.nextLine();

        Employee employee = new Employee(dept, hireDate, position, salary, fname, lname, tel, email, gender, nationality, dob, username, password);
        employee.saveToDatabase();
        System.out.println("Employee registered successfully! Please log in.");
    }

    // Passenger Menu and Functions
    private static void passengerMenu() {
        System.out.println("\n=== Passenger Menu ===");
        System.out.println("1. View Profile");
        System.out.println("2. Update Profile");
        System.out.println("3. Book a Flight");
        System.out.println("4. View My Bookings");
        System.out.println("5. Delete Account");
        System.out.println("6. Logout");
        System.out.print("Enter your choice: ");
        int choice = getUserChoice();
        try {
            switch (choice) {
                case 1: viewPassengerProfile(); break;
                case 2: updatePassengerProfile(); break;
                case 3: bookFlight(); break;
                case 4: viewBookings(); break;
                case 5: deleteAccount(); break;
                case 6: loggedInUser = null; currentPassword = null; currentUsername = null; System.out.println("Logged out."); break;
                default: System.out.println("Invalid choice.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (IllegalArgumentException | SecurityException e) {
            System.err.println("Input error: " + e.getMessage());
        }
    }

    private static void viewPassengerProfile() {
        ConcretePassenger passenger = (ConcretePassenger) loggedInUser;
        System.out.println("Passenger ID: " + passenger.getId());
        System.out.println("Name: " + passenger.getFirstname() + " " + passenger.getLastname());
        System.out.println("Phone: " + passenger.getTel());
        System.out.println("Email: " + passenger.getEmail());
        System.out.println("Gender: " + passenger.getGender());
        System.out.println("Nationality: " + passenger.getNationality());
        System.out.println("Date of Birth: " + passenger.getDob());
        System.out.println("Username: " + currentUsername);
        System.out.print("Enter your password to view passport number: "); String pass = scanner.nextLine();
        try {
            System.out.println("Passport Number: " + passenger.getPassportNumbers(pass));
        } catch (SecurityException e) {
            System.out.println("Incorrect password. Passport hidden.");
        }
    }

    private static void updatePassengerProfile() throws SQLException {
        ConcretePassenger passenger = (ConcretePassenger) loggedInUser;
        System.out.print("Enter current password to proceed: "); String inputPass = scanner.nextLine();
        if (!passenger.loginUser(currentUsername, inputPass)) {
            System.out.println("Incorrect password. Update cancelled.");
            return;
        }

        System.out.print("Enter new phone (10 digits, or press Enter to skip): "); String tel = scanner.nextLine();
        System.out.print("Enter new email (or press Enter to skip): "); String email = scanner.nextLine();
        System.out.print("Enter new passport number (or press Enter to skip): "); String passport = scanner.nextLine();
        System.out.print("Enter new password (or press Enter to skip): "); String newPass = scanner.nextLine();

        String newTel = !tel.isEmpty() ? tel : passenger.getTel();
        String newEmail = !email.isEmpty() ? email : passenger.getEmail();
        String newPassport = !passport.isEmpty() ? passport : passenger.getPassportNumbers(inputPass);
        String finalPass = !newPass.isEmpty() ? newPass : inputPass;

        if (!newPass.isEmpty()) {
            System.out.print("Confirm new password: "); String confirmPass = scanner.nextLine();
            passenger.setPassword(inputPass, newPass, confirmPass);
        }

        loggedInUser = new ConcretePassenger(newPassport, passenger.getFirstname(), passenger.getLastname(),
                newTel, newEmail, passenger.getGender(), passenger.getNationality(), passenger.getDob(),
                currentUsername, finalPass);
        loggedInUser.saveToDatabase();
        currentPassword = finalPass;
        System.out.println("Profile updated successfully!");
    }

    private static void bookFlight() throws SQLException {
        System.out.print("Enter origin: "); String origin = scanner.nextLine();
        System.out.print("Enter destination: "); String dest = scanner.nextLine();
        System.out.print("Enter departure date (YYYY-MM-DD): "); LocalDate depart = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter number of passengers: "); int numPass = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter seat class (Economy/Business): "); String seatClass = scanner.nextLine();

        double basePrice = 150.0; // System-set price per passenger (you can adjust this)
        double totalPayment = numPass * basePrice; // Calculate total payment
        System.out.println("Price per passenger: $" + basePrice);
        System.out.println("Total payment for " + numPass + " passengers: $" + totalPayment);

        Booking booking = new Booking(origin, dest, depart, numPass, seatClass, basePrice, loggedInUser.getId());
        System.out.println("Available Flights (Enter IDs to add, 'done' to finish):");
        displayAllFlights();
        System.out.print("Add flight ID: "); String flightId = scanner.nextLine();
        while (!flightId.equalsIgnoreCase("done")) {
            booking.addFlightID(flightId);
            System.out.print("Add another flight ID (or 'done'): "); flightId = scanner.nextLine();
        }
        booking.saveToDatabase();
        System.out.println("Flight booked successfully! Total payment: $" + totalPayment);
    }

    private static void viewBookings() throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM bookings WHERE passenger_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, loggedInUser.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Booking ID: " + rs.getInt("booking_id") + ", Origin: " + rs.getString("origin") +
                        ", Destination: " + rs.getString("destination") + ", Depart: " + rs.getDate("depart_date") +
                        ", Passengers: " + rs.getInt("num_passengers") + ", Class: " + rs.getString("seat_class") +
                        ", Total Price: $" + rs.getDouble("total_price"));
            }
        }
    }

    // Employee Menu and Functions
    private static void employeeMenu() {
        System.out.println("\n=== Employee Menu ===");
        System.out.println("1. View Profile");
        System.out.println("2. Update Profile");
        System.out.println("3. View All Users");
        System.out.println("4. Delete User by ID");
        System.out.println("5. Add Flight");
        System.out.println("6. Logout");
        System.out.print("Enter your choice: ");
        int choice = getUserChoice();
        try {
            switch (choice) {
                case 1: viewEmployeeProfile(); break;
                case 2: updateEmployeeProfile(); break;
                case 3: viewAllUsers(); break;
                case 4: deleteUserById(); break;
                case 5: addFlight(); break;
                case 6: loggedInUser = null; currentPassword = null; currentUsername = null; System.out.println("Logged out."); break;
                default: System.out.println("Invalid choice.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (IllegalArgumentException | SecurityException e) {
            System.err.println("Input error: " + e.getMessage());
        }
    }

    private static void viewEmployeeProfile() {
        Employee employee = (Employee) loggedInUser;
        System.out.println(employee.toString());
    }

    private static void updateEmployeeProfile() throws SQLException {
        Employee employee = (Employee) loggedInUser;
        System.out.print("Enter current password to proceed: "); String inputPass = scanner.nextLine();
        if (!employee.loginUser(currentUsername, inputPass)) {
            System.out.println("Incorrect password. Update cancelled.");
            return;
        }

        System.out.print("Enter new department (or press Enter to skip): "); String dept = scanner.nextLine();
        System.out.print("Enter new position (or press Enter to skip): "); String position = scanner.nextLine();
        System.out.print("Enter new salary (or press Enter to skip): "); String salaryStr = scanner.nextLine();
        System.out.print("Enter new phone (10 digits, or press Enter to skip): "); String tel = scanner.nextLine();
        System.out.print("Enter new email (or press Enter to skip): "); String email = scanner.nextLine();
        System.out.print("Enter new password (or press Enter to skip): "); String newPass = scanner.nextLine();

        if (!dept.isEmpty()) employee.setDepartment(dept);
        if (!position.isEmpty()) employee.setPosition(position);
        if (!salaryStr.isEmpty()) employee.setSalary(Double.parseDouble(salaryStr));
        String newTel = !tel.isEmpty() ? tel : employee.getTel();
        String newEmail = !email.isEmpty() ? email : employee.getEmail();
        String finalPass = !newPass.isEmpty() ? newPass : inputPass;

        if (!newPass.isEmpty()) {
            System.out.print("Confirm new password: "); String confirmPass = scanner.nextLine();
            employee.setPassword(inputPass, newPass, confirmPass);
        }

        loggedInUser = new Employee(employee.getDepartment(), employee.getHireDate(), employee.getPosition(),
                employee.getSalary(), employee.getFirstname(), employee.getLastname(), newTel, newEmail,
                employee.getGender(), employee.getNationality(), employee.getDob(), currentUsername, finalPass);
        loggedInUser.saveToDatabase();
        currentPassword = finalPass;
        System.out.println("Profile updated successfully!");
    }

    private static void viewAllUsers() throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "SELECT id, firstname, lastname, role FROM users";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("firstname") + " " +
                        rs.getString("lastname") + ", Role: " + rs.getString("role"));
            }
        }
    }

    private static void deleteUserById() throws SQLException {
        System.out.print("Enter user ID to delete: "); int id = Integer.parseInt(scanner.nextLine());
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "User deleted successfully!" : "User not found.");
        }
    }

    private static void addFlight() throws SQLException {
        System.out.print("Enter flight ID: "); String flightId = scanner.nextLine();
        System.out.print("Enter airplane ID: "); String airplaneId = scanner.nextLine();
        System.out.print("Enter departure location: "); String depLoc = scanner.nextLine();
        System.out.print("Enter arrival location: "); String arrLoc = scanner.nextLine();
        System.out.print("Enter departure time: "); String depTime = scanner.nextLine();
        System.out.print("Enter arrival time: "); String arrTime = scanner.nextLine();
        System.out.print("Enter booked economy seats: "); int econ = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter booked business seats: "); int bus = Integer.parseInt(scanner.nextLine());
        System.out.print("Is delayed? (true/false): "); boolean delayed = Boolean.parseBoolean(scanner.nextLine());

        Flight flight = new Flight(bus, econ, delayed, new ArrayList<>(), airplaneId, arrLoc, arrTime, depLoc, depTime, flightId);
        flight.saveToDatabase();
        System.out.println("Flight added successfully!");
    }

    private static void deleteAccount() throws SQLException {
        System.out.print("Enter your password to confirm account deletion: "); String password = scanner.nextLine();
        if (loggedInUser.loginUser(currentUsername, password)) {
            try (Connection conn = DatabaseUtil.getConnection()) {
                String sql = "DELETE FROM users WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, loggedInUser.getId());
                pstmt.executeUpdate();
                System.out.println("Account deleted successfully!");
                loggedInUser = null;
                currentPassword = null;
                currentUsername = null;
            }
        } else {
            System.out.println("Incorrect password. Deletion cancelled.");
        }
    }

    private static void displayAllFlights() throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM flights";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Flight ID: " + rs.getString("flight_id") + ", Airplane: " + rs.getString("airplane_id") +
                        ", From: " + rs.getString("departure_location") + " at " + rs.getString("departure_time") +
                        ", To: " + rs.getString("arrival_location") + " at " + rs.getString("arrival_time") +
                        ", Delayed: " + rs.getBoolean("delayed"));
            }
        }
    }
}