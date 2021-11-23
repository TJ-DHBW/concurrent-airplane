package airplane.body;

import java.util.ArrayList;

public class Row {
    private int rowNumber;
    private ArrayList<Seat> seats;

    public Row(int rowNumber) {
        this.rowNumber = rowNumber;
        this.seats = new ArrayList<>();
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }
}
