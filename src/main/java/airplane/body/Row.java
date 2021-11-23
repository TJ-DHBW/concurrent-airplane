package airplane.body;

import java.util.ArrayList;

public class Row {
    private int rowNumber;
    private ArrayList<Seat> seats;

    public Row(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }
}
