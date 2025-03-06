import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import USER.DatabaseUtil;

public class Booking {
    private static int bookingIdCounter = 0;
    private final int bookingId;
    private String origin;
    private String destination;
    private LocalDate depart;
    private int numOfPassengers;
    private String seatClass;
    private final ArrayList<String> flightIDs;
    private double price;
    private double totalPrice;
    private int passengerId;

    public Booking(String origin, String destination, LocalDate depart, int numOfPassengers, String seatClass, double price, String passengerUsernameBooking) {
        validateInputs(origin, destination, depart, numOfPassengers, seatClass, price);
        this.bookingId = ++bookingIdCounter;
        this.origin = origin;
        this.destination = destination;
        this.depart = depart;
        this.numOfPassengers = numOfPassengers;
        this.seatClass = seatClass;
        this.flightIDs = new ArrayList<>();
        this.price = price;
        this.totalPrice = price * numOfPassengers;
        this.passengerId = passengerUsernameBooking;
    }

    public Booking(String origin2, String destination2, LocalDate now, int numOfPassengers2, String seatClass2,
            double price2, int id) {
                this.bookingId = 0;
        //TODO Auto-generated constructor stub
    }

    private void validateInputs(String origin, String destination, LocalDate depart, int numOfPassengers, String seatClass, double price) {
        if (origin == null || origin.trim().isEmpty()) throw new IllegalArgumentException("Origin cannot be null or empty");
        if (destination == null || destination.trim().isEmpty()) throw new IllegalArgumentException("Destination cannot be null or empty");
        if (depart == null) throw new IllegalArgumentException("Departure date cannot be null");
        if (numOfPassengers <= 0) throw new IllegalArgumentException("Number of passengers must be greater than zero");
        if (seatClass == null || seatClass.trim().isEmpty()) throw new IllegalArgumentException("Seat class cannot be null or empty");
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
    }

    public void saveToDatabase() throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO bookings (booking_id, origin, destination, depart_date, num_passengers, seat_class, price, total_price, passenger_id) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookingId);
            pstmt.setString(2, origin);
            pstmt.setString(3, destination);
            pstmt.setDate(4, java.sql.Date.valueOf(depart));
            pstmt.setInt(5, numOfPassengers);
            pstmt.setString(6, seatClass);
            pstmt.setDouble(7, price);
            pstmt.setDouble(8, totalPrice);
            pstmt.setInt(9, passengerId);
            pstmt.executeUpdate();

            for (String flightId : flightIDs) {
                String fbSql = "INSERT INTO flight_bookings (flight_id, booking_id) VALUES (?, ?)";
                PreparedStatement fbStmt = conn.prepareStatement(fbSql);
                fbStmt.setString(1, flightId);
                fbStmt.setInt(2, bookingId);
                fbStmt.executeUpdate();
            }
        }
    }

    public int getId() { return bookingId; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public LocalDate getDepart() { return depart; }
    public int getNumOfPassengers() { return numOfPassengers; }
    public String getSeatClass() { return seatClass; }
    public ArrayList<String> getFlightIDs() { return new ArrayList<>(flightIDs); }
    public double getPrice() { return price; }
    public double getTotalPrice() { return totalPrice; }
    public void addFlightID(String flightID) {
        if (flightID == null || flightID.trim().isEmpty()) throw new IllegalArgumentException("Flight ID cannot be null or empty");
        this.flightIDs.add(flightID);
    }
}