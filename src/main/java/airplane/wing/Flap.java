package airplane.wing;

import util.TaskLogger;

import java.util.concurrent.CountDownLatch;

public class Flap extends Thread {
    private final CountDownLatch latch;
    private FlapLevel level = FlapLevel.UP;

    public Flap(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            TaskLogger.getLogger().info("Flap@" + Integer.toHexString(hashCode()) + " is waiting for the latch.");
            latch.await();
            this.level = FlapLevel.DOWN;
            TaskLogger.getLogger().info("Flap@" + Integer.toHexString(hashCode()) + " is now DOWN!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
