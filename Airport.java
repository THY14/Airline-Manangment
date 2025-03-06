import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import USER.DatabaseUtil;

public class Airport {
    private String code;
    private String name;
    private String location;
    private int openyear;
    private String country;
    private double totalPassengerTraffic;
    private boolean international;

    public Airport(String code, String name, String location, int openyear, String country, double totalPassengerTraffic, boolean international) {
        validateInputs(code, name, location, openyear, country, totalPassengerTraffic);
        this.code = code;
        this.name = name;
        this.location = location;
        this.openyear = openyear;
        this.country = country;
        this.totalPassengerTraffic = totalPassengerTraffic;
        this.international = international;
    }

    private void validateInputs(String code, String name, String location, int openyear, String country, double totalPassengerTraffic) {
        if (code == null || code.trim().length() != 3) throw new IllegalArgumentException("Code must be a 3-letter string");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        if (location == null || location.trim().isEmpty()) throw new IllegalArgumentException("Location cannot be null or empty");
        if (openyear < 1900 || openyear > java.time.Year.now().getValue()) throw new IllegalArgumentException("Open year must be between 1900 and current year");
        if (country == null || country.trim().isEmpty()) throw new IllegalArgumentException("Country cannot be null or empty");
        if (totalPassengerTraffic < 0) throw new IllegalArgumentException("Total passenger traffic cannot be negative");
    }

    public void saveToDatabase() throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO airports (code, name, location, open_year, country, total_passenger_traffic, international) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // pstmt.setString(1, code);
            pstmt.setString(2, name);
            pstmt.setString(3, location);
            pstmt.setInt(4, openyear);
            pstmt.setString(5, country);
            pstmt.setDouble(6, totalPassengerTraffic);
            pstmt.setBoolean(7, international);
            pstmt.executeUpdate();
        }
    }

    // public String getCode() { return code; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public int getOpenyear() { return openyear; }
    public String getCountry() { return country; }
    public double getTotalPassengerTraffic() { return totalPassengerTraffic; }
    public boolean isInternational() { return international; }
}