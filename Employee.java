public class Employee {
    private String employeeID;
    private String firstName;
    private String lastName;
    private String Gender;
    private String DoB;
    private String email;
    private String phoneNumber;
    private String position;
    private String department;
    private double salary;
    private String hireDate;

    public Employee (String employeeID, String firstName, String lastName, String Gender, String DoB, String email, String phoneNumber, String position, String department, double salary, String hireDate) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.Gender = Gender;
        this.DoB = DoB;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.department = department;
        this.salary = salary;
        this.hireDate = hireDate;
    }

}