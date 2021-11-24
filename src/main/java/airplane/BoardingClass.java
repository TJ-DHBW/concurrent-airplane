package airplane;

import airplane.actors.Passenger;
import airplane.body.Row;
import airplane.body.Seat;

public class BoardingClass {
    public static void bordAirplane(Airplane airplane) {
        for (Row row : airplane.getBody().getCabin().getRows()) {
            boolean placePassenger = true;
            for (Seat seat : row.getSeats()) {
                if (placePassenger) {
                    seat.setPassenger(new Passenger());
                    placePassenger = false;
                } else {
                    placePassenger = true;
                }
            }
        }
    }
}
