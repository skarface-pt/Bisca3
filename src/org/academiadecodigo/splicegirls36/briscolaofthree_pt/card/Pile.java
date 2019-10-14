package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

import java.util.LinkedList;

public class Pile extends CardStack {

    public int countPoints() {
        int sum = 0;
        for (Card card : stack) {
            sum += card.getPoints();
        }
        return sum;
    }

    void place(LinkedList<Card> cards) {

        for (Card card : cards) {
            stack.push(card);
        }
    }
}
