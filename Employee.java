public class Employee {
    private static int employeeID = 0 ;
    private String firstName;
    private String lastName;
    private String gender;
    private String DoB;
    private String nationality;
    private String email;
    private String phoneNumber;
    private String position;
    private String department;
    private double salary;
    private String hireDate;

    public Employee (String firstName, String lastName, String department) {
        employeeID = employeeID + 1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

}