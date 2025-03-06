import USER.Person;
import java.util.ArrayList;
import java.util.Scanner;

public class Flight extends TravelEntity {
    private boolean delayed;
    private int bookedEconomy;
    private int bookedBusiness;
    private final ArrayList<Person> persons;
    private static final ArrayList<Flight> flights = new ArrayList<>();

    public Flight(int bookedBusiness, int bookedEconomy, boolean delayed, ArrayList<Person> persons, 
                  String airplane_Id, String arrivalLocation, String arrivalTime, 
                  String departureLocation, String departureTime, String flight_Id) {
        super(airplane_Id, arrivalLocation, arrivalTime, departureLocation, departureTime, flight_Id);
        validateInputs(bookedBusiness, bookedEconomy);
        this.bookedBusiness = bookedBusiness;
        this.bookedEconomy = bookedEconomy;
        this.delayed = delayed;
        this.persons = (persons != null) ? new ArrayList<>(persons) : new ArrayList<>();
    }

    private void validateInputs(int bookedBusiness, int bookedEconomy) {
        if (bookedBusiness < 0) throw new IllegalArgumentException("Booked business seats cannot be negative");
        if (bookedEconomy < 0) throw new IllegalArgumentException("Booked economy seats cannot be negative");
    }

    public int getBookedEconomy() {
        return Math.max(bookedEconomy, 0);
    }

    public void setBookedEconomy(int bookedEconomy, int economyCapacity) {
        if (bookedEconomy > economyCapacity) {
            System.out.println("Not enough economy seats available!");
            return;
        }
        this.bookedEconomy = bookedEconomy;
    }

    public int getBookedBusiness() {
        return Math.max(bookedBusiness, 0);
    }

    public void setBookedBusiness(int bookedBusiness, int businessCapacity) {
        if (bookedBusiness > businessCapacity) {
            System.out.println("Not enough business seats available!");
            return;
        }
        this.bookedBusiness = bookedBusiness;
    }

    public int getTotalPassengers() {
        return bookedEconomy + bookedBusiness;
    }

    public String checkFlightStatus() {
        return delayed ? "Flight is delayed" : "Flight is on time";
    }

    public ArrayList<Person> getPersons() {
        return new ArrayList<>(persons);
    }
    public boolean isDelayed() {
        return delayed;
    }

    public void setDelayed(boolean delayed) {
        this.delayed = delayed;
    }

    public void addPerson(Person person) {
        if (person != null) {
            persons.add(person);
        } else {
            System.out.println("Error: Cannot add a null person.");
        }
    }

    

    @Override
    public String toString() {
        return "Flight [flight_Id=" + flight_Id + ", airplane_Id=" + airplane_Id + ", departureLocation="
                + departureLocation + ", delayed=" + delayed + ", bookedEconomy=" + bookedEconomy + ", arrivalLocation="
                + arrivalLocation + ", bookedBusiness=" + bookedBusiness + ", departureTime=" + departureTime
                + ", arrivalTime=" + arrivalTime + ", persons=" + persons + ", getBookedEconomy()=" + getBookedEconomy()
                + ", getClass()=" + getClass() + ", toString()=" + super.toString() + ", getBookedBusiness()="
                + getBookedBusiness() + ", getTotalPassengers()=" + getTotalPassengers() + ", checkFlightStatus()="
                + checkFlightStatus() + ", getPersons()=" + getPersons() + ", isDelayed()=" + isDelayed()
                + ", hashCode()=" + hashCode() + "]";
    }

    public static void createFlight() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter flight details:");
            System.out.print("Flight ID: ");
            String flightId = scanner.nextLine();
            System.out.print("Airplane ID: ");
            String airplaneId = scanner.nextLine();
            System.out.print("Departure Location: ");
            String departureLocation = scanner.nextLine();
            System.out.print("Arrival Location: ");
            String arrivalLocation = scanner.nextLine();
            System.out.print("Departure Time: ");
            String departureTime = scanner.nextLine();
            System.out.print("Arrival Time: ");
            String arrivalTime = scanner.nextLine();
            
            Flight flight = new Flight(0, 0, false, new ArrayList<>(), airplaneId, arrivalLocation, arrivalTime, departureLocation, departureTime, flightId);
            flights.add(flight);
            System.out.println("Flight created successfully!");
            System.out.println(flight);
        }
    }

    public static void showAllFlights() {
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
        } else {
            for (Flight flight : flights) {
                System.out.println(flight);
            }
        }
    }

    public static void main(String[] args) {
        Flight.createFlight();
        Flight.showAllFlights();
    }
}
