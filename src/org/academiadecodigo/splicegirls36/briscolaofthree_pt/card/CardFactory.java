package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.Game;

public class CardFactory {

    public static CardStack createCards(CardStackType stackType) {

        switch (stackType) {

            case DECK:
                Deck deck = new Deck();
                deck.shuffle();
                return deck;
            case PILE:
                Pile pile = new Pile();
                return pile;
            default:
                throw new IllegalStateException("Unexpected value: " + stackType);
        }
    }
}
