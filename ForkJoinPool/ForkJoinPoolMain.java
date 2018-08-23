package ForkJoinPool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.LongAdder;

public class ForkJoinPoolMain
{
    public static void main (String args[])
    {
        System.out.println("Book a driving test - each task automatically only applies for one test");

        // GET THE NUMBER OF CPU CORES AVAILABLE ON DEVICE
        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of cpu cores : " + coreCount);
        /*
        INTERFACE:
        ForkJoinPool implements the Executor Service interface.
        ForkJoinTask extends Future class - therefore has access to the methods

        FORK JOIN POOL INVOKES FORK JOIN TASK (ForkJoinPool gets access to everything in ForkJoinTask)
        ForkJoinTASK has two subclasses:
        RecursiveAction(returns null) and RecursiveTask(returns value) - gives access to the method "invoke" which calls "compute"

        HOW FORK JOIN POOL WORKS:
        Divide and conquer recursive algorithm:
        1. Gets tasks from blocking queue(synchronised) and if that task is large, it is converted into smaller sub-tasks - (DIVIDE/FORK)
        the smaller sub-task are put in a double ended queue known as "deque"
        2. These sub-tasks are then executed in parallel by the threads
           - if a thread completes their tasks and another thread is still doing tasks, the thread that has done their tasks
             will go and help the other thread complete their tasks. This process is the "WORK STEALING ALGORITHM" -
             The thread that has gone to help will execute tasks from the bottom of the deque whilst the other thread continues
             to execute from the top.
        3. The sub-task are then put back together, once execution is completed (CONQUER/FORK)
        4. The result is then displayed

       IT IS BETTER THAN THREAD POOLS BUT WHY?
       - "WORK STEALING ALGORITHM" More efficient as when a program is running no thread goes into waiting state - improve speed performance
       - Take away the chance od deadlock occurring:
         If executor has fixed number of threads, it might deadlock if the last running thread waits for another task to complete
         */
        ForkJoinPool pool = new ForkJoinPool(coreCount);

        // creating an object
        Booking booking = new Booking();

        /*
        LongAdder is better way to track number of tasks running than AtomicLong
        - AtomicLong is slower because synchronisation is required every time, as different threads access the same variable to increment (high contention)
        - LongAdder - each thread gets it own variable to increment no synchronisation required (Low contention)
                      When you call "sum" of all the thread variable then it uses synchronisation only once
        - LongAccumulator?
         */
        LongAdder counter = new LongAdder();

        // TASKS TO BE EXECUTED
        // invoke calls compute method // execute in parallel
        int app1 = pool.invoke(new forkJoinPractice(counter,booking,"John", 29));
        System.out.println("app1: " + app1);

        int app2 = pool.invoke(new forkJoinPractice(counter,booking,"Katie", 24));
        System.out.println("app2 : " + app2);

        int app3 = pool.invoke(new forkJoinPractice(counter,booking,"Luke", 16));
        System.out.println("app3: " + app3);

        System.out.println("MAIN Pool size: " + pool.getPoolSize());
        System.out.println("MAIN Parallelism : " + pool.getParallelism());

        // LongAdder sum the only time is uses synchronisation to add up all tasks that have run
        System.out.println("LongAdder : " + counter.sum());

    }
}

// Recursive task returns a value
class forkJoinPractice extends RecursiveTask<Integer>
{
    private final LongAdder counter;
    private Booking booking;
    private String name;
    private int age;

    // constructor
    public forkJoinPractice(LongAdder counter, Booking booking, String name, int age)
    {
        this.counter = counter;
        this.booking = booking;
        this.name = name;
        this.age = age;
    }

    // invoke from main call this method
    @Override
    // PROTECTED: Can be accessed within all classes - the opposite to PRIVATE
    protected Integer compute()
    {
        // longAdder (task counter)
        counter.increment();

        System.out.println("Pool size compute: " + getPool());
        System.out.println("Thread name Compute : " + Thread.currentThread().getName());

        booking.book(name, age);

        return 1;
    }
}

/*
Synchronised without using keyword because ForkPoolJoin uses blocking queue (linked blocking queue) which is synchronised
 */
class Booking
{
    private int spaces = 5;

    public void book(String name, int age)
    {
        System.out.println("Thread name book method : " + Thread.currentThread().getName());
        if(age < 18)
        {
            System.out.println("Hi, " + name + " Sorry you are too young to take a driving test");
        }

        if(age > 18)
        {
            System.out.println("Hi, " + name + " You are old enough to do a driving test");
            if(spaces >= 1)
            {
                spaces = spaces - 1;
                System.out.println(name + " = you driving test has been booked" + " we have " + spaces + " left");
            }
            else
            {
                System.out.println(" Sorry we have no driving tests ");
            }
        }
        System.out.println();
    }
}
