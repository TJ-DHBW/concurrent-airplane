package task;

import airplane.Airplane;
import util.TaskLogger;

import java.util.concurrent.TimeUnit;

public class Task5 {

    /*
    S05 | Semaphore oder alternative Realisierung in C# | 15 Punkte

    Zu Simulationszwecken hat das Flugzeug 500 Sensoren, die parallelisiert Informationen sammeln.
    Ein Sensor hat den Status Normal, Warning oder Alarm (zufällig gewählt). Die Zentraleinheit kann
    gleichzeitig Informationen von maximal 100 Sensoren auswerten. Die Zentraleinheit verwaltet
    drei Attribute countMessageNormal, countMessageWarning und countMessageAlarm, die nach
    jedem Messzyklus aktualisiert werden.
     */

    public static void run() throws InterruptedException {
        Airplane airplane = Airplane.AirbusA350_900Factory.buildAirplane();

        Thread thread = new Thread(airplane.getBody().getCentralUnit());
        thread.start();

        TimeUnit.SECONDS.sleep(11);
        TaskLogger.getLogger().info("In total: Normal->" + airplane.getBody().getCentralUnit().getNormalNumber()
                + " Warnings->" + airplane.getBody().getCentralUnit().getWarningNumber()
                + " Alarms-> " + airplane.getBody().getCentralUnit().getAlarmNumber());
    }
}
