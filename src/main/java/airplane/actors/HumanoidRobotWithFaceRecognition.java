package airplane.actors;

import airplane.body.Cabin;
import airplane.body.Row;
import airplane.body.Seat;
import airplane.body.SeatClass;
import util.TaskLogger;

import java.util.concurrent.Callable;

public class HumanoidRobotWithFaceRecognition extends HumanoidRobot implements Callable<Boolean> {
    private Cabin cabinToScan;
    private SeatClass seatClassToScan;

    public HumanoidRobotWithFaceRecognition(int id) {
        super(id);
    }


    @Override
    public Boolean call() throws Exception {
        for (Row row : cabinToScan.getRows()) {
            for (Seat seat : row.getSeats()) {
                if (seat.getSeatClass() != seatClassToScan) continue;
                if (seat.getPassenger() == null) continue;
                char[][] face = seat.getPassenger().getFace();
                if (isElChapo(face)) {
                    TaskLogger.getLogger().info("Robot searching seatClass " + seatClassToScan + " identified a passenger as El Chapo!.");
                    return true;
                }
                TaskLogger.getLogger().info("Robot searching seatClass " + seatClassToScan + " identified a passenger as not El Chapo.");
                Thread.sleep(50);
            }
        }
        TaskLogger.getLogger().info("Robot searching seatClass " + seatClassToScan + " did not find anything. Shutting down.");
        return false;
    }

    private boolean isElChapo(char[][] face) {
        for (char[] row : face) {
            String rowString = new String(row);
            if (rowString.contains("elchapo")) return true;
            if (rowString.contains("sinaloa")) return true;
        }
        return false;
    }

    public void setCabinToScan(Cabin cabinToScan) {
        this.cabinToScan = cabinToScan;
    }

    public void setSeatClassToScan(SeatClass seatClassToScan) {
        this.seatClassToScan = seatClassToScan;
    }
}
