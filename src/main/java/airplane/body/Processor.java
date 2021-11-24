package airplane.body;

import util.TaskLogger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Processor {
    private final Exchanger<String> exchanger;

    private int countBirds;

    public Processor(Exchanger<String> exchanger) {
        this.countBirds = 0;
        this.exchanger = exchanger;
    }

    public void runWithTimeout() {
        try {
            while (true) {
                TaskLogger.getLogger().info("Processor waiting for picture.");
                String received = exchanger.exchange("ack", 3, TimeUnit.SECONDS);
                TaskLogger.getLogger().info("Processor received picture.");
                int countedBirds = countBirdsInPicture(received);
                this.countBirds += countedBirds;
                TaskLogger.getLogger().info("BirdCount is now: " + this.countBirds + ".");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            TaskLogger.getLogger().info("Processor turning off.");
        }
    }

    private int countBirdsInPicture(String pic) {
        Pattern birdPattern = Pattern.compile("b");
        Matcher matcher = birdPattern.matcher(pic);

        int birds = 0;
        while (matcher.find()) birds++;

        return birds;
    }
}
