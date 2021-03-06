package airplane.body;

import airplane.actors.Passenger;

public class Seat {
    private final SeatClass seatClass;
    private Passenger passenger;
    private final String seatLetter;
    private final int rowNumber;

    public Seat(SeatClass seatClass, String seatLetter, int rowNumber) {
        this.seatClass = seatClass;
        this.seatLetter = seatLetter;
        this.rowNumber = rowNumber;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }
}
