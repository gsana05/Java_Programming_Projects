package BookingSynch;

public class BookingSynchMain
{
    public static void main (String args[])
    {
        BookingCheck bookingCheck = new BookingCheck();

        Booking obj = new Booking(bookingCheck,"Chris", 4); // initialise the object before it is created
        Thread t1 = new Thread(obj); // creating a thread of obj as it implements runnable

        Booking obj1 = new Booking(bookingCheck,"John", 4); // initialise the object before it is created
        Thread t2 = new Thread(obj1); // creating a thread of obj as it implements runnable

        Booking obj2 = new Booking(bookingCheck,"Peter", 5); // initialise the object before it is created
        Thread t3 = new Thread(obj2); // creating a thread of obj as it implements runnable

        t1.start(); // starts the thread by running the "RUN method"
        t2.start(); // starts the thread by running the "RUN method"
        t3.start(); // starts the thread by running the "RUN method"

        // JOIN : ALL THREADS MUST COMPLETE BEFORE MOVING ON TO THE PRINTLN
        // WITHOUT JOIN IT WOULD PRINTLN FIRST
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }


        System.out.println("End of program");
    }
}
