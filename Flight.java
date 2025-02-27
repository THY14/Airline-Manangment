import java.util.ArrayList;
import USER.Person;
import USER.Passenger;
import USER.Employee;
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

    public void setBookedEconomy(int bookedEconomy, int economyCapacity) throws Exception {
        if (bookedEconomy > economyCapacity) {
            throw new Exception("Not enough economy seats available!");
        }
        this.bookedEconomy = bookedEconomy;
    }

    public int getBookedBusiness() {
        return Math.max(bookedBusiness, 0);
    }

    public void setBookedBusiness(int bookedBusiness, int businessCapacity) throws Exception {
        if (bookedBusiness > businessCapacity) {
            throw new Exception("Not enough business seats available!");
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

    public void DisplayFlightInfo() {
        super.DisplayInfo();
        System.out.println("Booked Economy: " + bookedEconomy);
        System.out.println("Booked Business: " + bookedBusiness);
        System.out.println("Flight Status: " + checkFlightStatus());
        System.out.println("\nPersons on Flight:");
        for (Person p : persons) {
            System.out.println("- " + p);
        }
    }

    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Passenger("P12345", "securePass", 1, "John", "Doe", "123-456-7890", "john@example.com", "Male", "USA", "01-01-1990"));
        persons.add(new Employee("Alice", "Johnson", "555-1234", "alice@example.com", "Female", "USA", "03-03-1980", "Pilot", 80000,));

        Flight flight = new Flight(2, 3, false, persons, 
                                   "A320", "NYC", "10:00 AM", "LAX", "1:00 PM", "FL123");
        flight.DisplayFlightInfo();
    }
}
