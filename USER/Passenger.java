package USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Passenger extends Person {
    private String passportNumbers;

    public Passenger(String passportNumbers, String firstname, String lastname, String tel, String email, String gender, 
                     String nationality, String dob, String username, String password, String role) {
        super(firstname, lastname, tel, email, gender, nationality, dob, username, password, role);
        if (passportNumbers == null || passportNumbers.trim().isEmpty()) throw new IllegalArgumentException("Passport number cannot be null or empty");
        this.passportNumbers = passportNumbers;
        System.out.println("Created Passenger with ID: " + this.id + ", Username: " + username);  // Debug
    }

    public String getPassportNumbers(String password) {
        if (!this.password.equals(password)) throw new SecurityException("Access Denied");
        return passportNumbers;
    }

    public void setPassportNumbers(String password, String passportNumbers) {
        if (!this.password.equals(password)) throw new SecurityException("Access Denied");
        if (passportNumbers == null || passportNumbers.trim().isEmpty()) throw new IllegalArgumentException("Passport number cannot be null or empty");
        this.passportNumbers = passportNumbers;
    }

    @Override
    public void saveToDatabase() throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID: ID must be greater than 0 for database insertion. Current ID: " + id);
        }
        System.out.println("Saving Passenger to database with ID: " + id + ", Username: " + username);  // Debug
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO users (id, passport_number, firstname, lastname, tel, email, gender, nationality, dob, username, password, role) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                         "ON DUPLICATE KEY UPDATE passport_number = VALUES(passport_number), firstname = VALUES(firstname), lastname = VALUES(lastname), " +
                         "tel = VALUES(tel), email = VALUES(email), gender = VALUES(gender), nationality = VALUES(nationality), " +
                         "dob = VALUES(dob), username = VALUES(username), password = VALUES(password), role = VALUES(role)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, passportNumbers); // Use passportNumbers instead of null
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
}