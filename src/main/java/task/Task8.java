package task;

import airplane.Airplane;
import airplane.body.Engine;

import java.util.ArrayList;
import java.util.concurrent.Phaser;

public class Task8 {

    /*
    S08 | Phaser oder alternative Realisierung in C# | 10 Punkte

    Die maximale Drehzahl des Triebwerks beträgt 8000 RPM. Zu Simulationszwecken werden beim
    TakeOff die Phasen ReadyForDeparture, TrustSet, V1 und Climb unterschieden. Die beiden Trieb-
    werke laufen parallelisiert. Die RPM je Triebwerk und Phase sind wie folgt [i] ReadyForDeparture:
    500, [ii] TrustSet: 2400 [iii] V1: 4400 und [iv] Climb: 6000. Jede Phase dauert 3 Sekunden.
     */

    public static void run() {
        Airplane airplane = Airplane.AirbusA350_900Factory.buildAirplane();
        ArrayList<Engine> engines = airplane.getBody().getEngines();
        Phaser phaser = new Phaser();

        Thread thread01 = new Thread(() -> {
            try {
                engines.get(0).synchronisedTakeOff(phaser);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread02 = new Thread(() -> {
            try {
                engines.get(1).synchronisedTakeOff(phaser);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread01.start();
        thread02.start();
    }
}
