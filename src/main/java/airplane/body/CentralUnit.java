package airplane.body;

import airplane.actors.Counter;

public class CentralUnit {
    private final Counter counter;
    private int countMessageNormal;
    private int countMessageWarning;
    private int countMessageAlarm;

    public CentralUnit(Counter counter) {
        this.counter = counter;
    }

    public Counter getCounter() {
        return counter;
    }
}
