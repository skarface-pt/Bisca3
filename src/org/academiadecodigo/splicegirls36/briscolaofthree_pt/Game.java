package org.academiadecodigo.splicegirls36.briscolaofthree_pt;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Card;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.CardFactory;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.CardStackType;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Deck;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.player.ComputerPlayer;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.player.HumanPlayer;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.player.Player;

public class Game {

    // Game constants
    public static final int STARTING_NUMBER_CARDS_HAND = 3;
    public static final int NUMBER_CARDS_DECK = 40;
    public static final int NUMBER_CARDS_PER_SUIT = 10;
    public static final int NUMBER_CARDS_TABLE = 2;

    private Deck deck;
    private Card[] table;
    private Player player1;
    private Player player2;

    public Game () {

        this.deck = CardFactory.createDeck();
        this.table = new Card[NUMBER_CARDS_TABLE];
        this.player1 = new ComputerPlayer("Computer1");
        this.player2 = new ComputerPlayer("Computer2");
        setup();
    }

    private void setup() {

        player1.take(this.deal());
        player2.take(this.deal());
    }

    public Card[] deal() {

        Card[] hand = new Card[STARTING_NUMBER_CARDS_HAND];

        for (int i = 0; i < hand.length; i++) {
            hand[i] = deck.draw();
        }
        return hand;
    }

    public void placeInTable(Card card, Player player) {


    }
}
