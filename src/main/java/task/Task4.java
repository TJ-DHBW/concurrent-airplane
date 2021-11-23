package task;

import airplane.Airplane;

public class Task4 {

    /*
    S04 | CyclicBarrier oder alternative Realisierung in C# | 10 Punkte

    Das Flugzeug ist mit vier Einstiegstüren ausgestattet. Das Schließen einer Einstiegstür benötigt
    zwischen 1-3 Sekunden (zufallsgesteuert). Nachdem alle Türen parallelisiert geschlossen sind,
    starten parallelisiert die beiden Triebwerke. Das Starten eines Triebwerks dauert (zufallsgesteuert)
    1-5 Sekunden. Nach dem Starten der Triebwerke erfolgt die Meldung „ready for taxi“.
     */

    public static void run() {
        Airplane airplane = Airplane.AirbusA350_900Factory.buildAirplane();

        airplane.getBody().getEntryDoors().forEach(door -> new Thread(door).start());
    }
}
