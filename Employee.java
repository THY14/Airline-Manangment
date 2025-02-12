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

    public int getEmployeeID() { return employeeID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGender() { return gender; }
    public String getDepartment() { return department; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setGender(String gender) { this.gender = gender; }
    public void setDoB(String DoB) { this.DoB = DoB; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeID + ", Name: " + firstName + " " + lastName +
               ", Gender: " + gender + ", Date of Birth: " + DoB + ", Nationality: " + nationality +
               ", Email: " + email + ", Phone: " + phoneNumber + ", Position: " + position +
               ", Department: " + department + ", Salary: $" + salary + ", Hire Date: " + hireDate;
    }

}