class Booking {
    private String origin;
    private String destination;
    private String depart;
    private String returnDate; //For round trip
    private int numOfPassenger;

    private static int bookingId = 100000 ;
    private String flightID;
    private String seatClass;
    private String returnFlightNumber; //For round trip
    private String returnSeatClass;
    private double price; //Per passenger
    private double totalPrice; 

    //For one way ticket
    public Booking(String origin, String destination, String depart, int numOfPassenger, String seatClass){
        bookingId = bookingId + 1;
        this.origin = origin;
        this.destination = destination;
        this.depart = depart;
        this.numOfPassenger = numOfPassenger;
        this.seatClass = seatClass;
    }

    //For round trip
    public Booking(String origin, String destination, String depart, String returnDate, int numOfPassenger, String seatClass, String returnSeatClass){
        bookingId = bookingId + 1;
        this.origin = origin;
        this.destination = destination;
        this.depart = depart;
        this.returnDate = returnDate;
        this.numOfPassenger = numOfPassenger;   
        this.seatClass = seatClass;
        this.returnSeatClass = returnSeatClass;
    }

    public void bookingTicket(){
        if(returnDate.length() == 0){
            //For round trip
            

        }

    }

}