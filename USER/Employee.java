package USER;

public class Employee extends Person implements UserAuthentication {
    protected String position;
    protected String department;
    protected double salary;
    protected String hireDate;
    private String username;  // Add a username field
    private String password;  // Add a password field
    

    // Constructor register
    public Employee(String department, String hireDate, String position, double salary, int id, String firstname, String lastname, String tel, String email, String gender, String nationality, String dob, String username, String password) {
        super(firstname, lastname, tel, email, gender, nationality, dob);
        this.department = department;
        this.hireDate = hireDate;
        this.position = position;
        this.salary = salary;
        this.username = username;
        this.password = password;
    }
    //Consteuctor login
    public Employee(String email, String password){
        super(email);
        this.password = password;
    }

    // Getters and Setters for Employee attributes
    public int getEmployeeID() { 
        if (id > 0) {
            return id;
        } else {
            System.out.println("Invalid Employee ID");
            return -1;
        }
    }

    public String getFirstName() { 
        if (firstname != null && !firstname.isEmpty()) {
            return firstname;
        } else {
            return "First name not set";
        }
    }

    public String getLastName() { 
        if (lastname != null && !lastname.isEmpty()) {
            return lastname;
        } else {
            return "Last name not set";
        }
    }

    public String getGender() { 
        if (gender != null && (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Prefer not to answer"))) {
            return gender;
        } else {
            return "Invalid gender";
        }
    }

    public String getDepartment() { 
        if (department != null && !department.isEmpty()) {
            return department;
        } else {
            return "Department not set";
        }
    }

    public void setFirstName(String firstname) { 
        if (firstname != null && !firstname.isEmpty()) {
            this.firstname = firstname;
        } else {
            System.out.println("Invalid first name");
        }
    }

    public void setLastName(String lastname) { 
        if (lastname != null && !lastname.isEmpty()) {
            this.lastname = lastname;
        } else {
            System.out.println("Invalid last name");
        }
    }

    public void setGender(String gender) { 
        if (gender != null && (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Other"))) {
            this.gender = gender;
        } else {
            System.out.println("Invalid gender");
        }
    }

    public void setDoB(String dob) { 
        if (dob != null && dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.dob = dob;
        } else {
            System.out.println("Invalid date of birth format. Use YYYY-MM-DD");
        }
    }

    public void setNationality(String nationality) { 
        if (nationality != null && !nationality.isEmpty()) {
            this.nationality = nationality;
        } else {
            System.out.println("Invalid nationality");
        }
    }

    public void setEmail(String email) { 
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("Invalid email address");
        }
    }

    public void setPhoneNumber(String tel) { 
        if (tel != null && tel.matches("\\d{10}")) {
            this.tel = tel;
        } else {
            System.out.println("Invalid phone number. Must be 10 digits.");
        }
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.err.println("Salary cannot be negative.");
        }
    }

    public String getPassword(String password) {
        if(this.password.equals(password)){
            return password;
        }else{
            return "Invalid Password";
        }
    }
    public void setNewPassword(String newPassword, String oldPassword) {
        if(this.password.equals(oldPassword)){
            this.password = newPassword;
            System.out.println("Password changed successfully");
        }else{
            System.out.println("Invalid Password");
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

    @Override
    public void displayInfo() {
    System.out.println("Employee Information:");
    System.out.println("ID: " + id);
    System.out.println("Name: " + firstname + " " + lastname);
    System.out.println("Email: " + email);
    System.out.println("Department: " + department);
    System.out.println("Position: " + position);
    System.out.println("Salary: $" + salary);
    System.out.println("Hire Date: " + hireDate);
}   
    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + firstname + " " + lastname +
               ", Gender: " + gender + ", Date of Birth: " + dob + ", Nationality: " + nationality +
               ", Email: " + email + ", Phone: " + tel + ", Position: " + position +
               ", Department: " + department + ", Salary: $" + salary + ", Hire Date: " + hireDate;
    }
}
