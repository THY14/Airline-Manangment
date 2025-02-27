public class Airport {
    private String code ;
    private String name;
    private String location;
    private int openyear;
    private String country;
    private double totalPassengerTraffic;
    private boolean international;

    public Airport(String code, String name, String location, int openyear, String country, double totalPassengerTraffic, boolean international) {
        this.code = code;
        this.name = name;
        this.location = location;
        this.openyear = openyear;
        this.country = country;
        this.totalPassengerTraffic = totalPassengerTraffic;
        this.international = international;
    }
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        if (code != null && !code.trim().isEmpty() && code.length() == 3) {
            this.code = code;
        } else {
            System.out.println("Invalid code: Code must be a 3-letter string.");
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid name: Name cannot be empty.");
        }
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        if (location != null && !location.trim().isEmpty()) {
            this.location = location;
        } else {
            System.out.println("Invalid location: Location cannot be empty.");
        }
    }
    public int getOpenyear() {
        return openyear;
    }
    public void setOpenyear(int openYear) {
        if (openYear > 1900 && openYear <= 2025) {
            this.openyear = openYear;
        } else {
            System.out.println("Invalid open year: Year must be between 1900 and 2025.");
        }
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        if (country != null && !country.trim().isEmpty()) {
            this.country = country;
        } else {
            System.out.println("Invalid country: Country cannot be empty.");
        }
    }
    public void setTotalPassengerTraffic(double totalPassengerTraffic, double passengerIncrease) {
        this.totalPassengerTraffic = totalPassengerTraffic +  passengerIncrease;
    }
    public double getTotalPassengerTraffic() {
        return totalPassengerTraffic;
    }
    public boolean isInternational() {
        return international;
    }
    public void setInternational(boolean international) {
        this.international = international;
    }
    public static void main(String[] args){
        Airport airport = new Airport("NYC", "New York City", "New York", 1920, "USA", 10000000, true);
        System.out.println("Airport Details:");
        System.out.println("Code: " + airport.getCode());
        System.out.println("Name: " + airport.getName());
        System.out.println("Location: " + airport.getLocation());
        System.out.println("Open Year: " + airport.getOpenyear());
        System.out.println("Country: " + airport.getCountry());
        System.out.println("Total Passenger Traffic: " + airport.getTotalPassengerTraffic());
    }
}
