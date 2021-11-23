package airplane.actors;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private final AtomicInteger totalNumberOfPassenger = new AtomicInteger();

    public void passengerFound() {
        totalNumberOfPassenger.incrementAndGet();
    }

    public int getTotalNumberOfPassenger() {
        return totalNumberOfPassenger.get();
    }
}
