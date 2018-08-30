package AdvancedConcurrency;

import java.util.Scanner;
import java.util.concurrent.Phaser;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCommunication
{
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();
    private int count = 0;


    public void ThreadOne()
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
            try {
                cond.await();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            System.out.println("COND AWAIT LOOP");
            value = true;
        }

        System.out.println("Woken up");
        try {
            count++;
        } finally {
            lock.unlock(); // always put unlock in try and final so if code fails the lock still gets unlocked regardless
        }
    }

    public void ThreadTwo()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("Thread two waiting..");
        lock.lock();

        System.out.println("Press return key");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key");

        cond.signal(); // goes back to thread called await

        try {
            count++;
        } finally {
            lock.unlock(); // always put unlock in try and final so if code fails the lock still gets unlocked regardless
        }
    }

    public void Results()
    {
        System.out.println("THREAD COMMUNICATION RESULT: " + count);
    }
}

class A_threadCommunication implements Runnable
{
    private ThreadCommunication threadCommunication;
    private Phaser phaser;

    public A_threadCommunication(ThreadCommunication threadCommunication, Phaser phaser) {
        this.threadCommunication = threadCommunication;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        threadCommunication.ThreadOne();
        phaser.arrive();
    }
}

class B_threadCommunication implements Runnable
{
    private ThreadCommunication threadCommunication;
    private Phaser phaser;

    public B_threadCommunication(ThreadCommunication threadCommunication, Phaser phaser) {
        this.threadCommunication = threadCommunication;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        threadCommunication.ThreadTwo();
        phaser.arrive();
    }
}

