package airplane;

import airplane.actors.Counter;
import airplane.body.*;
import airplane.wing.Flap;
import airplane.wing.Wing;
import util.TaskLogger;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Airplane {
    private Body body;
    private ArrayList<Wing> wings = new ArrayList<>();

    public Airplane(Body body, Wing leftWing, Wing rightWing) {
        this.body = body;
        this.wings.add(leftWing);
        this.wings.add(rightWing);
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public ArrayList<Wing> getWings() {
        return wings;
    }

    public void setWings(ArrayList<Wing> wings) {
        this.wings = wings;
    }

    public static class AirbusA350_900Factory {
        public static Airplane buildAirplane() {
            Cabin cabin = new Cabin.AirbusA350_900CabinFactory().buildCabin();
            CentralUnit centralUnit = new CentralUnit(new Counter());
            AntiCollisionLight[] antiCollisionLights = new AntiCollisionLight[]{new AntiCollisionLight(), new AntiCollisionLight()};

            // Task 4
            CyclicBarrier engineToTaxiBarrier = new CyclicBarrier(2, () -> TaskLogger.getLogger().info("Ready for taxi."));
            Engine[] engines = new Engine[]{new Engine(1, engineToTaxiBarrier), new Engine(2, engineToTaxiBarrier)};
            CyclicBarrier doorToEngineBarrier = new CyclicBarrier(4, () -> {
                for (Engine engine : engines) new Thread(engine).start();
            });
            EntryDoor[] entryDoors = new EntryDoor[]{new EntryDoor(1, doorToEngineBarrier),
                    new EntryDoor(2, doorToEngineBarrier),
                    new EntryDoor(3, doorToEngineBarrier),
                    new EntryDoor(4, doorToEngineBarrier)};

            // Task6
            CountDownLatch landingGearToFlapLatch = new CountDownLatch(3);
            LandingGear[] landingGears = new LandingGear[]{new LandingGear(landingGearToFlapLatch),
                    new LandingGear(landingGearToFlapLatch),
                    new LandingGear(landingGearToFlapLatch)};
            Flap[] leftFlaps = new Flap[]{new Flap(landingGearToFlapLatch), new Flap(landingGearToFlapLatch)};
            Flap[] rightFlaps = new Flap[]{new Flap(landingGearToFlapLatch), new Flap(landingGearToFlapLatch)};


            Body body = new Body(cabin,
                    centralUnit,
                    antiCollisionLights,
                    entryDoors,
                    engines,
                    landingGears);
            Wing leftWing = new Wing(leftFlaps);
            Wing rightWing = new Wing(rightFlaps);
            return new Airplane(body, leftWing, rightWing);
        }
    }
}
