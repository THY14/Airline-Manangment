import java.util.ArrayList;

public class Airplane {
    private int id;
    private int economyCapacity;
    private int businessCapacity;
    private String model;
    private ArrayList<Double> flightHours;  
    private static String airline;  
    private static double fuelCapacity;  
    private String password; 

    public Airplane(int id, int economyCapacity, int businessCapacity, String model,
                    ArrayList<Double> flightHours, String airline, double fuelCapacity, String password) {
        this.id = id;
        this.economyCapacity = economyCapacity;
        this.businessCapacity = businessCapacity;
        this.model = model;
        this.flightHours = flightHours;
        this.airline = airline;
        this.fuelCapacity = fuelCapacity;
        this.password = password;  
    }

    private boolean authenticate(String password) {
        return this.password.equals(password);
    }

  
    public static String getAirline(String password) {
        if (authenticateStatic(password)) {
            return airline;
        } else {
            return "Access Denied";
        }
    }

    public static double getFuelCapacity(String password) {
        if (authenticateStatic(password)) {
            return fuelCapacity;
        } else {
            return -1;  
        }
    }

    public ArrayList<Double> getFlightHours(String password) {
        if (authenticate(password)) {
            return flightHours;
        } else {
            return null;  
        }
    }

    public double getFlightHour(int index, String password) {
        if (authenticate(password)) {
            if (index >= 0 && index < flightHours.size()) {
                return flightHours.get(index);
            }
        }
        return -1;  
    }

    // Method to add a flight hour to the flight hours list, requires password authentication
    public void addFlightHour(double hour, String password) {
        if (authenticate(password)) {
            flightHours.add(hour);
        }
    }

    // Getter and Setter for other attributes (no authentication required for non-sensitive data)
    public int getId(String password) {
        if (authenticate(password)) {
            return id;
        } else {
            return -1;  // Return -1 to indicate access denial
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
            return -1;  // Return -1 to indicate access denial
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
            return -1;  // Return -1 to indicate access denial
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

    // Setter for flight hours list (direct access without password control)
    public void setFlightHours(ArrayList<Double> flightHours, String password) {
        if (authenticate(password)) {
            this.flightHours = flightHours;
        }
    }

    // Static methods to set the airline and fuel capacity (requires password)
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


    private static boolean authenticateStatic(String password) {
        
        return password.equals("globalPassword");
    }
}
