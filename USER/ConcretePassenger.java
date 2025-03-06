package USER;

public class ConcretePassenger extends Passenger {
    public ConcretePassenger(String passportNumbers, String firstname, String lastname, String tel, String email, 
                             String gender, String nationality, String dob, String username, String password) {
        super(passportNumbers, firstname, lastname, tel, email, gender, nationality, dob, username, password, "PASSENGER");
    }
}