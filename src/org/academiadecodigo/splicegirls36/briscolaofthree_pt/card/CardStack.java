package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

import java.util.LinkedList;

public class CardStack {

    protected LinkedList<Card> stack;

    CardStack() {
        this.stack = new LinkedList<>();
    }

    public void place(Card card){

        stack.push(card);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
