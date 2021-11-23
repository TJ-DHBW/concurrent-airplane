package task;

import airplane.Airplane;
import airplane.wing.Flap;

public class Task6 {

    /*
    S06 | CountDownLatch oder alternative Realisierung in C# | 10 Punkte

    Jede Tragfläche verfügt über zwei Flaps. Bei der Landung wartet die parallelisierte Stellung (Level:
    Down) der Flaps das Ausfahren der drei Fahrwerke ab. Das Ausfahren eines Fahrwerkes dauert 1-
    3 Sekunden (zufallsgesteuert).
     */

    // TODO Look at this again. The timing is reeeeeeally weird.
    public static void run() {
        Airplane airplane = Airplane.AirbusA350_900Factory.buildAirplane();

        airplane.getWings().forEach(wing -> wing.getFlaps().forEach(Flap::run));
        airplane.getBody().getLandingGears().forEach(Thread::start);
    }
}
