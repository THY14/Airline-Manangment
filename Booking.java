class Booking {
    private String bookingId;
    private String passengerId;
    private String flightNumber;
    private String seatNumber;
    private String bookingDate;
    private String status;
    private double totalPrice;
    private String paymentMethod;

    // For Return Ticket
    private boolean isRoundTrip;
    private String returnFlightNumber;
    private String returnDate;
    private String returnSeatNumber;

    public Booking(String bookingId, String passengerId, String flightNumber, String seatNumber, String bookingDate, String status, double totalPrice, String paymentMethod, boolean isRoundTrip, String returnFlightNumber, String returnDate, String returnSeatNumber) {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.flightNumber = flightNumber;
        this.seatNumber = seatNumber;
        this.bookingDate = bookingDate;
        this.status = status;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.isRoundTrip = isRoundTrip;
        this.returnFlightNumber = returnFlightNumber;
        this.seatNumber = returnSeatNumber;
        this.bookingDate = returnDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    
}