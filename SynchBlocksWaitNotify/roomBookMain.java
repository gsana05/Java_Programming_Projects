package SynchBlocksWaitNotify;

public class roomBookMain
{
    public static void main(String[] args)
    {
        final Tickets tickets = new Tickets();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    tickets.producer();
                } catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    tickets.consumer();
                } catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        } catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}