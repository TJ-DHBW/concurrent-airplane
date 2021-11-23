package airplane.body;

import airplane.actors.Counter;

public class CentralUnit {
    private Counter counter;
    private int countMessageNormal;
    private int countMessageWarning;
    private int countMessageAlarm;

    public CentralUnit() {
        this.counter = new Counter();
    }

    public Counter getCounter() {
        return counter;
    }
}
