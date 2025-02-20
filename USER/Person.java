package USER;
public abstract class Person { //Main class
    protected static int id=0;
    protected String firstname;
    protected String lastname;
    protected String tel;
    protected String email;
    protected String gender;
    protected String nationality;
    protected String dob;
    
    public Person(String firstname, String lastname, String tel, String email, String gender, String nationality,String dob) {
        id = id + 1;
        this.firstname = firstname;
        this.lastname = lastname;
        this.tel = tel;
        this.email = email;
        this.gender = gender;
        this.nationality = nationality;
        this.dob = dob;
    }

    public Person(String email){
        this.email = email;
    }

    public abstract void displayInfo();
}

