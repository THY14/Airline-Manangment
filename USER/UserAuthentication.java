package USER;
import java.util.HashMap;

public interface UserAuthentication {

    // Register a user
    default boolean registerUser(String username, String password, String confirmPassword);

    // Login a user
    default boolean loginUser(String username, String password);

    // Validate password strength
    default boolean validatePassword(String password);
}
