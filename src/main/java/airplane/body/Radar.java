package airplane.body;

import util.TaskLogger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ThreadLocalRandom;

public class Radar {
    private final char[] characters = new char[]{'.', 'c', 'b', 't'};
    private final int[] probability = new int[]{84, 10, 3, 3};
    private final Exchanger<String> exchanger;

    private int countAcknowledged;

    public Radar(Exchanger<String> exchanger) {
        this.countAcknowledged = 0;
        this.exchanger = exchanger;
    }

    public void runTenTimes() {
        try {
            for (int i = 0; i < 10; i++) {
                TaskLogger.getLogger().info("Radar taking picture #" + (i + 1) + ".");
                String picToSend = preparePictureForSend(takePicture());
                String received = exchanger.exchange(picToSend);
                if (received.equals("ack")) {
                    countAcknowledged++;
                    TaskLogger.getLogger().info("Radar received ack.");
                } else {
                    TaskLogger.getLogger().info("Radar did not receive ack, but: " + received + ".");
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char[][] takePicture() {
        char[][] pic = new char[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                pic[i][j] = generateChar();
            }
        }
        return pic;
    }

    private String preparePictureForSend(char[][] picture) {
        StringBuilder builder = new StringBuilder();
        for (char[] chars : picture) {
            builder.append(chars);
        }
        return builder.toString();
    }

    private char generateChar() {
        int randomChar = ThreadLocalRandom.current().nextInt(probabilitySumToIndex(probability.length - 1));

        int i = 0;
        int probabilitySum = probabilitySumToIndex(i);
        while (randomChar >= probabilitySum) {
            i++;
            probabilitySum = probabilitySumToIndex(i);
        }
        return characters[i];
    }

    private int probabilitySumToIndex(int index) {
        if (index < 0 || index > probability.length - 1)
            throw new IllegalArgumentException("Radar::probabilitySumToIndex only accepts arguments within 0 - probability.length-1.");
        int sum = 0;
        for (int i = 0; i <= index; i++) {
            sum += probability[i];
        }
        return sum;
    }
}
