package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

public enum Rank {

    ACE(11, 10),
    SEVEN(10, 9),
    KING(4, 8),
    JACK(3, 7),
    QUEEN(2, 6),
    SIX(0, 5),
    FIVE(0, 4),
    FOUR(0, 3),
    THREE(0, 2),
    TWO(0, 1);

    private int value;
    private int rank;

    Rank(int value, int rank) {

        this.value = value;
        this.rank = rank;
    }

    public boolean isLargerThan(Rank rank) {

        return this.rank > rank.rank;
    }
}
