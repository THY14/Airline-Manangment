import USER.Person;
import java.util.ArrayList;
import java.util.Scanner;

public class Flight extends TravelEntity {
    private boolean delayed;
    private int bookedEconomy;
    private int bookedBusiness;
    private ArrayList<Person> persons;

    public Flight(int bookedBusiness, int bookedEconomy, boolean delayed, 
                  ArrayList<Person> persons, String airplane_Id, String arrivalLocation, 
                  String arrivalTime, String departureLocation, String departureTime, 
                  String flight_Id) {
        super(airplane_Id, arrivalLocation, arrivalTime, departureLocation, departureTime, flight_Id);
        this.bookedBusiness = bookedBusiness;
        this.bookedEconomy = bookedEconomy;
        this.delayed = delayed;
        this.persons = (persons != null) ? persons : new ArrayList<>();
    }

    public void addPerson(Person person) {
        if (person == null) {
            System.out.println("Error: Cannot add a null person.");
            return;
        }
        persons.add(person);
    }

    public boolean isDelayed() {
        return delayed;
    }

    public void setDelayed(boolean delayed) {
        if (this.delayed == delayed) {
            System.out.println("No change: Flight delay status is already set to " + delayed);
        } else {
            this.delayed = delayed;
            System.out.println(delayed ? "Flight is now marked as delayed." : "Flight is now on time.");
        }
    }

    public int getBookedEconomy() {
        return Math.max(bookedEconomy, 0);
    }

    public void setBookedEconomy(int bookedEconomy, int economyCapacity)  {
        if (bookedEconomy > economyCapacity) {
            System.out.println("Not enough economy seats available!");
        }
        this.bookedEconomy = bookedEconomy;
    }

    public int getBookedBusiness() {
        return Math.max(bookedBusiness, 0);
    }

    public void setBookedBusiness(int bookedBusiness, int businessCapacity)  {
        if (bookedBusiness > businessCapacity) {
            System.out.println("Not enough business seats available!");
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
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        if (persons == null) {
            System.out.println("Error: Person list cannot be null.");
        } else {
            this.persons = persons;
        }
    }

    @Override
    public String toString() {
        return "Flight [flight_Id=" + flight_Id + ", airplane_Id=" + airplane_Id + ", departureLocation="
                + departureLocation + ", delayed=" + delayed + ", arrivalLocation=" + arrivalLocation
                + ", bookedEconomy=" + bookedEconomy + ", bookedBusiness=" + bookedBusiness + ", departureTime="
                + departureTime + ", arrivalTime=" + arrivalTime + ", persons=" + persons + ", isDelayed()="
                + isDelayed() + ", getBookedEconomy()=" + getBookedEconomy()
                + ", getBookedBusiness()=" + getBookedBusiness() + ", getTotalPassengers()=" + getTotalPassengers()
                + ", checkFlightStatus()=" + checkFlightStatus() + "]";
    }
    public static void createFlight(){
        Scanner scanner = new Scanner(System.in);
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
        System.out.println("Flight created successfully!");
        System.out.println(flight);

    }
    public static void showAllFlight(){
        
    }
    public static void main(String[] args) {
                // Create a new flight
        Flight.createFlight();
    }
}
