package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.Game;

public class CardFactory {

    public static Deck createDeck() {
        Deck deck = new Deck();
        createCards(deck);
        deck.shuffle();
        return deck;
    }

    public static Pile createPile() {
        Pile pile = new Pile();
        return pile;
    }

    public static void createCards(Deck deck) {

        Card card;
        Suit[] suits = Suit.values();
        Rank[] ranks = Rank.values();

        for (int i = suits.length; i > 0; i--) {
            for (int j = ranks.length; j > 0; j--) {
                card = new Card(ranks[j], suits[i]);
                deck.place(card);
            }
        }
    }
}
