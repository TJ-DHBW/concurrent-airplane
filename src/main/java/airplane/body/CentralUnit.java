package airplane.body;

import airplane.actors.Counter;

public class CentralUnit {
    private Counter counter;

    public CentralUnit() {
        this.counter = new Counter();
    }

    public Counter getCounter() {
        return counter;
    }
}
