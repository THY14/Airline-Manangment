import USER.Employee;
import USER.Passenger;
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

    public void setBookedEconomy(int bookedEconomy, int economyCapacity) throws FlightException {
        if (bookedEconomy > economyCapacity) {
            throw new FlightException("Not enough economy seats available!");
        }
        this.bookedEconomy = bookedEconomy;
    }

    public int getBookedBusiness() {
        return Math.max(bookedBusiness, 0);
    }

    public void setBookedBusiness(int bookedBusiness, int businessCapacity) throws FlightException {
        if (bookedBusiness > businessCapacity) {
            throw new FlightException("Not enough business seats available!");
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

    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Passenger("123456789", 1, "John", "Doe", "555-1234", "john.doe@example.com", "Male", "USA", "1990-01-01", "john_doe", "securePassword123"));
        persons.add(new Employee("Engineering", "2025-01-15", "Software Engineer", 95000, 1, "Alice", "Johnson", "555-0011", "alice.johnson@example.com", "Female", "USA", "1992-06-23", "alice_92", "password123"));

        Flight flight = new Flight(2, 3, false, persons, 
                                   "A320", "NYC", "10:00 AM", "LAX", "1:00 PM", "FL123");

        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter new flight delay status (true/false): ");
            boolean newDelayedStatus = sc.nextBoolean();
            flight.setDelayed(newDelayedStatus);

            System.out.println("Input BookedEconomy:");
            int bookedEconomy = sc.nextInt();

            System.out.println("Input BookedBusiness:");
            int bookedBusiness = sc.nextInt();

            // Using FlightException Handling
            FlightException.handleException(() -> flight.setBookedEconomy(bookedEconomy, 100)); // 100 max economy seats
            FlightException.handleException(() -> flight.setBookedBusiness(bookedBusiness, 40)); // 40 max business seats

        } catch (Exception e) {
            System.out.println("Invalid input! Please enter numbers only.");
            sc.nextLine();  // Clear buffer to avoid infinite loop
        } finally {
            sc.close();  // Close scanner to prevent resource leak
        }
    }
}
