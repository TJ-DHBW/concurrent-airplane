package airplane.actors;

import airport.StorageSite;
import util.TaskLogger;

import java.util.concurrent.ThreadLocalRandom;

public class ControlHuman implements Runnable {
    private final StorageSite storageSite;

    public ControlHuman(StorageSite storageSite) {
        this.storageSite = storageSite;
    }

    @Override
    public void run() {
        int randomContainerIndex = ThreadLocalRandom.current().nextInt(storageSite.getContainers().size());
        int numObjectsInContainer = storageSite.getContainers().get(randomContainerIndex).countObjects();
        if (numObjectsInContainer > 25) {
            TaskLogger.getLogger().warning("A container has more than 25 objects. Num Objects: " + numObjectsInContainer);
        } else {
            TaskLogger.getLogger().info("All good. The searched container currently holds " + numObjectsInContainer + " objects.");
        }
    }
}
