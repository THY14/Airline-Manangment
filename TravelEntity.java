abstract public class TravelEntity {
    protected String flight_Id;
    protected String airplane_Id;
    protected String departureLocation;
    protected String arrivalLocation;
    protected String departureTime;
    protected String arrivalTime;

    public TravelEntity(String airplane_Id, String arrivalLocation, String arrivalTime, String departureLocation, String departureTime, String flight_Id) {
        this.airplane_Id = airplane_Id;
        this.arrivalLocation = arrivalLocation;
        this.arrivalTime = arrivalTime;
        this.departureLocation = departureLocation;
        this.departureTime = departureTime;
        this.flight_Id = flight_Id;
    }
    
    public void DisplayInfo(){
        System.out.println("Flight ID: " + flight_Id);
        System.out.println("Airplane: " + airplane_Id);
        System.out.println("Departure Location: " + departureLocation + "at" + departureTime);
        System.out.println("Arrival Location: " + arrivalLocation + "at" + arrivalTime  );
    }

}
