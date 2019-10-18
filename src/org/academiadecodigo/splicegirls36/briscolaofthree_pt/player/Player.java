package org.academiadecodigo.splicegirls36.briscolaofthree_pt.player;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.Game;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Card;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.CardFactory;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Deck;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Pile;

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
        this.hand = hand;
    }

    public abstract Card play();

    public void draw(Deck deck) {

        Card card = deck.draw();
        hand.add(card);
    }

    public int countPoints() {

        return pile.countPoints();
    }

    public void collectCards(List<Card> playedCards) {

        pile.place(playedCards);
    }


}
