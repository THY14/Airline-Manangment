package USER;

public class Passenger extends Person {
    protected String passportNumbers;
    protected static int totalPassengers = 0;
    protected String password;
        public Passenger(String passportNumbers, String password, int id, String firstname, String lastname, String tel, String email, String gender, String nationality,String dob) {
            super(id, firstname, lastname, tel, email, gender, nationality, dob);
            this.passportNumbers = passportNumbers;
            this.password = password;
            totalPassengers++;  
        }
        // Method to verify password before accessing private fields
        public boolean authenticate(String password) {
            return this.password.equals(password);
        }
        public String getFirstName(String password) {
            if (authenticate(password)) {
                return firstname;
            } else {
                return "Access Denied";
            }
        }
    
        public String getLastName(String password) {
            if (authenticate(password)) {
                return lastname;
            } else {
                return "Access Denied";
            }
        }
    
        public String getTel(String password) {
            if (authenticate(password)) {
                return tel;
            } else {
                return null;  
            }
        }
        public String getEmail(String password) {
            if (authenticate(password)) {
                return email;
            } else {
                return null;
            }
        }
        public String getGender(String password) {
            if (authenticate(password)) {
                return gender;
            } else {
                return null;
            }
        }
        public String getPassportNumbers(String password) {
            if (authenticate(password)) {
                return passportNumbers;
            } else {
                return null;
            }
        }
        public void setFirstName(String password, String firstname) {
            if (authenticate(password)) {
                this.firstname = firstname;
            } else {
                System.out.println("Access Denied: Incorrect Password");
            }
        }
    
        public void setLastName(String password, String lastName) {
            if (authenticate(password)) {
                this.lastname = lastName;
            } else {
                System.out.println("Access Denied: Incorrect Password");
            }
        }
    
        public void setTel(String password,String tel) {
            if (authenticate(password)) {
                this.tel = tel;
            } else {
                System.out.println("Access Denied: Incorrect Password");
            }
        }
    
        public void setEmail(String password,String email) {
            if (authenticate(password)) {
                this.email = email;
            } else {
                System.out.println("Access Denied: Incorrect Password");
            }
        }
    
        public void setGender(String password, String gender) {
            if (authenticate(password)) {
                this.gender = gender;
            } else {
                System.out.println("Access Denied: Incorrect Password");
            }
        }
    
        public void setPassportNumbers(String password,String passportNumbers) {
            if (authenticate(password)) {
                this.passportNumbers = passportNumbers;
            } else {
                System.out.println("Access Denied: Incorrect Password");
            }
        }
        public int getTotalPassengers(String password) {
           if(authenticate(password)) {
              return totalPassengers ;
           }
           else {
              return -1;
           }
    }
}
