package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.Game;

public class CardFactory {

    public static Card createCard(Rank rank, Suit suit) {

        return new Card(rank, suit);
    }

    public static Card[] createCards() {

        Card[] deck = new Card[Game.NUMBER_CARDS_DECK];
        int index = 0;

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck[index] = new Card(rank, suit);
                index++;
            }
        }
        return deck;
    }
}
