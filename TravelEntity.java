public class TravelEntity {
    protected final String flight_Id;
    protected final String airplane_Id;
    protected String departureLocation;
    protected String arrivalLocation;
    protected String departureTime;
    protected String arrivalTime;

    public TravelEntity(String airplane_Id, String arrivalLocation, String arrivalTime, String departureLocation, String departureTime, String flight_Id) {
        if (airplane_Id == null || airplane_Id.trim().isEmpty()) throw new IllegalArgumentException("Airplane ID cannot be null or empty");
        if (arrivalLocation == null || arrivalLocation.trim().isEmpty()) throw new IllegalArgumentException("Arrival location cannot be null or empty");
        if (arrivalTime == null || arrivalTime.trim().isEmpty()) throw new IllegalArgumentException("Arrival time cannot be null or empty");
        if (departureLocation == null || departureLocation.trim().isEmpty()) throw new IllegalArgumentException("Departure location cannot be null or empty");
        if (departureTime == null || departureTime.trim().isEmpty()) throw new IllegalArgumentException("Departure time cannot be null or empty");
        if (flight_Id == null || flight_Id.trim().isEmpty()) throw new IllegalArgumentException("Flight ID cannot be null or empty");
        this.airplane_Id = airplane_Id;
        this.arrivalLocation = arrivalLocation;
        this.arrivalTime = arrivalTime;
        this.departureLocation = departureLocation;
        this.departureTime = departureTime;
        this.flight_Id = flight_Id;
    }

    public void DisplayInfo() {
        System.out.println("Flight ID: " + flight_Id);
        System.out.println("Airplane: " + airplane_Id);
        System.out.println("Departure Location: " + departureLocation + " at " + departureTime);
        System.out.println("Arrival Location: " + arrivalLocation + " at " + arrivalTime);
    }
}