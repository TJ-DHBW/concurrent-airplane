package airplane.body;

import airplane.actors.Passenger;

public class Seat {
    private SeatClass seatClass;
    private Passenger passenger;
    private String seatLetter;
    private int rowNumber;

    public Seat(SeatClass seatClass, String seatLetter, int rowNumber) {
        this.seatClass = seatClass;
        this.seatLetter = seatLetter;
        this.rowNumber = rowNumber;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(SeatClass seatClass) {
        this.seatClass = seatClass;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getSeatLetter() {
        return seatLetter;
    }

    public void setSeatLetter(String seatLetter) {
        this.seatLetter = seatLetter;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }
}
