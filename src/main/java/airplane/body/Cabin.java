package airplane.body;

import java.util.ArrayList;

public class Cabin {
    private ArrayList<Row> rows;

    public Cabin(CabinBuilder builder) {
        this.rows = builder.rows;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }

    public static class CabinBuilder {
        private ArrayList<Row> rows;

        public CabinBuilder setRows() {
            this.rows = new ArrayList<>();
            Character[] seatLettersBusiness = {'A', 'C', 'D', 'G', 'H', 'K'};
            rows.addAll(createClassSeating(SeatClass.business, 1, 8, seatLettersBusiness));
            Character[] seatLettersPremiumEconomy = {'A', 'C', 'D', 'E', 'G', 'H', 'K'};
            rows.addAll(createClassSeating(SeatClass.premiumEconomy, 12, 12, seatLettersPremiumEconomy));
            rows.addAll(createClassSeating(SeatClass.premiumEconomy, 14, 15, seatLettersPremiumEconomy));
            Character[] seatLettersEconomyLeg = {'A', 'C', 'D', 'E', 'G', 'H', 'K'};
            rows.addAll(createClassSeating(SeatClass.premiumEconomy, 16, 16, seatLettersEconomyLeg));
            Character[] seatLettersEconomy = {'A', 'B', 'C', 'D', 'E', 'G', 'H', 'J', 'K'};
            rows.addAll(createClassSeating(SeatClass.premiumEconomy, 18, 42, seatLettersEconomy));
            return this;
        }

        public Cabin createCabin() {
            return new Cabin(this);
        }

        public ArrayList<Row> createClassSeating(SeatClass seatClass, int startingNumber, int stopNumber, Character[] seatLetters){
            ArrayList<Row> rows = new ArrayList<>();
            for(int i = startingNumber; i<=stopNumber; i++){
                Row row = new Row(i);
                for (int k = 0; k < seatLetters.length; k++){
                    Seat seat = new Seat(SeatClass.business, seatLetters[k].toString(), i);
                    row.getSeats().add(seat);
                }
                rows.add(row);
            }
            return rows;
        }
    }

}
