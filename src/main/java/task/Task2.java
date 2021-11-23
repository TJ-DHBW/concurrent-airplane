package task;

import airplane.Airplane;
import airplane.body.AntiCollisionLight;
import util.TaskLogger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task2 {

    /*
    S02 | ExecutorService oder alternative Realisierung in C# | 5 Punkte

    An der Ober- und Unterseite des Flugzeuges befindet sich je ein Anti-Collision Light. Die beiden
    Anti-Collisions Light werden gleichzeitig gestartet und blicken synchron alle 3 Sekunden.
     */

    public static void run() throws InterruptedException {
        Airplane airplane = new Airplane();
        ExecutorService execService = Executors.newFixedThreadPool(2);

        try {
            TaskLogger.getLogger().info("Starting the blinking of the lights now.");
            for (AntiCollisionLight light : airplane.getBody().getAntiCollisionLights()) {
                execService.execute(light::blinkThrice);
            }
        } finally {
            execService.shutdown();
            execService.awaitTermination(19, TimeUnit.SECONDS);
        }
        TaskLogger.getLogger().info("Blinking of the lights has ended.");
    }
}
