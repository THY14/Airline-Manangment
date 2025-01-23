class Booking {
    private String bookingId;
    private String passengerId;
    private String flightNumber;
    private String seatNumber;
    private String bookingDate;
    private String status;
    private double totalPrice;

    public Booking(String bookingId, String passengerId, String flightNumber, String seatNumber, String bookingDate, String status, double totalPrice) {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.flightNumber = flightNumber;
        this.seatNumber = seatNumber;
        this.bookingDate = bookingDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    
}