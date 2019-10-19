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
    public static final int NUMBER_PLAYERS = 2;

    private Player player1;
    private Player player2;
    private Table table;

    private class Table implements Iterable<Pick> {

        private Card.Suit leadSuit;
        private List<Pick> sequence;
        private ArrayList<Player> orderOfPlay;
        private Card briscola;
        private Card.Suit trumpSuit;
        private Deck deck;

        Table() {
            this.deck = CardFactory.createDeck();
            sequence = new LinkedList<>();
            orderOfPlay = new ArrayList<>(NUMBER_PLAYERS);
        }

        void add (Player player, Card card) {
            sequence.add(new Pick(player, card));
        }

        void setOrderFor (Player player, int order) {

            if (order > 0 && order < NUMBER_PLAYERS) {
                orderOfPlay.add(order - 1, player);
            }
        }

        @Override
        public Iterator<Pick> iterator() {
            return sequence.iterator();
        }

        List<Pick> getPicks(Card.Suit suit) {

            Iterator<Pick> iterator = iterator();
            List<Pick> picks = new LinkedList<>();
            Pick nextPick;

            while (iterator().hasNext()) {
                nextPick = iterator.next();
                if (nextPick.getCard().getSuit().equals(suit)) {
                    picks.add(nextPick);
                }
            }
            return picks;
        }

        void setLeadSuit() {
            this.leadSuit = sequence.get(0).getCard().getSuit();
        }

        Card.Suit getLeadSuit() {

            return leadSuit;
        }

        private void setBriscola() {
            this.briscola = deck.draw();
            this.trumpSuit = briscola.getSuit();
        }

        private void setupOrderForNextTrick(Player trickWinner) {

            int trickWinnerPos = sequence.indexOf(trickWinner);

            Collections.rotate(sequence, -trickWinnerPos);
        }

        private Pick findHighestPick(Card.Suit suit) {

            PickComparator pickComparator = new PickComparator();
            List<Pick> leadPicks;
            Pick leadPick;

            leadPicks = getPicks(getLeadSuit());
            leadPick = Collections.max(leadPicks, pickComparator);

            return leadPick;
        }

        private Player findTrickWinner() {

            List<Pick> trumpPicks = getPicks(trumpSuit);
            Pick leadPick = null;

            if (trumpPicks.isEmpty()) {

                leadPick = findHighestPick(leadSuit);
                return leadPick.getPlayer();
            }

            leadPick = findHighestPick(trumpSuit);
            return leadPick.getPlayer();
        }

        private List<Card> getCards() {

            List<Card> cards = new ArrayList<>();

            for (Pick pick : sequence) {
                cards.add(pick.getCard());
            }
            return cards;
        }

        private void resetSequence() {

            sequence.clear();
        }

        public List<Card> deal() {

            List<Card> hand = new ArrayList<>(STARTING_NUMBER_CARDS_HAND);

            for (int i = 0; i < hand.size(); i++) {
                hand.add(table.deck.draw());
            }
            return hand;
        }

        private void dealAllPlayers() {

            for (int i = 0; i < orderOfPlay.size(); i++) {

                orderOfPlay.get(i).take(deal());
            }
        }

        private class PickComparator implements Comparator<Pick> {

            @Override
            public int compare(Pick pick1, Pick pick2) {
                if ((pick1.getCard().getSuit().equals(trumpSuit) && pick2.getCard().getSuit().equals(trumpSuit)) ||
                        (pick1.getCard().getSuit().equals(leadSuit) && pick2.getCard().getSuit().equals(leadSuit))) {
                    return pick1.getCard().getRank().compareTo(pick2.getCard().getRank());
                }
                if (pick1.getCard().getSuit().equals(trumpSuit)) {
                    return 1;
                }
                if (pick2.getCard().getSuit().equals(trumpSuit)) {
                    return -1;
                }
                if (pick1.getCard().getSuit().equals(table.leadSuit)) {
                    return 1;
                }
                if (pick2.getCard().getSuit().equals(table.leadSuit)) {
                    return -1;
                }
                return 0;
            }
        }

    }

    public Game () {

        this.player1 = new ComputerPlayer("Computer1");
        this.player2 = new ComputerPlayer("Computer2");
        this.table = new Table();
    }

    private void setup() {
        int turn = Randomizer.getRandom(NUMBER_PLAYERS) + 1;

        if (turn == 1) {
            table.setOrderFor(player1, 1);
            table.setOrderFor(player2, 2);
            player1.take(table.deal());
            player2.take(table.deal());
        } else {
            table.setOrderFor(player1, 2);
            table.setOrderFor(player2, 1);
            player2.take(table.deal());
            player1.take(table.deal());
        }

        table.setBriscola();
    }

    public Player runTrick() {

        Player trickWinner = null;

        // Ask each player in order to choose a card and play it. The order starts on the winner of last trick
        for (Player player : table.orderOfPlay) {
            table.add(player, player.play());
        }

        table.setLeadSuit();

        trickWinner = table.findTrickWinner();
        trickWinner.collectCards(table.getCards());

        // Use Collections.rotate() with negative shift to change the order of the sequence for the next round
        table.setupOrderForNextTrick(trickWinner);

        return trickWinner;
    }

    public Player run () {

        Player trickWinner = null;
        Player gameWinner = null;

        setup();

        trickWinner = runTrick();
        table.resetSequence();

        while(!table.deck.isEmpty()) {

            table.dealAllPlayers();
            trickWinner = runTrick();
            table.resetSequence();
        }

        // Last 3 tricks of the game, in which each player plays each of its remaining cards
        for (int count = STARTING_NUMBER_CARDS_HAND; count > 0; count--) {
            table.dealAllPlayers();
            trickWinner = runTrick();
            table.resetSequence();
        }

        gameWinner = findGameWinner();

        return gameWinner;
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

}
