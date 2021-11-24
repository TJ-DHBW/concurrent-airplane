package airplane.body;

import config.Configuration;
import util.TaskLogger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class EntryDoor implements Runnable {
    private final CyclicBarrier cyclicBarrier;
    private boolean isOpen = true;
    private final int id;

    public EntryDoor(int id, CyclicBarrier barrier) {
        this.id = id;
        this.cyclicBarrier = barrier;
    }

    public void closeDoor() throws InterruptedException {
        TaskLogger.getLogger().info("EntryDoor " + id + " started closing");
        int timeToClose = Configuration.instance.r.nextInt(3);
        Thread.sleep(1000 * (timeToClose + 1));
        isOpen = false;
        TaskLogger.getLogger().info("EntryDoor " + id + " is now closed");
    }

    @Override
    public void run() {
        try {
            closeDoor();
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
