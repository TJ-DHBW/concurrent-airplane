package task;

import airplane.actors.CargoRobot;
import airplane.actors.ControlHuman;
import airport.StorageSite;
import util.TaskLogger;

public class Task3 {

    /*
    S03 | Lock oder alternative Realisierung in C# | 15 Punkte

    Auf einem Lagerplatz befinden sich 250 Gegenstände. In einem Container werden 25 Gegen-
    stände aufeinander gestapelt. Die Entnahme der Gegenstände von dem Lagerplatz und die Bela-
    dung der Container findet parallelisiert durch fünf  Roboter statt. Zwischenzeitlich kontrolliert ein
    Mensch einen zufällig ausgewählten Container bezüglich aktueller Anzahl der Gegenstände.
     */

    public static void run() throws InterruptedException {
        // TODO Task3
        StorageSite site = new StorageSite(250);

        TaskLogger.getLogger().info("Starting CargoRobots.");
        Thread thread;
        for (int i = 0; i < 5; i++) {
            thread = new Thread(new CargoRobot(site));
            thread.start();
        }

        ControlHuman human = new ControlHuman(site);
        sendHumanToCheckInIntervall(human, 10, 1000);

        Thread.sleep(11000);
        TaskLogger.getLogger().info(site.getLevels());
    }

    private static void sendHumanToCheckInIntervall(ControlHuman human, int repetitions, int intervallMs) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < repetitions; i++) {
                Thread controlThread = new Thread(human);
                TaskLogger.getLogger().info("Starting a new Container control round.");
                controlThread.start();
                try {
                    Thread.sleep(intervallMs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
