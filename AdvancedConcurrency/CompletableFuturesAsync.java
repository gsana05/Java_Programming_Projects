package AdvancedConcurrency;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

 /*
   ASYNCHRONOUS - NON-BLOCKING
   Completable Futures - use "forkJoinPool.commonPool" by default - you can specify to use your own thread pool or executor
   Completes all task asynchronously and when all are completed the output is delivered FAST
   - as oppose to synchronous it completes tasks sequentially and makes the others wait until current task is completed SLOW
 */

public class CompletableFuturesAsync
{
    private int idNumber;
    private String name;


    public CompletableFuture<String> calculateAsynce()
    {
        Scanner scan = new Scanner(System.in);
        String surname = "Jones";

        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        try {

            System.out.println("METHOD Thread : " + Thread.currentThread().getName());
            // Completes all task asynchronously and when all are completed the output is delivered FAST
            completableFuture = CompletableFuture.supplyAsync(() -> surname)
                    // chaining: each chain/task executes asynchronously
                    // - the returns from the calls can return in a different order than the way they were created - FAST
                    // order food and get given a number(future) and some point in the future food will arrive(completeFuture)
                    .thenApply(s -> s + " is your name")
                    .thenApply(s -> s + lastName())
                    .thenApplyAsync(s -> s + applyAsyncMeth()) // thenApplyAsync gets executed by another thread
                    .thenCompose(s->CompletableFuture.supplyAsync(()->s+" and you love Liverpool. - "))
                    .thenCombine(CompletableFuture.supplyAsync(()-> " - random number: " + ID()), (s1,s2)->s1+s2);

            String name = null;
            CompletableFuture<String> completableFuture101 = CompletableFuture.supplyAsync(()->{
                if(name == null){
                    throw new RuntimeException("How to handle errors  - example name is not enetered !!!");
                }
                return "Hello,"+name;
            }).handle((s,t)->s!=null?s:"Hello,Stranger!! - error handling if user does not enter name");

            System.out.println(completableFuture101.get());


            //System.out.println("METHOD name: " + completableFuture.get());

            // Complete: at some point in the future execute, once all tasks have been completed to set the value. - Placeholder
            //completableFuture.complete(completableFuture.get());

            // CompleteFuture: gets the value
            String text = completableFuture.get();
            completableFuture = CompletableFuture.completedFuture(text);
            //System.out.println("METHOD -> " + completableFuture.get());


            completableFuture.cancel(true); // only executes if thread does not execute successfully otherwise ignore

        } catch (Exception e) {
            e.printStackTrace();
        }
        return completableFuture;
    }

    public String lastName()
    {
        String surname = "(surname)";
        return surname;
    }

    public String applyAsyncMeth()
    {
        String line = "| thenApplyAsync |";
        return line;
    }

    public int ID()
    {
        // This is how to generate random number when using threads - much faster than Random rand = new Random();
        idNumber = ThreadLocalRandom.current().nextInt(100);
        //System.out.println("ID NUMBER : " + idNumber);
        return idNumber;
    }
}

