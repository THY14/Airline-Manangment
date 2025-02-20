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
        this.numOfPassengers = numOfPassengers;
        this.seatClass = seatClass;
        this.flightIDs = new ArrayList<>();
        this.price = price;
    }
    public int getId() {
        return bookingId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        if (origin != null && !origin.isEmpty()) { // Check for null or empty
            this.origin = origin;
        } else {
            System.out.println("Origin cannot be null or empty.");
        }
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        if (destination != null && !destination.isEmpty()) {
            this.destination = destination;
        } else {
            System.out.println("Destination cannot be null or empty.");
        }
    }


    public LocalDate getDepart() {
        return depart;
    }

    public void setDepart(LocalDate depart) {
        if (depart != null) {
            this.depart = depart;
        } else {
            System.out.println("Departure date cannot be null.");
        }
    }

    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public void setNumOfPassengers(int numOfPassengers) {
        if (numOfPassengers > 0) {
            this.numOfPassengers = numOfPassengers;
            this.totalPrice = this.price * numOfPassengers; // Recalculate total price
        } else {
            System.out.println("Number of passengers must be greater than zero.");
        }
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        if (seatClass != null && !seatClass.isEmpty()) {
            this.seatClass = seatClass;
        } else {
            System.out.println("Seat class cannot be null or empty.");
        }
    }

    public ArrayList<String> getFlightIDs() {
        return new ArrayList<>(flightIDs); // Return a copy to prevent external modification
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
            this.totalPrice = price * numOfPassengers;
        } else {
            System.out.println("Price cannot be negative.");
        }
    }

    public double getTotalPrice() {
        return totalPrice;
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
            System.out.println ("Return date cannot be before departure date.");
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
