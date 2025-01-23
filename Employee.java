public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String Tel;
    private double salary;
    private String position;
    private String department;
    private String hireDate;

    public Employee(String employeeId, String firstN, String lastN, String posit, String depart, String mail, String number, double fee, String hire) {
        id = employeeId;
        firstName = firstN;
        lastName = lastN;
        position = posit;
        department = depart;
        email = mail;
        Tel = number;
        salary = fee;
        hireDate = hire;
    }

    public String getId(){ return id; }
    
    public void setFirstName(String firstN){ firstName = firstN; }
    public String getFirstName(){ return firstName; }

    public void setLastName(String lastN){ lastName = lastN;}
    public String getLastName(){ return lastName; }

    public void setPosition(String posit){ position = posit;  }
    public String getPosition(){ return position; }

    public void setDepartment(String depart){ depart = department; }
    public String getDepartment(){ return department;}

    public void setEmail(String mail){ mail = email; }
    public String getEmail(){ return email; }

    public void setTel(String number){ number = Tel; }
    public String getTel(){ return Tel; }

    public void setSalary(double fee){ fee = salary;}
    public double getSalary(){ return salary;}

    public void setHireDate(String hire){ hire = hireDate; }
    public String getHireDate(){ return hireDate;}   

}