package airplane;

import airplane.actors.Passenger;
import airplane.body.Row;
import airplane.body.Seat;

public class BoardingClass {
    public static Airplane bordAirplane(Airplane airplane){
        for(Row row : airplane.getBody().getCabin().getRows()){
            boolean placePassenger = true;
            for (Seat seat : row.getSeats()){
                if(placePassenger){
                    seat.setPassenger(new Passenger());
                    placePassenger = false;
                }
                else {
                    placePassenger = true;
                }
            }
        }
        return airplane;
    }
}
