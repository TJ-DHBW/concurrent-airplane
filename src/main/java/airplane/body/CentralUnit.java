package airplane.body;

import airplane.actors.Counter;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class CentralUnit {
    private final Counter counter;
    private AtomicInteger countMessageNormal = new AtomicInteger();
    private AtomicInteger countMessageWarning = new AtomicInteger();
    private AtomicInteger countMessageAlarm = new AtomicInteger();
    private Semaphore semaphore;

    public CentralUnit(Counter counter) {
        this.counter = counter;
    }

    public Counter getCounter() {
        return counter;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
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
}
