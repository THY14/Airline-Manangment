import java.time.LocalDate;
import java.util.ArrayList;

class Booking {
    private static int bookingId = 0;
    private String origin;
    private String destination;
    private LocalDate depart;
    private int numOfPassengers;
    private String seatClass;
    private ArrayList<String> flightIDs;
    private double price;
    private double totalPrice;

    public Booking(String origin, String destination, LocalDate depart, int numOfPassengers, String seatClass, double price) {
        bookingId = bookingId + 1 ;
        this.origin = origin;
        this.destination = destination;
        this.depart = depart;
        setNumOfPassengers(numOfPassengers);
        this.seatClass = seatClass;
        this.flightIDs = new ArrayList<>();
        setPrice(price);
    }

    public int getId() { return bookingId; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public LocalDate getDepart() { return depart; }
    public int getNumOfPassengers() { return numOfPassengers; }
    public String getSeatClass() { return seatClass; }
    public ArrayList<String> getFlightIDs() { return flightIDs; }
    public double getPrice() { return price; }
    public double getTotalPrice() { return totalPrice; }

    public void setNumOfPassengers(int numOfPassengers) {
        if (numOfPassengers > 0) {
            this.numOfPassengers = numOfPassengers;
        } else {
            System.out.println ("Number of passengers must be greater than zero.");
        }
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
            this.totalPrice = price * numOfPassengers;
        } else {
            System.out.println ("Price cannot be negative.");
        }
    }

    public void addFlightID(String flightID) { this.flightIDs.add(flightID); }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Origin: " + origin + ", Destination: " + destination +
               ", Departure: " + depart + ", Passengers: " + numOfPassengers + ", Total Price: $" + totalPrice;
    }

}

// One-way booking
class OneWayBooking extends Booking {
    public OneWayBooking(String origin, String destination, LocalDate depart, int numOfPassengers, double price, String seatClass) {
        super(origin, destination, depart, numOfPassengers, seatClass, price);
    }
}

// Round-trip booking
class RoundTripBooking extends Booking {
    private LocalDate returnDate;
    private String returnSeatClass;

    public RoundTripBooking(String origin, String destination, LocalDate depart, LocalDate returnDate, int numOfPassengers, double price, String seatClass, String returnSeatClass) {
        super(origin, destination, depart, numOfPassengers, seatClass, price);
        if (returnDate.isBefore(depart)) {
            throw new IllegalArgumentException("Return date cannot be before departure date.");
        }
        this.returnDate = returnDate;
        this.returnSeatClass = returnSeatClass;
    }

    public LocalDate getReturnDate() { return returnDate; }
    public String getReturnSeatClass() { return returnSeatClass; }
}

// Multi-way booking
class MultiWayBooking extends Booking {
    private ArrayList<String> destinations;
    private ArrayList<LocalDate> travelDates;

    public MultiWayBooking(String origin, int numOfPassengers, double price, String seatClass) {
        super(origin, "", null, numOfPassengers, seatClass, price);
        this.destinations = new ArrayList<>();
        this.travelDates = new ArrayList<>();
    }

    public void addDestination(String destination, LocalDate travelDate) {
        destinations.add(destination);
        travelDates.add(travelDate);
    }

    public ArrayList<String> getDestinations() { return destinations; }
    public ArrayList<LocalDate> getTravelDates() { return travelDates; }
}
