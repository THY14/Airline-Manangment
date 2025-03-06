package USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Person implements UserAuthentication {
    protected final int id;
    protected String firstname;
    protected String lastname;
    protected String tel;
    protected String email;
    protected String gender;
    protected String nationality;
    protected String dob;
    protected String username;
    protected String password;
    protected String role;
    private static int idCounter = 0;  // Ensure initialized to 0

    public Person(String firstname, String lastname, String tel, String email, String gender, String nationality, String dob, String username, String password, String role) {
        validateFullInputs(firstname, lastname, tel, email, gender, nationality, dob, username, password, role);
        this.id = ensureValidId(++idCounter);  // Ensure a valid, unique ID
        this.firstname = firstname;
        this.lastname = lastname;
        this.tel = tel;
        this.email = email;
        this.gender = gender;
        this.nationality = nationality;
        this.dob = dob;
        this.username = username;
        this.password = password;
        this.role = role;
        System.out.println("Created Person with ID: " + this.id + ", Username: " + username + ", Role: " + role);  // Debug
    }

    public Person(String email, String password) {
        if (email == null || email.trim().isEmpty()) throw new IllegalArgumentException("Email cannot be null or empty");
        if (password == null || password.trim().isEmpty()) throw new IllegalArgumentException("Password cannot be null or empty");
        this.id = 0;  // Temporary ID for login-only constructor; should not be used for saving
        this.email = email;
        this.password = password;
        this.role = null;
        System.out.println("Created Person for login with Email: " + email);  // Debug
    }

    private void validateFullInputs(String firstname, String lastname, String tel, String email, String gender, String nationality, String dob, String username, String password, String role) {
        if (firstname == null || firstname.trim().isEmpty()) throw new IllegalArgumentException("First name cannot be null or empty");
        if (lastname == null || lastname.trim().isEmpty()) throw new IllegalArgumentException("Last name cannot be null or empty");
        if (tel == null || !tel.matches("\\d{10}")) throw new IllegalArgumentException("Phone number must be 10 digits");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Invalid email address");
        if (gender == null || (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female") && !gender.equalsIgnoreCase("Other"))) {
            throw new IllegalArgumentException("Gender must be Male, Female, or Other");
        }
        if (nationality == null || nationality.trim().isEmpty()) throw new IllegalArgumentException("Nationality cannot be null or empty");
        if (dob == null || !dob.matches("\\d{4}-\\d{2}-\\d{2}")) throw new IllegalArgumentException("Date of birth must be in YYYY-MM-DD format");
        if (username == null || username.trim().isEmpty()) throw new IllegalArgumentException("Username cannot be null or empty");
        if (password == null || password.trim().isEmpty()) throw new IllegalArgumentException("Password cannot be null or empty");
        if (role == null || (!role.equals("PASSENGER") && !role.equals("EMPLOYEE"))) throw new IllegalArgumentException("Role must be PASSENGER or EMPLOYEE");
    }

    @Override
    public boolean registerUser(String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) throw new IllegalArgumentException("Passwords do not match");
        if (!validatePassword(password)) throw new IllegalArgumentException("Password does not meet strength requirements");
        this.username = username;
        this.password = password;
        return true;
    }

    @Override
    public boolean loginUser(String username, String password) {
        if (this.username == null || this.password == null) return false;
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public boolean validatePassword(String password) {
        if (password.length() < 8) throw new IllegalArgumentException("Password must be at least 8 characters long");
        if (!password.matches(".*\\d.*")) throw new IllegalArgumentException("Password must contain at least one digit (0-9)");
        if (!password.matches(".*[^a-zA-Z0-9].*")) throw new IllegalArgumentException("Password must contain at least one special character (e.g., !, @, #)");
        if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*")) throw new IllegalArgumentException("Password must contain both uppercase and lowercase letters");
        return true;
    }

    public void setPassword(String oldPassword, String newPassword, String confirmNewPassword) {
        if (!this.password.equals(oldPassword)) throw new SecurityException("Incorrect old password");
        if (!newPassword.equals(confirmNewPassword)) throw new IllegalArgumentException("New passwords do not match");
        if (!validatePassword(newPassword)) throw new IllegalArgumentException("New password does not meet strength requirements");
        this.password = newPassword;
    }

    public void saveToDatabase() throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID: ID must be greater than 0 for database insertion. Current ID: " + id);
        }
        System.out.println("Saving Person to database with ID: " + id + ", Username: " + username + ", Role: " + role);  // Debug
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO users (id, passport_number, firstname, lastname, tel, email, gender, nationality, dob, username, password, role) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE " +
                         "passport_number = VALUES(passport_number), firstname = VALUES(firstname), lastname = VALUES(lastname), " +
                         "tel = VALUES(tel), email = VALUES(email), gender = VALUES(gender), nationality = VALUES(nationality), " +
                         "dob = VALUES(dob), username = VALUES(username), password = VALUES(password), role = VALUES(role)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            if (this instanceof Passenger) {
                pstmt.setString(2, ((Passenger) this).getPassportNumbers(password));
            } else {
                pstmt.setString(2, null);
            }
            pstmt.setString(3, firstname);
            pstmt.setString(4, lastname);
            pstmt.setString(5, tel);
            pstmt.setString(6, email);
            pstmt.setString(7, gender);
            pstmt.setString(8, nationality);
            pstmt.setString(9, dob);
            pstmt.setString(10, username);
            pstmt.setString(11, password);
            pstmt.setString(12, role);
            pstmt.executeUpdate();
        }
    }

    private int ensureValidId(int proposedId) {
        if (proposedId <= 0) {
            idCounter = 1; // Reset to ensure a valid starting ID
            return 1;
        }
        return proposedId;
    }

    public int getId() { return idCounter; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getTel() { return tel; }
    public String getEmail() { return email; }
    public String getGender() { return gender; }
    public String getNationality() { return nationality; }
    public String getDob() { return dob; }
    public String getRole() { return role; }
}