package SynchBlocksWaitNotify;

import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tickets
{
    int total = 0;
    private Lock lock = new ReentrantLock();
    private TicketHolder ticketHolder = new TicketHolder();

    public void producer() throws InterruptedException
    {
        // synchronised block
        synchronized (this) // this = lock of tickets class
        {
            System.out.println("Producer thread waiting ...");
            boolean value = ticketHolder.love();
            // stop execution at this point continue with program and come back after that // releases ownership of monitor log
            while(value)
            {
                // Must be in a loop to prevent spurious wakeup - (waking up without being notified)
                wait(); // can only called in synchronised code blocks // pauses block of code and comes back when notified
                System.out.println("Resumed");
                System.out.println("Total : " + total);
                value = false;
            }
        }
    }

    public void consumer() throws InterruptedException
    {
        Scanner scan = new Scanner(System.in);
        Thread.sleep(2000);

        // synchronised block
        synchronized (this) // this = lock of tickets class
        {
            System.out.println("Waiting for return key");
            scan.nextLine();
            System.out.println("Return Key Pressed");
            total = total + 10;
            notify(); // lets the wait() know that it can continue with its code block
            //Thread.sleep(5000);
        }
    }
}
