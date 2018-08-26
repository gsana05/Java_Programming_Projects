package TrafficLights;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficClassMain
{
    public static void main (String args[])
    {
        activity obj = new activity();

       Thread t1 = new Thread(new Runnable() {
           @Override
           public void run() {
               System.out.println("Thread ONE");
               obj.one();
           }
       });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread TWO");
                obj.two();
            }
        });


        t1.start();
        t2.start();
    }
}


class activity
{
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();
    private String lightsOne = "RED";
    private String lightTwo = "GREEN";

    public void one()
    {
        while(true)
        {
            lock.lock();
            try{
                cond.await();
            } catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            try {
                if(lightsOne.equals("RED"))
                {
                    System.out.println("ONE :-------------- " + lightsOne);
                    lightsOne = "AMBER";
                }
                else if(lightsOne.equals("AMBER"))
                {
                    System.out.println("ONE :------------ " + lightsOne);
                    lightsOne = "GREEN";
                }
                else if(lightsOne.equals("GREEN"))
                {
                    System.out.println("ONE :------------- " + lightsOne);
                    lightsOne = "RED";
                }

            } finally {
                lock.unlock();
            }
        }

    }

    public void two()
    {
        while(true)
        {
            try {
                Thread.sleep(2000);
            } catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            lock.lock();
            try {
                if(lightTwo.equals("GREEN"))
                {
                    System.out.println("TWO : " + lightTwo);
                    lightTwo = "AMBER";
                    cond.signal();
                }
                else if(lightTwo.equals("AMBER"))
                {
                    System.out.println("TWO : " + lightTwo);
                    lightTwo = "RED";
                    cond.signal();
                }
                else if(lightTwo.equals("RED"))
                {
                    System.out.println("TWO : " + lightTwo);
                    lightTwo = "GREEN";
                    cond.signal();
                }

            } finally {
                try {
                    Thread.sleep(2000);
                } catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }
    }
}

