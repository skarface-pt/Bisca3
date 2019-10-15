package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

public class Card {

    private Rank rank;
    private Suit suit;

    Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getPoints() {

        return rank.getValue();
    }

    public Suit getSuit () {
        return this.suit;
    }
}
