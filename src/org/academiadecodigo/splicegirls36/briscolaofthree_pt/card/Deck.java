package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

import java.util.Collections;

public class Deck extends CardStack {

    public void shuffle() {

        Collections.shuffle(super.stack);
    }

    public Card draw() {

        return stack.pop();
    }
}
