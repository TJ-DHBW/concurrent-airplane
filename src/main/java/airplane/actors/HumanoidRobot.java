package airplane.actors;

import airplane.body.Cabin;
import airplane.body.Row;
import airplane.body.Seat;
import util.TaskLogger;

public class HumanoidRobot {
    private final int id;

    public HumanoidRobot(int id) {
        this.id = id;
    }

    public void countPassengers(Counter counter, Cabin cabin, int minRow, int maxRow) {
        for (Row row : cabin.getRows()) {
            if (row.getRowNumber() >= minRow && row.getRowNumber() <= maxRow) {
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
