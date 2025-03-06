<<<<<<< HEAD
=======
import USER.DatabaseUtil;
>>>>>>> 6ab626cb5a74ac9403758d098681b4651a151bac
import USER.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Scanner;
=======
>>>>>>> 6ab626cb5a74ac9403758d098681b4651a151bac

public class Flight extends TravelEntity {
    private boolean delayed;
    private int bookedEconomy;
    private int bookedBusiness;
    private final ArrayList<Person> persons;

    public Flight(int bookedBusiness, int bookedEconomy, boolean delayed, ArrayList<Person> persons, 
                  String airplane_Id, String arrivalLocation, String arrivalTime, 
                  String departureLocation, String departureTime, String flight_Id) {
        super(airplane_Id, arrivalLocation, arrivalTime, departureLocation, departureTime, flight_Id);
        validateInputs(bookedBusiness, bookedEconomy, persons);
        this.bookedBusiness = bookedBusiness;
        this.bookedEconomy = bookedEconomy;
        this.delayed = delayed;
        this.persons = (persons != null) ? new ArrayList<>(persons) : new ArrayList<>();
    }

    private void validateInputs(int bookedBusiness, int bookedEconomy, ArrayList<Person> persons) {
        if (bookedBusiness < 0) throw new IllegalArgumentException("Booked business seats cannot be negative");
        if (bookedEconomy < 0) throw new IllegalArgumentException("Booked economy seats cannot be negative");
    }

    public void saveToDatabase() throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO flights (flight_id, airplane_id, departure_location, arrival_location, departure_time, arrival_time, booked_economy, booked_business, delayed) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, flight_Id);
            pstmt.setString(2, airplane_Id);
            pstmt.setString(3, departureLocation);
            pstmt.setString(4, arrivalLocation);
            pstmt.setString(5, departureTime);
            pstmt.setString(6, arrivalTime);
            pstmt.setInt(7, bookedEconomy);
            pstmt.setInt(8, bookedBusiness);
            pstmt.setBoolean(9, delayed);
            pstmt.executeUpdate();
        }
    }

<<<<<<< HEAD
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
=======
    public boolean isDelayed() { return delayed; }
    public int getBookedEconomy() { return bookedEconomy; }
    public int getBookedBusiness() { return bookedBusiness; }
    public ArrayList<Person> getPersons() { return new ArrayList<>(persons); }
}
>>>>>>> 6ab626cb5a74ac9403758d098681b4651a151bac
