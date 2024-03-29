package org.academiadecodigo.splicegirls36.briscolaofthree_pt.player;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.Game;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private String name;

    protected List<Card> hand;

    protected Pile pile;


    public Player (String name) {

        this.name = name;
        hand = new ArrayList<>(Game.STARTING_NUMBER_CARDS_HAND);
        this.pile = CardFactory.createPile();

    }

    public void take(List<Card> hand) {

        this.hand.addAll(hand);
    }

    public void take(Card card) {

        hand.add(card);
    }

    public abstract Card play();


    public int countPoints() {

        return pile.countPoints();
    }

    public void collectCards(List<Card> playedCards) {

        pile.place(playedCards);
    }

    @Override
    public String toString() {
        return this.name + " has a hand of " + hand + "\n and a pile of " + pile + "\n";
    }

    public String getName() {
        return name;
    }

    public String printHand() {

        return hand.toString();
    }

    public int getHandSize() {
        return hand.size();
    }

    public List<Card> getHand() {
        return hand;
    }
}
