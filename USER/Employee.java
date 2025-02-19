package USER;
public class Employee extends Person {
    protected String position;
    protected String department;
    protected double salary;
    protected String hireDate;

    public Employee( String department, String hireDate, String position, double salary, int id, String firstname, String lastname, String tel, String email, String gender, String nationality, String dob) {
        super(id, firstname, lastname, tel, email, gender, nationality,dob);
        this.department = department;
        this.hireDate = hireDate;
        this.position = position;
        this.salary = salary;
    }  
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
            System.err.println ("Salary cannot be negative.");
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