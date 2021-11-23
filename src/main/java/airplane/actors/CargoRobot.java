package airplane.actors;

import airport.CargoObject;
import airport.Container;
import airport.StorageSite;
import util.TaskLogger;

public class CargoRobot implements Runnable {
    private final StorageSite storageSite;
    private final int actionDelay = 150;

    public CargoRobot(StorageSite storageSite) {
        this.storageSite = storageSite;
    }

    @Override
    public void run() {
        CargoObject objectToLoad = storageSite.retrieveCargoObject();
        while (objectToLoad != null) {
            Container container = findNonFullContainer();
            if (container == null) throw new IllegalStateException("All Containers are full! This should not happen.");
            while (!container.store(objectToLoad)) {
                container = findNonFullContainer();
                if (container == null)
                    throw new IllegalStateException("All Containers are full! This should not happen.");
            }
            TaskLogger.getLogger().info("CargoRobot@" + Integer.toHexString(hashCode()) + " has deposited an object in a Container.");
            objectToLoad = storageSite.retrieveCargoObject();
            try {
                Thread.sleep(actionDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Container findNonFullContainer() {
        for (Container container : storageSite.getContainers()) {
            if (container.countObjects() < 25) return container;
        }
        return null;
    }
}
