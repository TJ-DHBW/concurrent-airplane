package task;

import airplane.Airplane;
import airplane.BoardingClass;
import airplane.actors.Counter;
import airplane.actors.HumanoidRobot;
import airplane.body.Cabin;
import util.TaskLogger;

import java.util.ArrayList;

public class Task1 {

    /*
    S01 | Atomic oder alternative Realisierung in C# | 10 Punkte

    In den Klassen Business, Premium Economy und Economy werden Humanoide Roboter
    eingesetzt. Die Humanoiden Roboter sind für folgende Reihen zuständig:
    01: 1-3 | 02: 4-6 | 03: 7-8 | 04: 12-15 | 05: 16-26, 06: 27-34, 07: 35-42.
    Parallelisiert zählen die Humanoiden Roboter die Passagiere und aktualisieren das Attribut
    totalNumberOfPasssenger, welches von der Zentraleinheit des Flugzeuges verwaltet wird.
    */

    public static void run() throws InterruptedException {
        //TODO Task 1
        Airplane airplane = new Airplane();
        BoardingClass.bordAirplane(airplane);

        ArrayList<HumanoidRobot> robots = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            robots.add(new HumanoidRobot(i));
        }
        Counter counter = airplane.getBody().getCentralUnit().getCounter();
        Cabin cabin = airplane.getBody().getCabin();

        Thread thread01 = new Thread(() -> {
            robots.get(0).countPassengers(counter, cabin, 1, 3);
        });

        Thread thread02 = new Thread(() -> {
            robots.get(1).countPassengers(counter, cabin, 2, 6);
        });

        Thread thread03 = new Thread(() -> {
            robots.get(2).countPassengers(counter, cabin, 7, 8);
        });

        Thread thread04 = new Thread(() -> {
            robots.get(3).countPassengers(counter, cabin, 12, 15);
        });

        Thread thread05 = new Thread(() -> {
            robots.get(4).countPassengers(counter, cabin, 16, 26);
        });

        Thread thread06 = new Thread(() -> {
            robots.get(5).countPassengers(counter, cabin, 27, 34);
        });

        Thread thread07 = new Thread(() -> {
            robots.get(6).countPassengers(counter, cabin, 35, 42);
        });

        thread01.start();
        thread02.start();
        thread03.start();
        thread04.start();
        thread05.start();
        thread06.start();
        thread07.start();

        thread01.join();
        thread02.join();
        thread03.join();
        thread04.join();
        thread05.join();
        thread06.join();
        thread07.join();

        TaskLogger.getLogger().info("All robots done, final number of passengers: " + counter.getTotalNumberOfPassenger());
    }
}
