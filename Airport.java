public class Airport {
    private String code ;
    private String name;
    private String location;
    private int openyear;
    private String country;
    private static long annualFlights;
    private double totalPassengerTraffic;
    private boolean international;

    public Airport(String code, String name, String location, int openyear, String country, double totalPassengerTraffic, boolean international) {
        annualFlights++;
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
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public int getOpenyear() {
        return openyear;
    }
    

    public void setOpenyear(int openyear) {
        this.openyear = openyear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public static long getAnnualFlights() {
        return annualFlights;
    }
    public double getAveragePassengerTraffic() {
        return totalPassengerTraffic / annualFlights;
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
}
