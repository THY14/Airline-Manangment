
import java.util.ArrayList;
public class Flight<Person> extends TravelEntity {
	private boolean delayed;
	private int bookedEconomy;
	private int bookedBusiness;
	private ArrayList <Person> persons;
	

    public Flight(int bookedBusiness, int bookedEconomy, boolean delayed, ArrayList<Person> persons, String airplane_Id, String arrivalLocation, String arrivalTime, String departureLocation, String departureTime, String flight_Id) {
        super(airplane_Id, arrivalLocation, arrivalTime, departureLocation, departureTime, flight_Id);
        this.bookedBusiness = bookedBusiness;
        this.bookedEconomy = bookedEconomy;
        this.delayed = delayed;
        this.persons = persons;
    }
	
    public boolean isDelayed() {
        return delayed;
    }

    public void setDelayed(boolean delayed) {
        if (this.delayed == delayed) {
            System.out.println("No change: Flight delay status is already set to " + delayed);
        } else {
            this.delayed = delayed;
            if (delayed) {
                System.out.println(" Flight is now marked as delayed.");
            } else {
                System.out.println(" Flight is now on time.");
            }
        }
    }

    public int getBookedEconomy() {
        if (bookedEconomy == 0) {
            System.out.println("No economy seats have been booked yet.");
        }
        return Math.max(bookedEconomy, 0); // Ensures it never returns a negative number
    }

    public void setBookedEconomy(int bookedEconomy, int economyCapacity) {
        if(bookedEconomy <= economyCapacity){
            this.bookedEconomy = bookedEconomy;
        } else {
            System.out.println("Not enough economy seats available");
        }
    }

    public int getBookedBusiness() {
        if (bookedBusiness == 0) {
            System.out.println("No business seats have been booked yet.");
        }
        return Math.max(bookedBusiness, 0);
    }

    public void setBookedBusiness(int bookedBusiness, int businessCapacity) {
        if(bookedBusiness <= businessCapacity){
            this.bookedBusiness = bookedBusiness;
        } else {
            System.out.println("Not enough business seats available");
        }
    }
    public int getTotalPassengers() {
        return bookedEconomy + bookedBusiness;
    }

    public String checkFlightStatus() {
        return delayed ? "Flight is delayed" : "Flight is on time";
    }

    
    
    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        if (persons == null) {
            System.out.println("Error: Person list cannot be null.");
        } else {
            this.persons = persons;
        }
    }
    public void DisplayFlightInfo() {
        super.DisplayInfo();
        System.out.println("Booked Economy: " + bookedEconomy);
        System.out.println("Booked Business: " + bookedBusiness);
        System.out.println("Flight Status: " + checkFlightStatus());
    }

    
}
    

