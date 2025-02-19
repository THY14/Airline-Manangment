package USER;
import java.util.HashMap;

public interface UserAuthentication {
    // HashMap to store users
    static HashMap<String, String> users = new HashMap<>();

    // Register a user
    default boolean registerUser(String username, String password, String confirmPassword) {
        // Check if the passwords match
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return false; // Registration failed
        }
        // Validate password strength
        if (!validatePassword(password)) {
            System.out.println("Password does not meet strength requirements.");
            return false; // Registration failed
        }

        // Check if the username already exists
        if (users.containsKey(username)) {
            System.out.println("User already exists.");
            return false; // Registration failed
        } else {
            users.put(username, password);
            System.out.println("Registration successful.");
            return true; // Registration successful
        }
    }

    // Login a user
    default boolean loginUser(String username, String password) {
        // Check if the user exists and password matches
        if (!users.containsKey(username)) {
            System.out.println("Invalid username.");
            return false; // Invalid username
        } else {
            if (users.get(username).equals(password)) {
                System.out.println("Login successful.");
                return true; // Login successful
            } else {
                System.out.println("Invalid password.");
                return false; // Incorrect password
            }
        }
    }

    // Validate password strength
    default boolean validatePassword(String password) {
        // Simple password validation: At least 8 characters long, contains numbers and special characters
        if (password.length() < 8) {
            return false; // Password too short
        }

        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }

        return hasDigit && hasSpecialChar; 
    }
}
