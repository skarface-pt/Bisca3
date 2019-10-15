package org.academiadecodigo.splicegirls36.briscolaofthree_pt;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.*;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.player.ComputerPlayer;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.player.HumanPlayer;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.player.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {

    // Game constants
    public static final int STARTING_NUMBER_CARDS_HAND = 3;
    public static final int NUMBER_CARDS_DECK = 40;
    public static final int NUMBER_CARDS_PER_SUIT = 10;
    public static final int NUMBER_CARDS_TABLE = 2;

    private Deck deck;
    private Player player1;
    private Player player2;
    private Card briscola;
    private Suit trump;
    private Suit winnerSuit;
    private List<Player> sequence;


    public Game () {

        this.deck = CardFactory.createDeck();
        this.player1 = new ComputerPlayer("Computer1");
        this.player2 = new ComputerPlayer("Computer2");
        this.sequence = new LinkedList<>();
    }

    private void setup() {
        int turn = Randomizer.getRandom(1) + 1;

        if (turn == 1) {
            player1.take(this.deal());
            player2.take(this.deal());
            sequence.add(player1);
            sequence.add(player2);
        } else {
            player2.take(this.deal());
            player1.take(this.deal());
            sequence.add(player2);
            sequence.add(player1);
        }

        briscola = deck.draw();
        trump = briscola.getSuit();

    }

    public Card[] deal() {

        Card[] hand = new Card[STARTING_NUMBER_CARDS_HAND];

        for (int i = 0; i < hand.length; i++) {
            hand[i] = deck.draw();
        }
        return hand;
    }





    public void run () {

        setup();

        for (Player player: sequence) {
            player.play();
        }
        winnerSuit = sequence.get(0).getPick().getSuit();




    }

    //private Player




}
