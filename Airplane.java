import java.util.ArrayList;

public class Airplane {
    private int id;
    private int economyCapacity;
    private int businessCapacity;
    private String model;
    private ArrayList<Double> flightHours;
    private static String airline;
    private static double fuelCapacity;
    private String password;  // Instance password

    // Constructor
    public Airplane(int id, int economyCapacity, int businessCapacity, String model,
                    ArrayList<Double> flightHours, String airline, double fuelCapacity, String password) {
        this.id = id;
        this.economyCapacity = economyCapacity;
        this.businessCapacity = businessCapacity;
        this.model = model;
        this.flightHours = flightHours;
        this.airline = airline;
        this.fuelCapacity = fuelCapacity;
        this.password = password;  // Set the instance password
    }

    // Instance authentication method (checks instance password)
    private boolean authenticate(String password) {
        return this.password.equals(password);  // Compare against the instance password
    }

    // Static authentication method for global access (e.g., airline, fuelCapacity)
    private static boolean authenticateStatic(String password) {
        // Static password set to "12345"
        return password.equals("12345");  // Compare against the global static password
    }

    // Static method to get the airline with authentication
    public static String getAirline(String password) {
        if (authenticateStatic(password)) {
            return airline;
        } else {
            return "Access Denied";
        }
    }

    // Static method to get the fuel capacity with authentication
    public static double getFuelCapacity(String password) {
        if (authenticateStatic(password)) {
            return fuelCapacity;
        } else {
            return -1;
        }
    }

    // Instance method to get flight hours
    public ArrayList<Double> getFlightHours(String password) {
        if (authenticate(password)) {
            return flightHours;
        } else {
            return null;
        }
    }

    // Instance method to get a specific flight hour by index
    public double getFlightHour(int index, String password) {
        if (authenticate(password)) {
            if (index >= 0 && index < flightHours.size()) {
                return flightHours.get(index);
            }
        }
        return -1;
    }

    // Instance method to add a flight hour
    public void addFlightHour(double hour, String password) {
        if (authenticate(password)) {
            flightHours.add(hour);
        }
    }

    // Instance methods for getting and setting attributes
    public int getId(String password) {
        if (authenticate(password)) {
            return id;
        } else {
            return -1;
        }
    }

    public void setId(int id, String password) {
        if (authenticate(password)) {
            this.id = id;
        }
    }

    public int getEconomyCapacity(String password) {
        if (authenticate(password)) {
            return economyCapacity;
        } else {
            return -1;
        }
    }

    public void setEconomyCapacity(int economyCapacity, String password) {
        if (authenticate(password)) {
            this.economyCapacity = economyCapacity;
        }
    }

    public int getBusinessCapacity(String password) {
        if (authenticate(password)) {
            return businessCapacity;
        } else {
            return -1;
        }
    }

    public void setBusinessCapacity(int businessCapacity, String password) {
        if (authenticate(password)) {
            this.businessCapacity = businessCapacity;
        }
    }

    public String getModel(String password) {
        if (authenticate(password)) {
            return model;
        } else {
            return "Access Denied";
        }
    }

    public void setModel(String model, String password) {
        if (authenticate(password)) {
            this.model = model;
        }
    }

    public void setFlightHours(ArrayList<Double> flightHours, String password) {
        if (authenticate(password)) {
            this.flightHours = flightHours;
        }
    }

    // Static methods for setting global attributes
    public static void setAirline(String airline, String password) {
        if (authenticateStatic(password)) {
            Airplane.airline = airline;
        }
    }

    public static void setFuelCapacity(double fuelCapacity, String password) {
        if (authenticateStatic(password)) {
            Airplane.fuelCapacity = fuelCapacity;
        }
    }

    // Main method with example usage
    public static void main(String[] args) {
        // Example flight hours
        ArrayList<Double> flightHours = new ArrayList<>();
        flightHours.add(3.5);
        flightHours.add(5.0);
        flightHours.add(4.2);

        // Create an Airplane object with instance password set to "12345"
        Airplane airplane = new Airplane(101, 150, 50, "Boeing 747", flightHours, "Delta Airlines", 50000.0, "12345");

        // Testing instance methods with instance password ("12345")
        System.out.println("Airplane Model: " + airplane.getModel("12345"));
        System.out.println("Economy Capacity: " + airplane.getEconomyCapacity("12345"));
        System.out.println("Business Capacity: " + airplane.getBusinessCapacity("12345"));
        System.out.println("Flight Hours: " + airplane.getFlightHours("12345"));
        
        // Testing flight hour access by index
        System.out.println("Flight hour at index 1: " + airplane.getFlightHour(1, "12345"));
        
        // Add new flight hour and print updated list
        airplane.addFlightHour(6.0, "12345");
        System.out.println("Updated Flight Hours: " + airplane.getFlightHours("12345"));
        
        // Test setting new id with instance password
        airplane.setId(202, "12345");
        System.out.println("New Airplane ID: " + airplane.getId("12345"));

        // Testing static methods with the global password ("12345")
        Airplane.setAirline("American Airlines", "12345");  // Using the new global password
        Airplane.setFuelCapacity(60000.0, "12345");

        System.out.println("Airline: " + Airplane.getAirline("12345"));
        System.out.println("Fuel Capacity: " + Airplane.getFuelCapacity("12345"));
    }
}
