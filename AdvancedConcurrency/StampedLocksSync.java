package AdvancedConcurrency;

import java.util.concurrent.Exchanger;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.locks.StampedLock;

public class StampedLocksSync extends RecursiveAction
{
    // the fastest: reads and writes faster when contention is high performance
    // *** contention: multiple threads waiting for the lock to be unlocked by the current thread using it
    //      *** FAIRNESS: longest waiting thread gets next access to the lock - reentrant lock does not do this and causes thread starvation
    // - synchronised keyword and reentrant lock are slow performance
    private StampLockMethods stampedLockMethods;
    private Exchanger<String> exchanger;
    private String name;
    private int ticketRequests;

    // Constructor overloading
    public StampedLocksSync(StampLockMethods stampedLockMethods, String name, int ticketRequests) {
        this.stampedLockMethods = stampedLockMethods;
        this.name = name;
        this.ticketRequests = ticketRequests;
    }

    // constructor overloading
    public StampedLocksSync(StampLockMethods stampLockMethods)
    {
        this.stampedLockMethods = stampLockMethods;
    }

    @Override
    protected void compute() {
        if(ticketRequests > 0)
        {
            stampedLockMethods.firstThread(name, ticketRequests);
            stampedLockMethods.thirdThread(name);
        }
        else
        {
            stampedLockMethods.secondThread();
        }

    }
}

class StampLockMethods
{
    private int tickets = 5;
    private StampedLock stampedLock = new StampedLock();
    private double totalRevenue = 12.23;

    public void firstThread(String name, int ticketRequests)
    {
        System.out.println("STAMPED LOCK WRITING");
        long stamp = stampedLock.writeLock();
        try {
            System.out.println("First thread");
            if(tickets >= ticketRequests && tickets > 0)
            {
                System.out.println("Hi " + name + ", number of tickets book successfully: " + ticketRequests);
                tickets = tickets - ticketRequests;
            }
            else
            {
                System.out.println("Hi " + name + ", number of tickets is NOT possible : "
                        + ticketRequests + ", we only have " + tickets + " left");
            }
        } finally
        {
            stampedLock.unlockWrite(stamp);
        }

    }

    public void secondThread()
    {
        System.out.println("STAMPED LOCK READING");
        long stamp = stampedLock.readLock();
        try {
            System.out.println("Number of tickets left: " + tickets);
        } finally
        {
            stampedLock.unlockRead(stamp);
        }
    }

    public void thirdThread(String name)
    {
        System.out.println("STAMPED LOCK READING");
        long stamp = stampedLock.readLock();
        try {
            System.out.println("Name of booking: " + name);
        } finally
        {
            stampedLock.unlockRead(stamp);
        }
    }
}
