package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.graphics.Graphic;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.graphics.GraphicCard;

public class Card {

    private Rank rank;
    private Suit suit;
    private GraphicCard graphicCard;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public enum Rank {
        TWO(0, "2"),
        THREE(0, "3"),
        FOUR(0, "4"),
        FIVE(0, "5"),
        SIX(0,"6"),
        QUEEN(2, "Q"),
        JACK(3, "J"),
        KING(4, "K"),
        SEVEN(10, "7"),
        ACE(11, "A");

        private int points;

        private String rankChar;

        public String getRankChar() {
            return rankChar;
        }

        Rank(int points, String rankChar) {

            this.points = points;
            this.rankChar = rankChar;
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

        SPADES("S"),
        HEARTS("H"),
        CLUBS("C"),
        DIAMONDS("D");

        Suit(String suitChar) {
            this.suitChar = suitChar;
        }

        private String suitChar;

        public String getSuitChar() {
            return suitChar;
        }

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


    public GraphicCard getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(GraphicCard graphicCard) {
        this.graphicCard = graphicCard;
    }
}
