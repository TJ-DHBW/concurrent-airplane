package task;

import airplane.Airplane;
import airplane.body.EntryDoor;
import util.TaskLogger;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class Task4 {

    /*
    S04 | CyclicBarrier oder alternative Realisierung in C# | 10 Punkte

    Das Flugzeug ist mit vier Einstiegstüren ausgestattet. Das Schließen einer Einstiegstür benötigt
    zwischen 1-3 Sekunden (zufallsgesteuert). Nachdem alle Türen parallelisiert geschlossen sind,
    starten parallelisiert die beiden Triebwerke. Das Starten eines Triebwerks dauert (zufallsgesteuert)
    1-5 Sekunden. Nach dem Starten der Triebwerke erfolgt die Meldung „ready for taxi“.
     */

    public static void run() {
        Airplane airplane = new Airplane();

        CyclicBarrier cyclicBarrier01 = new CyclicBarrier(4, () -> {
            CyclicBarrier cyclicBarrier02 = new CyclicBarrier(2, () -> {
                TaskLogger.getLogger().info("Ready for taxi");
            });
            airplane.getBody().getEngines().get(0).setCyclicBarrier(cyclicBarrier02);
            airplane.getBody().getEngines().get(1).setCyclicBarrier(cyclicBarrier02);
            Thread engine01 = new Thread(airplane.getBody().getEngines().get(0));
            engine01.start();
            Thread engine02 = new Thread(airplane.getBody().getEngines().get(1));
            engine02.start();
        });

        ArrayList<EntryDoor> doors = airplane.getBody().getEntryDoors();
        for (EntryDoor door : doors) {
            door.setCyclicBarrier(cyclicBarrier01);
        }

        Thread threadDoor01 = new Thread(doors.get(0));
        Thread threadDoor02 = new Thread(doors.get(1));
        Thread threadDoor03 = new Thread(doors.get(2));
        Thread threadDoor04 = new Thread(doors.get(3));

        threadDoor01.start();
        threadDoor02.start();
        threadDoor03.start();
        threadDoor04.start();
    }
}
