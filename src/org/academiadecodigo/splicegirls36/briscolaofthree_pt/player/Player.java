package org.academiadecodigo.splicegirls36.briscolaofthree_pt.player;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.Game;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Card;

public abstract class Player {

    private String name;

    //List of cards the player has in his hand for the current round - possibly a stack data structure
    protected Card[] hand;

    protected Card[] pile;

    protected Card pick;


    public Player () {

        hand = new Card[Game.STARTING_NUMBER_CARDS_HAND];
        pile = new Card[Game.NUMBER_CARDS_DECK];
    }

    public abstract void play();

    public Card getPick() {
        return pick;
    }
}
