package task;

import airplane.Airplane;

import java.util.concurrent.Semaphore;

public class Task5 {

    /*
    S05 | Semaphore oder alternative Realisierung in C# | 15 Punkte

    Zu Simulationszwecken hat das Flugzeug 500 Sensoren, die parallelisiert Informationen sammeln.
    Ein Sensor hat den Status Normal, Warning oder Alarm (zufällig gewählt). Die Zentraleinheit kann
    gleichzeitig Informationen von maximal 100 Sensoren auswerten. Die Zentraleinheit verwaltet
    drei Attribute countMessageNormal, countMessageWarning und countMessageAlarm, die nach
    jedem Messzyklus aktualisiert werden.
     */

    public static void run() {
        Airplane airplane = Airplane.AirbusA350_900Factory.buildAirplane();

        Semaphore semaphore = new Semaphore(100);
        airplane.getBody().getCentralUnit().setSemaphore(semaphore);
    }
}
