package BookingSynch;

public class BookingCheck
{
    private int ticketAvailability = 10; // number of tickets available for booking event

    // Synchronised method
    // allowing only one thread at a time to access this method - provides consistent data of ticketAvailability varaible
    public synchronized void ticketChecking(String name, int ticketRequests)
    {
        if(ticketRequests <= 0) // must enter more than one ticket
        {
            System.out.println("You have entered no tickets");
        }
        else if(ticketAvailability >= ticketRequests && ticketAvailability > 0) // do we have enough tickets to match request
        {
            System.out.println("Hello, " + name + ", your " + ticketRequests + " ticket requests have been successful");
            ticketAvailability = ticketAvailability - ticketRequests;
        }
        else // if ticket request can NOT be matched do this
        {
            System.out.println("Hello, " + name + ", your " + ticketRequests + " ticket requests have been unsuccessful, we only have "
                    + ticketAvailability + " left");
        }
    }
}
