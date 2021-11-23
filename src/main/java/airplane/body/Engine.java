package airplane.body;

import config.Configuration;
import util.TaskLogger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Engine implements Runnable {
    private int id;
    private boolean hasStarted = false;
    private CyclicBarrier cyclicBarrier;

    public Engine(int id, CyclicBarrier barrier) {
        this.id = id;
        this.cyclicBarrier = barrier;
    }

    public void startEngine() throws InterruptedException {
        TaskLogger.getLogger().info("Engine " + id + " is starting");
        int timeToStart = Configuration.instance.r.nextInt(5);
        Thread.sleep(1000 * (timeToStart + 1));
        hasStarted = true;
        TaskLogger.getLogger().info("Engine " + id + " has started");
    }

    public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            startEngine();
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
