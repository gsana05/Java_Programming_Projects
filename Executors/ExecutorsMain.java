package Executors;

import java.util.ArrayList;
import java.util.concurrent.*;

public class ExecutorsMain
{
    public static void main (String args[])  throws InterruptedException
    {
        // GET THE NUMBER OF CPU CORES AVAILABLE ON DEVICE
        int coreCount = Runtime.getRuntime().availableProcessors();

        /*
        INTERFACE
        ExecutorService interface extends Executor interface

        LINKED BLOCKING QUEUE
        nexFixedThreadPool - under the surface runs tasks on a LINKED BLOCKING QUEUE (FIFO - Blocking Queue is synchronised)
        only one cpu thread can execute one task at a time:
        If task comes into blocking queue and all cpu threads are busy, the task is put in waiting state until thread becomes free.
        NOT SEQUENTIAL - as many tasks can be executing simultaneously so you will not know the order in which the tasks complete.

        POOL SIZE
        CPU Intensive = same size as the number of cores on device
        IO Intensive (getting data from database) = Can be greater than number of cpu cores on device - LEARN WHY
         */
        // THREAD POOL OF FACTORY WORKERS - SAME THREAD FINISHES A TASK AND START ANOTHER TASK
        ExecutorService service = Executors.newFixedThreadPool(coreCount);

        // instantiate class - (make object of the class)
        Activity activity = new Activity();

        /*
        COUNT DOWN LATCH - class that is thread safe (synchronised) as you can access multiple threads which do NOT cause 'race condition'
        * provides consistent reliable data

        PARAMETERS:
        takes in the number of threads that need to be completed before moving on in the program

        COUNTDOWN()
        subtracts latch by one - when latch is 0 moves on to next stage of program

        AWAIT()
        When this is called no code after this get executed until all threads before have completed
         */
        CountDownLatch latch = new CountDownLatch(2);


        // TASKS THAT NEED EXECUTING //////////////////////
        /*
        RUN:
        used with EXECUTE AND SUBMIT
         */
        service.execute(new LockSenior(activity, latch));
        /*
        FUTURE:
        get a return value from thread

        CALLABLE
        used with SUBMIT
         */
        Future<Integer> future = service.submit(new LockJunior(activity, latch));

        System.out.println("WAITING FOR ALL THREADS TO COMPLETE");
        /// END OF TASKS BEING EXECUTED //////////////////////////////////////

        // await - stop executing - until above threads have completed before continuing
        latch.await();
        System.out.println("ALL THREADS TO COMPLETE");

        // arrayList to hold return value
        ArrayList<Integer> list1 = new ArrayList<>();

        try{
            list1.add(future.get()); // adding return value to array
        } catch(ExecutionException E)
        {
            E.printStackTrace();
        } catch (InterruptedException E)
        {
            E.printStackTrace();
        }


        // printing out arrayList values
        System.out.println("\nReturn value from callable demonstration");
        for(Integer i : list1)
        {
            System.out.println("NUMBER RETURN : " + i);
        }

        // calling method -
        activity.finished();

        /*
        shuts down the program when all tasks have been completed -
        so if a task is currently in running state it will finish after that task has completed and then will shut down
         */
        service.shutdown();

        /*
        This will wait a given amount of time and when the time is up, code below will be executed.
        service.awaitTermination(1, TimeUnit.MICROSECONDS);
        System.out.println("Hello - Testing awaitTermination");
        */
    }
}

