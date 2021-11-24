package airplane.body.sensor;

import airplane.body.CentralUnit;
import config.Configuration;
import util.TaskLogger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Sensor extends Thread {
    private SensorStatus sensorStatus;
    private Semaphore semaphore;
    private CentralUnit centralUnit;
    private int id;

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
            TimeUnit.SECONDS.sleep(3);
            int statusInt = Configuration.instance.r.nextInt(3);
            sensorStatus = SensorStatus.values()[statusInt];
            centralUnit.readValueFromSensor(this);
            TaskLogger.getLogger().info("Sensor " + id + " sent message to central unit");
            semaphore.release();
            TaskLogger.getLogger().info("Sensor " + id + " has deregistered");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
