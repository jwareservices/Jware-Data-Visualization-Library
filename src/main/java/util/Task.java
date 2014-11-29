package util;

import java.util.concurrent.Executor;

/**
 * * * @author J. Paul Jackson pjackson@vnet.net<p>
 * @version 1.3	July 8, 1998
 * <p>
 * <p>
 * @version 2.0 August, 2014
 * <p>
  */
public abstract class Task extends Object implements Runnable {

    protected Executor executor;
    boolean async;
    int cycle;

    /**
     * * Constructor: creates, initializes and starts the tasks thread. * async
     * Tells this thread to run forever or not. * cycle is how long to sleep.
     * @param async
     * @param cycle
     */
    public Task(boolean async, int cycle) {
        this.async = async;
        this.cycle = cycle;
        executor = new Executor() {

            @Override
            public void execute(Runnable r) {
            }
        };

    }

    /**
     *
     */
    @Override
    public void run() {
        do {
            doTask();
            try {
                Thread.sleep(cycle);
            } catch (InterruptedException e) {
                return;
            }
        } while (async);
    }

    public abstract void doTask();
}
