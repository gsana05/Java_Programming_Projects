package Executors;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Activity
{
    // AWAIT AND SIGNAL
    private int count = 0;
    // A better alternative instead of using Synchronised keyword - reentrant lock has more built in methods
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition(); // allows for await, signal and signalAll

    private void increment()
    {
        for(int i = 0; i < 10000; i++)
        {
            count++;
        }
    }
    /*
    LOCKS:
    Fairness: The lock goes to the thread that has been waiting the longest - unlike synchronised a thread could wait a very long time
    Reentrant: allows a thread to enter a lock more than once - synchronised only allows once
     */

    public void firstTread() throws InterruptedException
    {
        boolean value = false;
        lock.lock();
        System.out.println("Waiting....");
        while(!value)
        {
            /*
            Needs to be in a loop to prevent SPURIOUS WAKEUP (thread wakes up without being signalled)
            await - goes to "signal"
             */
            cond.await();
            System.out.println("COND AWAIT LOOP");
            value = true;
        }

        System.out.println("Woken up");
        try {
            increment();
        } finally {
            lock.unlock(); // always put unlock in try and final so if code fails the lock still gets unlocked regardless
        }
    }

    public void secondTread() throws InterruptedException
    {
        Thread.sleep(1000);
        lock.lock();

        System.out.println("Press return key");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key");

        cond.signal(); // goes back to thread called await

        try {
            increment();
        } finally {
            lock.unlock(); // always put unlock in try and final so if code fails the lock still gets unlocked regardless
        }
    }

    public void finished()
    {
        System.out.println("count is : " + count);
    }

}
