package USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employee extends Person {
    private String position;
    private String department;
    private double salary;
    private String hireDate;

    public Employee(String department, String hireDate, String position, double salary, String firstname, String lastname, 
                    String tel, String email, String gender, String nationality, String dob, String username, String password) {
        super(firstname, lastname, tel, email, gender, nationality, dob, username, password, "EMPLOYEE");
        validateEmployeeInputs(department, hireDate, position, salary);
        this.department = department;
        this.hireDate = hireDate;
        this.position = position;
        this.salary = salary;
        System.out.println("Created Employee with ID: " + this.id + ", Username: " + username);  // Debug
    }

    private void validateEmployeeInputs(String department, String hireDate, String position, double salary) {
        if (department == null || department.trim().isEmpty()) throw new IllegalArgumentException("Department cannot be null or empty");
        if (hireDate == null || !hireDate.matches("\\d{4}-\\d{2}-\\d{2}")) throw new IllegalArgumentException("Hire date must be in YYYY-MM-DD format");
        if (position == null || position.trim().isEmpty()) throw new IllegalArgumentException("Position cannot be null or empty");
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative");
    }

    public int getEmployeeID() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) throw new IllegalArgumentException("Department cannot be null or empty");
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if (position == null || position.trim().isEmpty()) throw new IllegalArgumentException("Position cannot be null or empty");
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative");
        this.salary = salary;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        if (hireDate == null || !hireDate.matches("\\d{4}-\\d{2}-\\d{2}")) throw new IllegalArgumentException("Hire date must be in YYYY-MM-DD format");
        this.hireDate = hireDate;
    }

    @Override
    public void saveToDatabase() throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID: ID must be greater than 0 for database insertion. Current ID: " + id);
        }
        System.out.println("Saving Employee to database with ID: " + id + ", Username: " + username);  // Debug
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO users (id, passport_number, firstname, lastname, tel, email, gender, nationality, dob, username, password, role) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE " +
                         "passport_number = VALUES(passport_number), firstname = VALUES(firstname), lastname = VALUES(lastname), " +
                         "tel = VALUES(tel), email = VALUES(email), gender = VALUES(gender), nationality = VALUES(nationality), " +
                         "dob = VALUES(dob), username = VALUES(username), password = VALUES(password), role = VALUES(role)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, null);  // Employees don’t have passport numbers
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

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + firstname + " " + lastname +
               ", Gender: " + gender + ", Date of Birth: " + dob + ", Nationality: " + nationality +
               ", Email: " + email + ", Phone: " + tel + ", Position: " + position +
               ", Department: " + department + ", Salary: $" + salary + ", Hire Date: " + hireDate;
    }
}