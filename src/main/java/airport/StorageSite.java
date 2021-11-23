package airport;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StorageSite {
    private final ArrayList<CargoObject> objects = new ArrayList<>();
    private final ArrayList<Container> containers = new ArrayList<>();

    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock writeLock = reentrantReadWriteLock.writeLock();

    public StorageSite(int numObjects) {
        for (int i = 0; i < numObjects; i++) {
            objects.add(new CargoObject());
        }

        int numContainers = (int) Math.ceil(numObjects / 25.0);
        for (int i = 0; i < numContainers; i++) {
            containers.add(new Container());
        }
    }

    public CargoObject retrieveCargoObject() {
        writeLock.lock();
        try {
            if (objects.isEmpty()) return null;
            return objects.remove(0);
        } finally {
            writeLock.unlock();
        }
    }

    public String getLevels() {
        StringBuilder builder = new StringBuilder();
        builder.append("Objects on site: ").append(objects.size()).append("\n");
        for (int i = 0; i < containers.size(); i++) {
            builder.append("Container #").append(i).append(": ").append(containers.get(i).countObjects()).append("/25\n");
        }
        return builder.toString();
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }
}
