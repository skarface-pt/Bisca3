package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

import java.util.Collections;

public class Deck extends CardStack {

    public void shuffle() {

        Collections.shuffle(super.stack);
    }

    public Card draw() {

        return stack.pop();
    }

    public void addToBottom (Card briscola) {

        stack.addFirst(briscola);
    }

    @Override
    public String toString() {

        String result = "\n Deck: \n";
        for (Card card : stack) {
            result += " " + card + " ";
        }
        result += " size of " + this.getNumberOfCards() + "\n";
        return result;
    }
}
