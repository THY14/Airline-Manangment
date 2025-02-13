
import java.util.ArrayList;
public class Flight extends TravelEntity implements Interface{
	private boolean delayed;
	private int bookedEconomy;
	private int bookedBusiness;
	private ArrayList <Employee> employees;
	private ArrayList <Passenger> passengers;

    public Flight(int bookedBusiness, int bookedEconomy, boolean delayed, ArrayList<Employee> employees, ArrayList<Passenger> passengers, String airplane_Id, String arrivalLocation, String arrivalTime, String departureLocation, String departureTime, String flight_Id) {
        super(airplane_Id, arrivalLocation, arrivalTime, departureLocation, departureTime, flight_Id);
        this.bookedBusiness = bookedBusiness;
        this.bookedEconomy = bookedEconomy;
        this.delayed = delayed;
        this.employees = employees;
        this.passengers = passengers;
    }
	
    public boolean isDelayed() {
        return delayed;
    }

    public void setDelayed(boolean delayed) {
        this.delayed = delayed;
    }

    public int getBookedEconomy() {
        return bookedEconomy;
    }

    public void setBookedEconomy(int bookedEconomy) {
        this.bookedEconomy = bookedEconomy;
    }

    public int getBookedBusiness() {
        return bookedBusiness;
    }

    public void setBookedBusiness(int bookedBusiness) {
        this.bookedBusiness = bookedBusiness;
    }
    public int getTotalPassengers() {
        return bookedEconomy + bookedBusiness;
    }

    public String checkFlightStatus() {
        return delayed ? "Flight is delayed" : "Flight is on time";
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }
    @Override
    public void addEmployees(Employee employees) {
        this.employees.add(employees);
    }
    @Override
    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
    }

    @Override
    public void addpassenger(Passenger passenger) {
        this.passengers.add(passenger);
        
    }

    @Override
    public void removePassenger(Passenger passenger) {
        this.passengers.remove(passenger);
    }
    
}

