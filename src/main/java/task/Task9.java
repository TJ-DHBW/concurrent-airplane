package task;

import airplane.Airplane;
import airplane.BoardingClass;
import airplane.actors.DEA;
import airplane.actors.HumanoidRobotWithFaceRecognition;

public class Task9 {

    /*
    S09 | Callable und Future oder alternative Realisierung in C# | 10 Punkte

    Zu Simulationszwecken erhält Passenger ein Attribut face (10x10) mit zufällig gewählten Zeichen
    aus dem Pool [a | c | e | h | i | l | n | o | p | s]. In den Klassen Business, Premium  Economy und
    Economy setzt die DEA für die gezielte Fahndung Humanoide Roboter mit Gesichtserkennung ein.
    Parallelisiert scannen die Humanoiden Roboter die Gesichter der Passagiere. Das Scannen eines
    face dauert 50ms. Die DEA fragt jede Sekunde nach dem Ergebnis. Nachdem alle Roboter die
    Gesichter gescannt haben wird das boolesche Attribut isElChapoOnBoard bei der DEA aktualisiert.
    Befindet sich in einer Zeile die Kette „elchapo“ oder „sinaloa“ wird das Attribut isElChapoOnBoard
    auf true gesetzt, andernfalls auf false.
     */

    public static void run() {
        Airplane airplane = Airplane.AirbusA350_900Factory.buildAirplane();
        BoardingClass.bordAirplane(airplane);

        HumanoidRobotWithFaceRecognition[] robots = {new HumanoidRobotWithFaceRecognition(1),
                new HumanoidRobotWithFaceRecognition(2),
                new HumanoidRobotWithFaceRecognition(3)};
        DEA dea = new DEA(robots);

        dea.searchAirplaneForElChapo(airplane);
        System.out.println("Im done...");
    }
}
