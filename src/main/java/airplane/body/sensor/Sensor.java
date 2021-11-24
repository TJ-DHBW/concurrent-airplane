package airplane.body.sensor;

import airplane.body.CentralUnit;
import config.Configuration;
import util.TaskLogger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Sensor implements Runnable {
    private SensorStatus sensorStatus;
    private final Semaphore semaphore;
    private final CentralUnit centralUnit;
    private final int id;

    public Sensor(int id, Semaphore semaphore, CentralUnit centralUnit) {
        this.id = id;
        this.semaphore = semaphore;
        this.centralUnit = centralUnit;
    }

    public SensorStatus getSensorStatus() {
        return sensorStatus;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            TaskLogger.getLogger().info("Sensor " + id + " has registered");
            TimeUnit.SECONDS.sleep(2);
            int statusInt = Configuration.instance.r.nextInt(3);
            sensorStatus = SensorStatus.values()[statusInt];
            centralUnit.readValueFromSensor(this);
            TaskLogger.getLogger().info("Sensor " + id + " sent message to central unit");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
