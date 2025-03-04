public class TravelEntity {
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("TravelEntity{");
            sb.append("flight_Id=").append(flight_Id);
            sb.append(", airplane_Id=").append(airplane_Id);
            sb.append(", departureLocation=").append(departureLocation);
            sb.append(", arrivalLocation=").append(arrivalLocation);
            sb.append(", departureTime=").append(departureTime);
            sb.append(", arrivalTime=").append(arrivalTime);
            sb.append('}');
            return sb.toString();
        }
    public static void main(String[] args) {
        TravelEntity flight1 = new TravelEntity("Airbus A320", "New York", "10:00 PM", "Los Angeles", "12:00 PM", "12345");
        System.out.println(flight1);
    }
}
