package AdvancedConcurrency;

import java.util.concurrent.*;

public class AdvancedConcurrencyMain
{
    public static void main (String args[]) throws InterruptedException, ExecutionException
    {
        //// STAGE ONE - SYNCHRONOUS - BLOCKING I/0 SLOW - RECURSIVE TASK///////////////////////////////////////////////////////////////
        System.out.println(" ");
        System.out.println("STAGE ONE -SYNCHRONOUS - BLOCKING I/O RECURSIVE TASK");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("Advanced Concurrency");
        int coreCount = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(coreCount);
        int userInput = pool.invoke(new StartProgRecursiveTaskSync()); // Synchronous - blocking I/O - waits for return value before continuing
        System.out.println("User entered: " + userInput);

        if(userInput == 2) // program continues if user enters 2
        {
            System.out.println("Play ball");
        }
        else if(userInput == 3) // program ends here if user chooses 3
        {
            System.out.println("Lets end the program right now");
            System.out.println("End of program");
            System.exit(0);
        }
        //// END OF STAGE ONE SYNCHRONOUS //////////////////////////////////////////////////////////////////////////

        //// STAGE TWO ASYNCHRONOUS - NON-BLOCKING I/0 FAST - COMPLETABLE FUTURES ////////////////////////////////////////////////////////
        /*
        Completable Futures - use "forkJoinPool.commonPool" by default - you can specify to use your own thread pool or executor
         */

        System.out.println(" ");
       System.out.println("NEXT STAGE TWO -COMPLETABLE FUTURES - ASYNCHRONOUS - THREAD LOCAL RANDOM");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        // CompleteFuture gets the value and is ready to do something with it
        CompletableFuture<String> completeFuture = CompletableFuture.completedFuture("CompletableFutures for Asynchronous programming BASICS");
        String text = completeFuture.get();
        System.out.println("TEXT : " + text);
        ///////////////////////////////////////////////////////////

        CompletableFuturesAsync completableFuturesAsync = new CompletableFuturesAsync();
        CompletableFuture<String> completableFuture1 = completableFuturesAsync.calculateAsynce();
        String name = completableFuture1.get();
        System.out.println("MAIN name: " + name);
        System.out.println("MAIN Thread : " + Thread.currentThread().getName());

        System.out.println(" ");
        System.out.println("STAGE THREE - STAMPED LOCK SYNCHRONISATION");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        StampLockMethods stampLockMethodsInStampedLocksSync = new StampLockMethods();

        // creating an object of same class by activating different constructors
        pool.invoke(new StampedLocksSync(stampLockMethodsInStampedLocksSync, "john", 6));
        pool.invoke(new StampedLocksSync(stampLockMethodsInStampedLocksSync, "Peter", 3));

        pool.invoke(new StampedLocksSync(stampLockMethodsInStampedLocksSync));

        System.out.println(" ");
        System.out.println("STAGE FOUR - EXECUTOR SERVICE - THREAD COMMUNICATION - PHASER");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        ThreadCommunication obj_communication = new ThreadCommunication();
        ExecutorService service = Executors.newFixedThreadPool(2);
        Phaser phaser = new Phaser(2);

        service.submit(new A_threadCommunication(obj_communication, phaser));
        service.submit(new B_threadCommunication(obj_communication, phaser));

        //Phaser (behaving as a CountDownLatch) // makes threads/task finish executing before continuing
        phaser.arriveAndAwaitAdvance();

        obj_communication.Results();
        service.shutdown();

        System.out.println("Hey, end of program");
    }
}




