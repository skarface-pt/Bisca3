package org.academiadecodigo.splicegirls36.briscolaofthree_pt.player;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.Game;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Card;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.CardFactory;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Deck;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Pile;

public abstract class Player {

    private String name;

    protected Card[] hand;

    protected Pile pile;

    protected Card pick;


    public Player (String name) {

        this.name = name;
        hand = new Card[Game.STARTING_NUMBER_CARDS_HAND];
        this.pile = CardFactory.createPile();

    }

    public void take(Card[] hand) {
        this.hand = hand;
    }

    public abstract Card play();

    public void draw(Deck deck) {

        Card card = deck.draw();

        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == null) {
                hand[i] = card;
            }
        }
    }

    public int countPoints() {

        return pile.countPoints();
    }

    public Card getPick() {
        return pick;
    }


}
