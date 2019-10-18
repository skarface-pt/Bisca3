package org.academiadecodigo.splicegirls36.briscolaofthree_pt;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.*;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.player.ComputerPlayer;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.player.Player;

import java.util.*;

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
    private Card.Suit trumpSuit;
    private Sequence sequence;

    private boolean isStartingTrick = true;

    private class CardComparator implements Comparator<Card> {

        @Override
        public int compare(Card card1, Card card2) {
            if ((card1.getSuit().equals(trumpSuit) && card2.getSuit().equals(trumpSuit)) ||
                    (card1.getSuit().equals(sequence.leadSuit) && card2.getSuit().equals(sequence.leadSuit))) {
                return card1.getRank().compareTo(card2.getRank());
            }
            if (card1.getSuit().equals(trumpSuit)) {
                return 1;
            }
            if (card2.getSuit().equals(trumpSuit)) {
                return -1;
            }
            if (card1.getSuit().equals(sequence.leadSuit)) {
                return 1;
            }
            if (card2.getSuit().equals(sequence.leadSuit)) {
                return -1;
            }
            return 0;
        }
    }

    private class Sequence implements Iterable<Player> {

        private Card.Suit leadSuit;
        private List<Player> sequencePlayed;

        Sequence() {

            sequencePlayed = new LinkedList<>();
        }

        void add (Player player) {
            sequencePlayed.add(player);
        }


        @Override
        public Iterator<Player> iterator() {
            return sequencePlayed.iterator();
        }

        List<Card> getCards(Card.Suit suit) {

            Iterator<Player> iterator = iterator();
            List<Card> cards = new LinkedList<>();
            Card nextCard;

            while (iterator().hasNext()) {
                nextCard = iterator.next().getPick();
                if (nextCard.getSuit().equals(suit)) {
                    cards.add(nextCard);
                }
            }
            return cards;
        }

        void setLeadSuit() {
            this.leadSuit = sequencePlayed.get(0).getPick().getSuit();
        }

        Card.Suit getLeadSuit() {

            return leadSuit;
        }

    }


    public Game () {

        this.deck = CardFactory.createDeck();
        this.player1 = new ComputerPlayer("Computer1");
        this.player2 = new ComputerPlayer("Computer2");
        this.sequence = new Sequence();
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

        setBriscola(deck.draw());

    }



    public Card[] deal() {

        Card[] hand = (isStartingTrick) ? new Card[STARTING_NUMBER_CARDS_HAND] : new Card[1];

        for (int i = 0; i < hand.length; i++) {
            hand[i] = deck.draw();
        }
        return hand;
    }

    public void run () {

        setup();

        for (Player player : sequence) {
            player.play();
        }
        sequence.setLeadSuit();

        isStartingTrick = false;

        while (!deck.isEmpty()) {
            for (Player player : sequence) {
                player.play();
            }
            sequence.setLeadSuit();

        }
    }

    private Card findHighestCard(Card.Suit suit) {

        CardComparator cardComparator = new CardComparator();
        List<Card> leadCards;
        Card leadCard;

        leadCards = sequence.getCards(sequence.getLeadSuit());
        leadCard = Collections.max(leadCards, cardComparator);

        return leadCard;
    }



    private Player findTrickWinner() {

        List<Card> trumpCards = sequence.getCards(trumpSuit);
        Card leadCard = null;

        if (trumpCards.isEmpty()) {

            leadCard = findHighestCard(sequence.leadSuit);

            for (Player player : sequence) {
                if (player.getPick().equals(leadCard)) {
                    return player;
                }
            }
        }

        leadCard = findHighestCard(trumpSuit);
        for (Player player : sequence) {
            if (player.getPick().equals(leadCard)) {
                return player;
            }
        }
        System.err.println("This line should never be reached, because by the very rules of the game a trick winner is always found.");
        return null;
    }

    private Player findGameWinner() {

        int player1Points = player1.countPoints();
        int player2Points =  player2.countPoints();

        if (player1Points > player2Points) {
            return player1;
        }
        if (player1Points == player2Points) {
            return null;
        }
        if (player1Points < player2Points) {
            return player2;
        }
        System.err.println("This line should never be reached, because by the very rules of the game a win or a tie are the only possible outcomes");
        return null;
    }

    private void setBriscola(Card briscola) {
        this.briscola = briscola;
        this.trumpSuit = briscola.getSuit();
    }

}
