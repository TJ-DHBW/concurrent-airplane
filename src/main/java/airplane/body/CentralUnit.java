package airplane.body;

import airplane.Airplane;
import airplane.actors.Counter;
import airplane.body.sensor.Sensor;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class CentralUnit implements Runnable {
    private final Counter counter;
    private AtomicInteger countMessageNormal = new AtomicInteger();
    private AtomicInteger countMessageWarning = new AtomicInteger();
    private AtomicInteger countMessageAlarm = new AtomicInteger();
    private Semaphore semaphore;
    private int numberOfMessages = 0;
    private Airplane airplane;

    public CentralUnit(Counter counter, Semaphore semaphore) {
        this.counter = counter;
        this.semaphore = semaphore;
    }

    public Counter getCounter() {
        return counter;
    }

    public void readValueFromSensor(Sensor sensor) {
        switch (sensor.getSensorStatus()) {
            case alarm:
                alarmMessage();
                break;
            case normal:
                normalMessage();
                break;
            case warning:
                warningMessage();
                break;
        }
    }


    public void normalMessage() {
        countMessageNormal.incrementAndGet();
    }

    public void warningMessage() {
        countMessageWarning.incrementAndGet();
    }

    public void alarmMessage() {
        countMessageAlarm.incrementAndGet();
    }

    public int getNormalNumber() {
        return countMessageNormal.get();
    }

    public int getWarningNumber() {
        return countMessageWarning.get();
    }

    public int getAlarmNumber() {
        return countMessageAlarm.get();
    }


    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    @Override
    public void run() {
        ArrayList<Sensor> sensors = airplane.getBody().getSensors();
        for (Sensor sensor : sensors) {
            Thread thread = new Thread(sensor);
            thread.start();
        }
    }
}
