package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

import java.util.List;

public class Pile extends CardStack {

    public int countPoints() {
        int sum = 0;
        for (Card card : stack) {
            sum += card.getPoints();
        }
        return sum;
    }

    public void place(List<Card> cards) {

        for (Card card : cards) {
            stack.push(card);
        }
    }

    @Override
    public String toString() {

        String result = "\n Pile: \n";
        for (Card card : stack) {
            result += " " + card + " ";
        }
        result += " adds up to " + countPoints() + " points \n";
        return result;
    }
}
