package airplane.body.sensor;

import config.Configuration;

import java.util.concurrent.Semaphore;

public class Sensor implements Runnable {
    private SensorStatus sensorStatus;
    private Semaphore semaphore;

    public Sensor() {
        int statusInt = Configuration.instance.r.nextInt(2);
        this.sensorStatus = SensorStatus.values()[statusInt];
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
