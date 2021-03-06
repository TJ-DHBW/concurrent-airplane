package airplane.body;

import java.util.ArrayList;

public class Cabin {
    private final ArrayList<Row> rows;

    public Cabin(ArrayList<Row> rows) {
        this.rows = rows;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

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
            ret.addAll(createClassSeating(SeatClass.economy, 16, 16, seatLettersEconomyLeg));
            ret.addAll(createClassSeating(SeatClass.economy, 18, 42, seatLettersEconomy));
            return ret;
        }

        public Cabin buildCabin() {
            return new Cabin(createRows());
        }

        private ArrayList<Row> createClassSeating(SeatClass seatClass, int startingNumber, int stopNumber, Character[] seatLetters) {
            ArrayList<Row> rows = new ArrayList<>();
            for (int i = startingNumber; i <= stopNumber; i++) {
                Row row = new Row(i);
                for (Character seatLetter : seatLetters) {
                    Seat seat = new Seat(seatClass, seatLetter.toString(), i);
                    row.getSeats().add(seat);
                }
                rows.add(row);
            }
            return rows;
        }
    }

}
