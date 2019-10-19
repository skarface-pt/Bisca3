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

        @Override
        public Iterator<Pick> iterator() {
            return sequence.iterator();
        }

        List<Pick> getPicks(Card.Suit suit) {

            Iterator<Pick> iterator = iterator();
            List<Pick> picks = new LinkedList<>();
            Pick nextPick = null;

            while (iterator.hasNext()) {
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
            this.deck.addToBottom(briscola);
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

        private List<Card> dealAll() {

            List<Card> hand = new ArrayList<>(STARTING_NUMBER_CARDS_HAND);

            for (int i = 0; i < STARTING_NUMBER_CARDS_HAND; i++) {
                hand.add(table.deck.draw());
            }
            return hand;
        }

        private Card dealOne() {

            return deck.draw();
        }

        private void dealAllPlayers() {
            Card card;
            Iterator<Player> iterator = orderOfPlay.listIterator();

            while (iterator.hasNext()) {

                card = dealOne();
                iterator.next().take(card);
                System.out.println(card);
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

        @Override
        public String toString() {
            return "In the table, there is a " + deck + " The briscola is " + briscola + "\n The trump suit is " + trumpSuit + "\n";
        }

        private String printOrderOfPlay() {
            String result = "";
            for (Player player : orderOfPlay) {
                result += " " + player.getName() + " ";
            }
            return result;
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
            table.orderOfPlay.add(player1);
            table.orderOfPlay.add(player2);
            player1.take(table.dealAll());
            player2.take(table.dealAll());
        } else {
            table.orderOfPlay.add(player2);
            table.orderOfPlay.add(player1);
            player2.take(table.dealAll());
            player1.take(table.dealAll());
        }

        table.setBriscola();

        System.out.println("The order of play at the 1st turn is " + table.printOrderOfPlay() + "\n");
    }

    private void runTrick() {

        Player trickWinner = null;
        Iterator<Player> playerIterator = table.orderOfPlay.listIterator();
        Player player = null;

        // Each player must draw a card from the deck

        // Ask each player in order to choose a card from their hand and play it. The order starts on the winner of last trick
        /**while (playerIterator.hasNext()) {

            player = playerIterator.next();
            table.add(player, player.play());
        }*/

        for (int i = 0; i < table.orderOfPlay.size(); i++) {

            player = table.orderOfPlay.get(i);
            System.out.println("Player " + player.getName() + " has a hand of " + player.printHand() + "\n");
            table.add(player, player.play());
            System.out.println("Player " + player + " played " + table.sequence.get(table.sequence.size() - 1).getCard() + "\n");
        }

        table.setLeadSuit();
        System.out.println("The lead suit is " + table.leadSuit);

        trickWinner = table.findTrickWinner();
        trickWinner.collectCards(table.getCards());

        System.out.println(trickWinner);

        // Use Collections.rotate() with negative shift to change the order of the sequence for the next round
        table.setupOrderForNextTrick(trickWinner);
        System.out.println("Completed trick. The trick winner is " + trickWinner.getName() + "\n" +
                "The next trick will be played in the following order: \n" + table.printOrderOfPlay() + "\n");
    }

    public Player run () {

        int count = 0;

        Player gameWinner = null;

        setup();

        System.out.println(table);

        runTrick();
        table.resetSequence();

        while(!table.deck.isEmpty()) {

            table.dealAllPlayers();
            runTrick();
            table.resetSequence();
        }


        System.out.println(player1.printHand());
        System.out.println(player2.printHand());
        // Last 3 tricks of the game, in which each player plays each of its remaining cards
        while (player1.getHandSize() > 0) {
            runTrick();
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

    @Override
    public String toString() {
        return player1 + " vs " + player2;
    }
}
