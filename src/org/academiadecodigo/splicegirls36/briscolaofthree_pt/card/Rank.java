package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

public enum Rank {

    ACE(11),
    SEVEN(10),
    KING(4),
    JACK(3),
    QUEEN(2),
    SIX(0),
    FIVE(0),
    FOUR(0),
    THREE(0),
    TWO(0);

    private int value;

    Rank(int value) {

        this.value = value;
    }

    int getValue() {
        return value;
    }
}
