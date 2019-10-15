package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

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

    private int value;

    Rank(int value) {

        this.value = value;
    }

    int getValue() {
        return value;
    }
}
