package USER;

public interface UserAuthentication {

    // Register a user
    boolean registerUser(String username, String password, String confirmPassword);

    // Login a user
    boolean loginUser(String username, String password);

    // Validate password strength
    boolean validatePassword(String password);
}
