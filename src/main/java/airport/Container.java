package airport;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Container {
    private final ArrayList<CargoObject> content = new ArrayList<>();

    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = reentrantReadWriteLock.readLock();
    private final Lock writeLock = reentrantReadWriteLock.writeLock();

    public boolean store(CargoObject objectToStore) {
        writeLock.lock();
        try {
            if (content.size() >= 25) return false;
            if (content.contains(objectToStore))
                throw new IllegalStateException("Can not store a CargoObject that is already stored!");
            content.add(objectToStore);
            return true;
        } finally {
            writeLock.unlock();
        }
    }

    public int countObjects() {
        readLock.lock();
        try {
            return content.size();
        } finally {
            readLock.unlock();
        }
    }
}
