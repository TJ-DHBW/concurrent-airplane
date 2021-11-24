package airplane.body;

import util.TaskLogger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class LandingGear extends Thread {
    private final CountDownLatch latch;
    private boolean isExtended;

    public LandingGear(CountDownLatch latch) {
        this.isExtended = false;
        this.latch = latch;
    }

    public void run() {
        try {
            TaskLogger.getLogger().info("LandingGear@" + Integer.toHexString(hashCode()) + " starting to extend.");
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(2001) + 1000);
            this.isExtended = true;
            TaskLogger.getLogger().info("LandingGer@" + Integer.toHexString(hashCode()) + " finished extending!");
        } catch (InterruptedException ignored) {
        } finally {
            latch.countDown();
        }
    }
}
