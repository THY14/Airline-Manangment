public class Passenger {
    private int id;
	private String firstName;
	private String lastName;
    private String passportNumber;
	private String Tel;
	private String email;
    private String Gender;
    public Passenger(int id, String firstName, String lastName, String Tel, String email,String passportNumber,String Gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.Tel = Tel;
        this.email = email;
        this.passportNumber = passportNumber;
        this.Gender = Gender;	
    }

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getTel() {
        return Tel;
    }
    public String getEmail() {
        return email;
    }
    public String getGender() {
        return Gender;
    }
    public String getpassportNumber () {
        return passportNumber;
    }
    public void setid(int id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setTel(String Tel) {
        this.Tel = Tel;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

}
