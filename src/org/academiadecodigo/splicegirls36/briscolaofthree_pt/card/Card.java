package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

public class Card {

    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public enum Rank {
        TWO(0),
        THREE(0),
        FOUR(0),
        FIVE(0),
        SIX(0),
        QUEEN(2),
        JACK(3),
        KING(4),
        SEVEN(10),
        ACE(11);

        private int points;

        Rank(int points) {

            this.points = points;
        }

        int getPoints() {
            return points;
        }

        @Override
        public String toString() {
            String name = super.toString();
            name.substring(1).toLowerCase();
            return name;
        }
    }

    public enum Suit {

        SPADES,
        HEARTS,
        CLUBS,
        DIAMONDS;

        @Override
        public String toString() {
            String name = super.toString();
            name.substring(1).toLowerCase();
            return name;
        }
    }

    public int getPoints() {

        return rank.getPoints();
    }

    public Suit getSuit () {
        return this.suit;
    }

    public String toString() {

        return this.rank.toString() + " of " + this.suit.toString();
    }

    public Rank getRank() {
        return rank;
    }
}
