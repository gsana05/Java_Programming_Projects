package BookingSynch;

public class Booking implements Runnable
{
    private BookingCheck bookingCheck; // Getting the class
    private String name;
    private int numberOfTickets;

    // constructor - gets initialised before object is created
    public Booking(BookingCheck bookingCheck, String name, int numberOfTickets)
    {
        this.bookingCheck = bookingCheck;
        this.name = name;
        this.numberOfTickets = numberOfTickets;
    }

    // When start is called in main - it call this "RUN METHOD"
    @Override
    public void run()
    {
        System.out.println("Run Method");
        bookingCheck.ticketChecking(name, numberOfTickets); // calling method
    }
}
