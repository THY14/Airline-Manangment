import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import USER.DatabaseUtil;

public class Airplane {
    private int id;
    private int economyCapacity;
    private int businessCapacity;
    private String model;
    private final ArrayList<Double> flightHours;
    private static String airline;
    private static double fuelCapacity;
    private final String password;

    public Airplane(int id, int economyCapacity, int businessCapacity, String model,
                    ArrayList<Double> flightHours, String airline, double fuelCapacity, String password) {
        validateInputs(id, economyCapacity, businessCapacity, model, flightHours, airline, fuelCapacity, password);
        this.id = id;
        this.economyCapacity = economyCapacity;
        this.businessCapacity = businessCapacity;
        this.model = model;
        this.flightHours = new ArrayList<>(flightHours);
        Airplane.airline = airline;
        Airplane.fuelCapacity = fuelCapacity;
        this.password = password;
    }

    private void validateInputs(int id, int economyCapacity, int businessCapacity, String model,
                                ArrayList<Double> flightHours, String airline, double fuelCapacity, String password) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
        if (economyCapacity < 0) throw new IllegalArgumentException("Economy capacity cannot be negative");
        if (businessCapacity < 0) throw new IllegalArgumentException("Business capacity cannot be negative");
        if (model == null || model.trim().isEmpty()) throw new IllegalArgumentException("Model cannot be null or empty");
        if (flightHours == null) throw new IllegalArgumentException("Flight hours cannot be null");
        if (airline == null || airline.trim().isEmpty()) throw new IllegalArgumentException("Airline cannot be null or empty");
        if (fuelCapacity < 0) throw new IllegalArgumentException("Fuel capacity cannot be negative");
        if (password == null || password.trim().isEmpty()) throw new IllegalArgumentException("Password cannot be null or empty");
    }

    public void saveToDatabase() throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            // Check if the ID already exists to avoid duplicate entry
            String checkSql = "SELECT COUNT(*) FROM airplanes WHERE id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new SQLException("Duplicate entry '" + id + "' for key 'PRIMARY': An airplane with this ID already exists.");
            }

            String sql = "INSERT INTO airplanes (id, economy_capacity, business_capacity, model, airline, fuel_capacity, password) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, economyCapacity);
            pstmt.setInt(3, businessCapacity);
            pstmt.setString(4, model);
            pstmt.setString(5, airline);
            pstmt.setDouble(6, fuelCapacity);
            pstmt.setString(7, password);
            pstmt.executeUpdate();
        }
    }

    public int getId(String password) {
        if (!this.password.equals(password)) throw new SecurityException("Access Denied");
        return id;
    }

    // Add other getters as needed
    public int getEconomyCapacity() { return economyCapacity; }
    public int getBusinessCapacity() { return businessCapacity; }
    public String getModel() { return model; }
    public ArrayList<Double> getFlightHours() { return new ArrayList<>(flightHours); }
    public static String getAirline() { return airline; }
    public static double getFuelCapacity() { return fuelCapacity; }
    public String getPassword() { return password; }
}