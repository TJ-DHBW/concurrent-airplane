package airplane.body;

import airplane.Airplane;
import airplane.actors.Counter;
import airplane.body.sensor.Sensor;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class CentralUnit implements Runnable {
    private final Counter counter;
    private final AtomicInteger countMessageNormal = new AtomicInteger();
    private final AtomicInteger countMessageWarning = new AtomicInteger();
    private final AtomicInteger countMessageAlarm = new AtomicInteger();
    private Airplane airplane;

    public CentralUnit(Counter counter) {
        this.counter = counter;
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

    @Override
    public void run() {
        ArrayList<Sensor> sensors = airplane.getBody().getSensors();
        for (Sensor sensor : sensors) {
            Thread thread = new Thread(sensor);
            thread.start();
        }
    }
}
