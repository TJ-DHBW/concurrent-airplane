package airplane.body;

import config.Configuration;
import util.TaskLogger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Engine implements Runnable {
    private final int id;
    private boolean hasStarted = false;
    private final CyclicBarrier cyclicBarrier;
    private int rotationSpeed;
    private final int rotationSpeedReadyForDeparture = 500;
    private final int rotationSpeedTrustSet = 2400;
    private final int rotationSpeedV1 = 4400;
    private final int rotationSpeedClimb = 6000;

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

    public void readyForDeparture() throws InterruptedException {
        rotationSpeed = rotationSpeedReadyForDeparture;
        TimeUnit.SECONDS.sleep(3);
    }

    public void trustSet() throws InterruptedException {
        rotationSpeed = rotationSpeedTrustSet;
        TimeUnit.SECONDS.sleep(3);
    }

    public void v1() throws InterruptedException {
        rotationSpeed = rotationSpeedV1;
        TimeUnit.SECONDS.sleep(3);
    }

    public void climb() throws InterruptedException {
        rotationSpeed = rotationSpeedClimb;
        TimeUnit.SECONDS.sleep(3);
    }

    public void synchronisedTakeOff(Phaser phaser) throws InterruptedException {
        phaser.register();

        phaser.arriveAndAwaitAdvance();
        readyForDeparture();
        TaskLogger.getLogger().info("Engine " + id + " is in phase: ReadyForDeparture with rpm " + rotationSpeed);

        phaser.arriveAndAwaitAdvance();
        trustSet();
        TaskLogger.getLogger().info("Engine " + id + " is in phase: TrustSet with rpm " + rotationSpeed);

        phaser.arriveAndAwaitAdvance();
        v1();
        TaskLogger.getLogger().info("Engine " + id + " is in phase: V1 with rpm " + rotationSpeed);

        phaser.arriveAndAwaitAdvance();
        climb();
        TaskLogger.getLogger().info("Engine " + id + " is in phase: Climb with rpm " + rotationSpeed);

        phaser.arriveAndAwaitAdvance();
        TaskLogger.getLogger().info("Engine " + id + ": TakeOff complete");
        phaser.arriveAndDeregister();
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
