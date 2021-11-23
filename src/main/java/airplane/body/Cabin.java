package airplane.body;

import java.util.ArrayList;

public class Cabin {
    private ArrayList<Row> rows;

    public Cabin(ArrayList<Row> rows) {
        this.rows = rows;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }

    // TODO Review this Factory
    public static class AirbusA350_900CabinFactory {
        private ArrayList<Row> createRows() {
            Character[] seatLettersBusiness = {'A', 'C', 'D', 'G', 'H', 'K'};
            Character[] seatLettersPremiumEconomy = {'A', 'C', 'D', 'E', 'G', 'H', 'K'};
            Character[] seatLettersEconomyLeg = {'A', 'C', 'D', 'E', 'G', 'H', 'K'};
            Character[] seatLettersEconomy = {'A', 'B', 'C', 'D', 'E', 'G', 'H', 'J', 'K'};

            ArrayList<Row> ret = new ArrayList<>();
            ret.addAll(createClassSeating(SeatClass.business, 1, 8, seatLettersBusiness));
            ret.addAll(createClassSeating(SeatClass.premiumEconomy, 12, 12, seatLettersPremiumEconomy));
            ret.addAll(createClassSeating(SeatClass.premiumEconomy, 14, 15, seatLettersPremiumEconomy));
            ret.addAll(createClassSeating(SeatClass.premiumEconomy, 16, 16, seatLettersEconomyLeg));
            ret.addAll(createClassSeating(SeatClass.premiumEconomy, 18, 42, seatLettersEconomy));
            // TODO most of the seats get passed premiumEconomy here.
            return ret;
        }

        public Cabin buildCabin() {
            return new Cabin(createRows());
        }

        // TODO An argument is not used here.
        private ArrayList<Row> createClassSeating(SeatClass seatClass, int startingNumber, int stopNumber, Character[] seatLetters) {
            ArrayList<Row> rows = new ArrayList<>();
            for (int i = startingNumber; i <= stopNumber; i++) {
                Row row = new Row(i);
                for (Character seatLetter : seatLetters) {
                    Seat seat = new Seat(SeatClass.business, seatLetter.toString(), i);
                    row.getSeats().add(seat);
                }
                rows.add(row);
            }
            return rows;
        }
    }

}
