package USER;
public abstract class Person implements UserAuthentication{ //Main class
    protected static int id = 0;
    protected String firstname;
    protected String lastname;
    protected String tel;
    protected String email;
    protected String gender;
    protected String nationality;
    protected String dob;
    protected String username;
    protected String password;
    
    //Register
    public Person(String firstname, String lastname, String tel, String email, String gender, String nationality, String dob,  String username, String password) {
        id = id + 1;
        this.firstname = firstname;
        this.lastname = lastname;
        this.tel = tel;
        this.email = email;
        this.gender = gender;
        this.nationality = nationality;
        this.dob = dob;
        this.username = username;
        this.password = password;
    }

    //Login
    public Person(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean registerUser(String username, String password, String confirmPassword) {
        // Check if the passwords match
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return false;  // Registration failed
        }

        // Validate password strength
        if (!validatePassword(password)) {
            System.out.println("Password does not meet strength requirements.");
            return false;  // Registration failed
        }

        // Store the username and password for later login attempts
        this.username = username;
        this.password = password;
        System.out.println("Registration successful.");
        return true;  // Registration successful
    }

    @Override
    public boolean loginUser(String username, String password) {
        // Check if the username matches and if the password is correct
        if (!this.username.equals(username)) {
            System.out.println("Invalid username.");
            return false;  // Invalid username
        } else {
            if (this.password.equals(password)) {
                System.out.println("Login successful.");
                return true;  // Login successful
            } else {
                System.out.println("Invalid password.");
                return false;  // Incorrect password
            }
        }
    }

    @Override
    public boolean validatePassword(String password) {
        if (password.length() < 8) {
            System.out.println("Password too short. Must be at least 8 characters.");
            return false; 
        } else if (!password.matches(".*\\d.*")) {
            System.out.println("Password must contain at least one digit.");
            return false;
        } else if (!password.matches(".*[^a-zA-Z0-9].*")) {
            System.out.println("Password must contain at least one special character (non-alphanumeric).");
            return false;
        } else if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*")) {
            System.out.println("Password should contain both uppercase and lowercase letters.");
        }
    
        System.out.println("Password available");
        return true; 
    }

}

