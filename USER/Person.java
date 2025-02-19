package USER;
public class Person {
    protected int id;
    protected String firstname;
    protected String lastname;
    protected String tel;
    protected String email;
    protected String gender;
    protected String nationality;
    protected String dob;
    
    public Person(int id, String firstname, String lastname, String tel, String email, String gender, String nationality,String dob) {
         this.id = id;
         this.firstname = firstname;
         this.lastname = lastname;
         this.tel = tel;
         this.email = email;
         this.gender = gender;
         this.nationality = nationality;
         this.dob = dob;
     }
}

