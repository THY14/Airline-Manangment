
import USER.*;
import java.util.ArrayList;
public class Flight extends TravelEntity {
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
    public Flight (int maxEconomySeats , int max){

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

    public void setBookedEconomy(int bookedEconomy, int economyCapacity) {
        if(bookedEconomy <= economyCapacity){
            this.bookedEconomy = bookedEconomy;
        } else {
            System.out.println("Not enough economy seats available");
        }
    }

    public int getBookedBusiness() {
        return bookedBusiness;
    }

    public void setBookedBusiness(int bookedBusiness, int businessCapacity) {
        if(bookedBusiness <= businessCapacity){
            this.bookedBusiness = bookedBusiness;
        } else {
            System.out.println("Not enough business seats available");
        }
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
    public void DisplayFlightInfo() {
        super.DisplayInfo();
        System.out.println("Booked Economy: " + bookedEconomy);
        System.out.println("Booked Business: " + bookedBusiness);
        System.out.println("Flight Status: " + checkFlightStatus());
    }
    
    }
    
    
}

