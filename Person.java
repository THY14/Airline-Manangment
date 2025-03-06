

import USER.UserAuthentication;

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
    private static int idCounter = 0;

    // Full constructor for registration
    public Person(String firstname, String lastname, String tel, String email, String gender, String nationality, String dob, String username, String password) {
        validateFullInputs(firstname, lastname, tel, email, gender, nationality, dob, username, password);
        this.id = ++idCounter;
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

    // Constructor for login
    public Person(String email, String password) {
        if (email == null || email.trim().isEmpty()) throw new IllegalArgumentException("Email cannot be null or empty");
        if (password == null || password.trim().isEmpty()) throw new IllegalArgumentException("Password cannot be null or empty");
        this.id = 0; // Not assigned for login-only constructor
        this.email = email;
        this.password = password;
    }

    private void validateFullInputs(String firstname, String lastname, String tel, String email, String gender, String nationality, String dob, String username, String password) {
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
        if (this.username == null || this.password == null) return false; // Not registered yet
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public boolean validatePassword(String password) {
        if (password.length() < 8) return false;
        if (!password.matches(".*\\d.*")) return false;
        if (!password.matches(".*[^a-zA-Z0-9].*")) return false;
        if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*")) return false;
        return true;
    }

    // Getters (public for subclasses to use)
    public int getId() { return id; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getTel() { return tel; }
    public String getEmail() { return email; }
    public String getGender() { return gender; }
    public String getNationality() { return nationality; }
    public String getDob() { return dob; }

    public Object getRole() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRole'");
    }
}