package USER;
public class Employee extends Person {
public class Employee extends Person implements UserAuthentication {
    protected String position;
    protected String department;
    protected double salary;
    protected String hireDate;
    private String username;  // Add a username field
    private String password;  // Add a password field
    static HashMap<String, String> users = new HashMap<>();

    // Constructor
    public Employee(String department, String hireDate, String position, double salary, int id, String firstname, String lastname, String tel, String email, String gender, String nationality, String dob, String username, String password) {
        super(id, firstname, lastname, tel, email, gender, nationality, dob);
        this.department = department;
        this.hireDate = hireDate;
        this.position = position;
        this.salary = salary;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters for Employee attributes
    public int getEmployeeID() { return id; }
    public String getFirstName() { return firstname; }
    public String getLastName() { return lastname; }
    public String getGender() { return gender; }
    public String getDepartment() { return department; }

    public void setFirstName(String firstname) { this.firstname = firstname; }
    public void setLastName(String lastname) { this.lastname = lastname; }
    public void setGender(String gender) { this.gender = gender; }
    public void setDoB(String dob) { this.dob = dob; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String tel) { this.tel = tel; }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.err.println("Salary cannot be negative.");
        }
    }

    // Implement the methods from the UserAuthentication interface
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
        // Password validation: At least 8 characters, contains digits and special characters
        if (password.length() < 8) {
            return false;  // Password too short
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
    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + firstname + " " + lastname +
               ", Gender: " + gender + ", Date of Birth: " + dob + ", Nationality: " + nationality +
               ", Email: " + email + ", Phone: " + tel + ", Position: " + position +
               ", Department: " + department + ", Salary: $" + salary + ", Hire Date: " + hireDate;
    }
}
