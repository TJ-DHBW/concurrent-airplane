package airplane.actors;

import airplane.body.Cabin;
import airplane.body.Row;
import airplane.body.Seat;
import util.TaskLogger;

public class HumanoidRobot {
    private int id;

    public HumanoidRobot(int id) {
        this.id = id;
    }

    public void countPassengers(Counter counter, Cabin cabin, int smallestRowNumber, int highestRowNumber) {
        for (Row row : cabin.getRows()) {
            if (row.getRowNumber() >= smallestRowNumber && row.getRowNumber() <= highestRowNumber) {
                for (Seat seat : row.getSeats()) {
                    if (seat.getPassenger() != null) {
                        counter.passengerFound();
                        TaskLogger.getLogger().info("Robot " + id + " found passenger.");
                    }
                }
            }
        }
        TaskLogger.getLogger().info("Robot " + id + " is done");
    }
}
