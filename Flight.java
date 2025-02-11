import java.time.LocalDateTime;
import java.util.ArrayList;

public class Flight {
    private static  int id;
	private Airplane airplane;
	private Airport origin;
	private Airport destination;
	private LocalDateTime departureTime; 
	private LocalDateTime arrivalTime; 
	private boolean delayed;
	private int bookedEconomy;
	private int bookedBusiness;
	private ArrayList <Employee> employees;
	private ArrayList <Passenger> passengers;
	public Flight(Airplane airplane, Airport origin, Airport destination, LocalDateTime departureTime,
			LocalDateTime arrivalTime, boolean delayed, int bookedEconomy, int bookedBusiness, ArrayList <Employee> employees,
			ArrayList <Passenger> passengers) {
		this.airplane = airplane;
		this.origin = origin;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.delayed = delayed;
		this.bookedEconomy = bookedEconomy;
		this.bookedBusiness = bookedBusiness;
		this.employees = new ArrayList<>();
		this.passengers = new ArrayList<>();
	}
	public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Flight.id = id;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
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
    
    // Methods to add employees and passengers
    public void addEmployee(Employee employee) { this.employees.add(employee); }
    public void addPassenger(Passenger passenger) { this.passengers.add(passenger); }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Flight{");
        sb.append("airplane=").append(airplane);
        sb.append(", origin=").append(origin);
        sb.append(", destination=").append(destination);
        sb.append(", departureTime=").append(departureTime);
        sb.append(", arrivalTime=").append(arrivalTime);
        sb.append(", delayed=").append(delayed);
        sb.append(", bookedEconomy=").append(bookedEconomy);
        sb.append(", bookedBusiness=").append(bookedBusiness);
        sb.append(", employees=").append(employees);
        sb.append(", passengers=").append(passengers);
        sb.append('}');
        return sb.toString();
    }
}

