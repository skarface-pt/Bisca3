package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

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
        Card.Suit[] suits = Card.Suit.values();
        Card.Rank[] ranks = Card.Rank.values();

        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                card = new Card(ranks[j], suits[i]);
                deck.place(card);
            }
        }
    }
}
