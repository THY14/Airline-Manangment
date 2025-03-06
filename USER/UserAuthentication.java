package USER;

public interface UserAuthentication {
    boolean registerUser(String username, String password, String confirmPassword);
    boolean loginUser(String username, String password);
    boolean validatePassword(String password);
}