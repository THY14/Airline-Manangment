import java.util.ArrayList;

public class Passenger {
    private int id;
    private String firstName;
    private String lastName;
    private ArrayList<String> passportNumbers;
    private ArrayList<String> Tel;
    private ArrayList<String> email;
    private String Gender;
    private static int totalPassengers = 0;

    // Password field for access control
    private String password;

    // Constructor
    public Passenger(int id, String firstName, String lastName, 
                     ArrayList<String> Tel, ArrayList<String> email, 
                     ArrayList<String> passportNumbers, String Gender, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.Tel = Tel;
        this.email = email;
        this.passportNumbers = passportNumbers;
        this.Gender = Gender;
        this.password = password;  // Set the password during creation
        totalPassengers++;    
    }

    // Method to verify password before accessing private fields
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    // Getter methods (after authentication)
    public String getFirstName(String password) {
        if (authenticate(password)) {
            return firstName;
        } else {
            return "Access Denied";
        }
    }

    public String getLastName(String password) {
        if (authenticate(password)) {
            return lastName;
        } else {
            return "Access Denied";
        }
    }

    public ArrayList<String> getTel(String password) {
        if (authenticate(password)) {
            return Tel;
        } else {
            return null;  
        }
    }

    public ArrayList<String> getEmail(String password) {
        if (authenticate(password)) {
            return email;
        } else {
            return null;
        }
    }

    public String getGender(String password) {
        if (authenticate(password)) {
            return Gender;
        } else {
            return null;
        }
    }

    public ArrayList<String> getPassportNumbers(String password) {
        if (authenticate(password)) {
            return passportNumbers;
        } else {
            return null;
        }
    }

    // Setters with authentication (password check before setting values)
    public void setFirstName(String password, String firstName) {
        if (authenticate(password)) {
            this.firstName = firstName;
        } else {
            System.out.println("Access Denied: Incorrect Password");
        }
    }

    public void setLastName(String password, String lastName) {
        if (authenticate(password)) {
            this.lastName = lastName;
        } else {
            System.out.println("Access Denied: Incorrect Password");
        }
    }

    public void setTel(String password, ArrayList<String> Tel) {
        if (authenticate(password)) {
            this.Tel = Tel;
        } else {
            System.out.println("Access Denied: Incorrect Password");
        }
    }

    public void setEmail(String password, ArrayList<String> email) {
        if (authenticate(password)) {
            this.email = email;
        } else {
            System.out.println("Access Denied: Incorrect Password");
        }
    }

    public void setGender(String password, String Gender) {
        if (authenticate(password)) {
            this.Gender = Gender;
        } else {
            System.out.println("Access Denied: Incorrect Password");
        }
    }

    public void setPassportNumbers(String password, ArrayList<String> passportNumbers) {
        if (authenticate(password)) {
            this.passportNumbers = passportNumbers;
        } else {
            System.out.println("Access Denied: Incorrect Password");
        }
    }
}
