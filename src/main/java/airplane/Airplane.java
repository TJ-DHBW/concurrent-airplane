package airplane;

import airplane.actors.Counter;
import airplane.body.*;
import airplane.body.sensor.Sensor;
import airplane.wing.Flap;
import airplane.wing.Wing;
import util.TaskLogger;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

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

            // Task5
            Semaphore semaphore = new Semaphore(100);
            CentralUnit centralUnit = new CentralUnit(new Counter(), semaphore);
            ArrayList<Sensor> sensors = new ArrayList<>();
            for (int i = 0; i < 500; i++) {
                sensors.add(new Sensor(i + 1, semaphore, centralUnit));
            }

            // Task6
            CountDownLatch landingGearToFlapLatch = new CountDownLatch(3);
            LandingGear[] landingGears = new LandingGear[]{new LandingGear(landingGearToFlapLatch),
                    new LandingGear(landingGearToFlapLatch),
                    new LandingGear(landingGearToFlapLatch)};
            Flap[] leftFlaps = new Flap[]{new Flap(landingGearToFlapLatch), new Flap(landingGearToFlapLatch)};
            Flap[] rightFlaps = new Flap[]{new Flap(landingGearToFlapLatch), new Flap(landingGearToFlapLatch)};

            // Task7
            Exchanger<String> radarProcessorExchanger = new Exchanger<>();
            Processor processor = new Processor(radarProcessorExchanger);
            Radar radar = new Radar(radarProcessorExchanger);

            Body body = new Body(cabin,
                    centralUnit,
                    antiCollisionLights,
                    entryDoors,
                    engines,
                    landingGears,
                    radar,
                    processor,
                    sensors);
            Wing leftWing = new Wing(leftFlaps);
            Wing rightWing = new Wing(rightFlaps);
            Airplane airplane = new Airplane(body, leftWing, rightWing);
            centralUnit.setAirplane(airplane);

            return airplane;
        }
    }
}
