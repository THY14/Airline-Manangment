import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {
    private int id;
	private Airplane airplane;
	private Airport origin;
	private Airport destination;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private boolean delayed;
	private int bookedEconomy;
	private int bookedBusiness;
	private Employee[] Employees;
	private Passenger[] passengers;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd::HH:mm:ss");


    public Flight(){
        delayed = false;
        bookedEconomy = 0;
        bookedBusiness = 0;
        Employees = new Employee[10];
    }


    

}
