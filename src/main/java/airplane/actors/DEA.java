package airplane.actors;

import airplane.Airplane;
import airplane.body.Cabin;
import airplane.body.SeatClass;
import util.TaskLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DEA {
    private final ArrayList<HumanoidRobotWithFaceRecognition> robots = new ArrayList<>();

    private boolean isElChapoOnBoard;

    public DEA(HumanoidRobotWithFaceRecognition[] robots) {
        this.isElChapoOnBoard = false;
        this.robots.addAll(Arrays.asList(robots));
    }

    public void searchAirplaneForElChapo(Airplane airplane) {
        SeatClass[] classesToSearch = {SeatClass.business, SeatClass.premiumEconomy, SeatClass.economy};
        Cabin cabin = airplane.getBody().getCabin();

        int robotIndex = 0;
        List<HumanoidRobotWithFaceRecognition> configuredRobots = new ArrayList<>();
        try {
            for (SeatClass seatClass : classesToSearch) {
                HumanoidRobotWithFaceRecognition currentRobot = this.robots.get(robotIndex);
                currentRobot.setSeatClassToScan(seatClass);
                currentRobot.setCabinToScan(cabin);
                configuredRobots.add(currentRobot);
                robotIndex++;
            }
        } catch (IndexOutOfBoundsException e) {
            TaskLogger.getLogger().warning("DEA has to few robots to search all the seatClasses.");
            return;
        }
        TaskLogger.getLogger().info("DEA configured the robots to search the airplane.");

        ExecutorService executorservice = Executors.newFixedThreadPool(5);
        try {
            TaskLogger.getLogger().info("DEA is starting the robots.");
            List<Future<Boolean>> futures = executorservice.invokeAll(configuredRobots);


            for (Future<Boolean> future : futures) {
                this.isElChapoOnBoard |= future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorservice.shutdown();
        }
        TaskLogger.getLogger().info("Robots finished. isElChapoOnBoard: " + this.isElChapoOnBoard + ".");
    }
}
