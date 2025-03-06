import USER.DatabaseUtil;
import USER.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public boolean isDelayed() { return delayed; }
    public int getBookedEconomy() { return bookedEconomy; }
    public int getBookedBusiness() { return bookedBusiness; }
    public ArrayList<Person> getPersons() { return new ArrayList<>(persons); }
}