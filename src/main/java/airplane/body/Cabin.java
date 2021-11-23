package airplane.body;

import java.util.ArrayList;

public class Cabin {
    private ArrayList<Row> businessClass;
    private ArrayList<Row> premiumEconomyClass;
    private ArrayList<Row> economyClass;

    public Cabin(CabinBuilder builder) {
        this.businessClass = builder.businessClass;
        this.premiumEconomyClass = builder.premiumEconomyClass;
        this.economyClass = builder.economyClass;
    }

    public static class CabinBuilder {
        private ArrayList<Row> businessClass;
        private ArrayList<Row> premiumEconomyClass;
        private ArrayList<Row> economyClass;

        public CabinBuilder setBusinessClass() {
            this.businessClass = new ArrayList<>();
            Character[] seatLettersBusiness = {'A', 'C', 'D', 'G', 'H', 'K'};
            this.businessClass = createClassSeating(SeatClass.business, 1, 8, seatLettersBusiness);
            return this;
        }


        public CabinBuilder setPremiumEconomyClass() {
            this.premiumEconomyClass = new ArrayList<>();
            Character[] seatLettersPremiumEconomy = {'A', 'C', 'D', 'E', 'G', 'H', 'K'};
            this.premiumEconomyClass = createClassSeating(SeatClass.premiumEconomy, 12, 12, seatLettersPremiumEconomy);
            this.premiumEconomyClass.addAll(createClassSeating(SeatClass.premiumEconomy, 14, 15, seatLettersPremiumEconomy));
            return this;
        }

        public CabinBuilder setEconomyClass() {
            this.economyClass = economyClass;
            Character[] seatLettersEconomyLeg = {'A', 'C', 'D', 'E', 'G', 'H', 'K'};
            economyClass.addAll(createClassSeating(SeatClass.premiumEconomy, 16, 16, seatLettersEconomyLeg));
            Character[] seatLettersEconomy = {'A', 'B', 'C', 'D', 'E', 'G', 'H', 'J', 'K'};
            economyClass.addAll(createClassSeating(SeatClass.premiumEconomy, 18, 42, seatLettersEconomy));
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
