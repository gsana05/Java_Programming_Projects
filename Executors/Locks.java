package Executors;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

class LockSenior implements Runnable
{
    private CountDownLatch latch;
    private Activity activity;

    public LockSenior(Activity activity, CountDownLatch latch)
    {
        this.activity = activity;
        this.latch = latch;
    }

    @Override
    public void run()
    {
        System.out.println("Run method - LOCK SENIOR");
        System.out.println("Thread name RUN 1 : " + Thread.currentThread().getName());

        try {
            activity.firstTread();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        // when this thread is competed this reduces the latch by one
        latch.countDown();
    }
}

// The same as RUN method - the difference is that it returns a value using futures
class LockJunior implements Callable<Integer> {
    private CountDownLatch latch;
    private Activity activity;

    public LockJunior(Activity activity, CountDownLatch latch) {
        this.activity = activity;
        this.latch = latch;
    }

    // The same as RUN method - the difference is that it returns a value using futures
    @Override
    public Integer call() throws Exception
    {
        System.out.println("Run method - LOCKS JUNIOR");
        System.out.println("Thread name RUN 2 : " + Thread.currentThread().getName());

        try {
            activity.secondTread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // when this thread is competed this reduces the latch by one
        latch.countDown();
        return 25;
    }
}

