package org.academiadecodigo.splicegirls36.briscolaofthree_pt;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.*;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.graphics.*;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.player.ComputerPlayer;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.player.HumanPlayer;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.player.Player;

import java.util.*;

public class Game implements KeyboardHandler {

    // Game constants
    public static final int STARTING_NUMBER_CARDS_HAND = 3;
    public static final int NUMBER_CARDS_DECK = 40;
    public static final int NUMBER_CARDS_PER_SUIT = 10;
    public static final int NUMBER_CARDS_TABLE = 2;
    public static final int NUMBER_PLAYERS = 2;

    private Player player1;
    private Player player2;
    private Table table;

    private boolean humanPicked = false;
    private GraphicPosition humanPick;

    private GraphicPosition gPos1;
    private GraphicPosition gPos2;

    private Graphic graphic;

    private void keyBoardInit() {

        Keyboard keyboard = new Keyboard(this);
        KeyboardEvent [] events = new KeyboardEvent[4];

        for (int i = 0; i < events.length; i++) {
            events[i] = new KeyboardEvent();

        }

        events[0].setKey(KeyboardEvent.KEY_1);
        events[0].setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        events[1].setKey(KeyboardEvent.KEY_2);
        events[1].setKeyboardEventType((KeyboardEventType.KEY_PRESSED));

        events[2].setKey(KeyboardEvent.KEY_3);
        events[2].setKeyboardEventType((KeyboardEventType.KEY_PRESSED));

        events[3].setKey(KeyboardEvent.KEY_SPACE);
        events[3].setKeyboardEventType((KeyboardEventType.KEY_PRESSED));


        for (int i = 0; i < events.length; i++) {
            keyboard.addEventListener((events[i]));

        }


    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

            switch (keyboardEvent.getKey()) {

                case KeyboardEvent.KEY_1:

                    humanPick = GraphicPosition.PLAYER_A_CARD_1;
                    System.out.println("Picked 1" + humanPick);
                    humanPicked = true;
                    break;

                case KeyboardEvent.KEY_2:

                    humanPick = GraphicPosition.PLAYER_A_CARD_2;
                    System.out.println("Picked 2" + humanPick);
                    humanPicked = true;
                    break;

                case KeyboardEvent.KEY_3:

                    humanPick = GraphicPosition.PLAYER_A_CARD_3;
                    System.out.println("Picked 3" + humanPick);
                    humanPicked = true;
                    break;
                default:
                    System.exit(0);
            }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

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

            int trickWinnerPos = orderOfPlay.indexOf(trickWinner);

            Collections.rotate(orderOfPlay, -trickWinnerPos);
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

            if (trumpPicks.size() ==  1) {
                return trumpPicks.get(0).getPlayer();
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
                Player player = iterator.next();
                player.take(card);
                try {
                    if (player == player1) {

                        card.getGraphicCard().draw();
                        card.getGraphicCard().move(gPos1);

                    } else {
                        card.getGraphicCard().draw();
                        card.getGraphicCard().move(gPos2);
                    }
                    System.out.println(card);
                } catch (InterruptedException e) {
                    e.getMessage();
                }
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

        this.player1 = new HumanPlayer("You", this);
        this.player2 = new ComputerPlayer("Computer");
        this.table = new Table();
        this.graphic = new Graphic();
        keyBoardInit();
    }

    private void showGraphicBriscola() {

        List<GraphicCard> graphicDeck = graphic.getGraphicDeck();
        GraphicCard graphicBriscola = table.briscola.getGraphicCard();
        try {
            graphicBriscola.move(GraphicPosition.TRUMP);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        graphicBriscola.draw();
        graphic.getCoverCards().get(0).draw();
    }

    private void setup() {
        int turn = Randomizer.getRandom(NUMBER_PLAYERS) + 1;

        graphic.setupGraphicDeck(table.deck.getStack());

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

        showGraphicBriscola();

        graphicDraw(turn);
        System.out.println(turn);
        System.out.println("The order of play at the 1st turn is " + table.printOrderOfPlay() + "\n");
    }

    private void graphicDraw(int turn) {

        try {
            if (turn == 1) {

                player1.getHand().get(0).getGraphicCard().draw();
                player1.getHand().get(0).getGraphicCard().move(GraphicPosition.PLAYER_A_CARD_1);

                player1.getHand().get(1).getGraphicCard().draw();
                player1.getHand().get(1).getGraphicCard().move(GraphicPosition.PLAYER_A_CARD_2);

                player1.getHand().get(2).getGraphicCard().draw();
                player1.getHand().get(2).getGraphicCard().move(GraphicPosition.PLAYER_A_CARD_3);

                player2.getHand().get(0).getGraphicCard().draw();
                player2.getHand().get(0).getGraphicCard().move(GraphicPosition.PLAYER_B_CARD_1);

                player2.getHand().get(1).getGraphicCard().draw();
                player2.getHand().get(1).getGraphicCard().move(GraphicPosition.PLAYER_B_CARD_2);

                player2.getHand().get(2).getGraphicCard().draw();
                player2.getHand().get(2).getGraphicCard().move(GraphicPosition.PLAYER_B_CARD_3);

                /**graphic.getCoverCards().get(1).draw();
                graphic.getCoverCards().get(1).move(GraphicPosition.PLAYER_B_CARD_1);

                graphic.getCoverCards().get(2).draw();
                graphic.getCoverCards().get(2).move(GraphicPosition.PLAYER_B_CARD_2);

                graphic.getCoverCards().get(3).draw();
                graphic.getCoverCards().get(3).move(GraphicPosition.PLAYER_B_CARD_3);*/

            } else {

                /**graphic.getCoverCards().get(1).draw();
                graphic.getCoverCards().get(1).move(GraphicPosition.PLAYER_B_CARD_1);

                graphic.getCoverCards().get(2).draw();
                graphic.getCoverCards().get(2).move(GraphicPosition.PLAYER_B_CARD_2);

                graphic.getCoverCards().get(3).draw();
                graphic.getCoverCards().get(3).move(GraphicPosition.PLAYER_B_CARD_3);*/

                player2.getHand().get(0).getGraphicCard().draw();
                player2.getHand().get(0).getGraphicCard().move(GraphicPosition.PLAYER_B_CARD_1);

                player2.getHand().get(1).getGraphicCard().draw();
                player2.getHand().get(1).getGraphicCard().move(GraphicPosition.PLAYER_B_CARD_2);

                player2.getHand().get(2).getGraphicCard().draw();
                player2.getHand().get(2).getGraphicCard().move(GraphicPosition.PLAYER_B_CARD_3);

                player1.getHand().get(0).getGraphicCard().draw();
                player1.getHand().get(0).getGraphicCard().move(GraphicPosition.PLAYER_A_CARD_1);

                player1.getHand().get(1).getGraphicCard().draw();
                player1.getHand().get(1).getGraphicCard().move(GraphicPosition.PLAYER_A_CARD_2);

                player1.getHand().get(2).getGraphicCard().draw();
                player1.getHand().get(2).getGraphicCard().move(GraphicPosition.PLAYER_A_CARD_3);


            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private void runTrick() {

        Player trickWinner = null;
        Iterator<Player> playerIterator = table.orderOfPlay.listIterator();
        Player player = null;
        Card card;

        // Ask each player in order to choose a card from their hand and play it. The order starts on the winner of last trick
        for (int i = 0; i < table.orderOfPlay.size(); i++) {

            player = table.orderOfPlay.get(i);
            System.out.println("Player " + player.getName() + " has a hand of " + player.printHand() + "\n");
            card = player.play();
            table.add(player, card);
            try {
                if (player == player1) {
                    gPos1 = card.getGraphicCard().getPosition();
                    card.getGraphicCard().move(GraphicPosition.TABLE_1);
                } else {
                    gPos2 = card.getGraphicCard().getPosition();
                    card.getGraphicCard().move(GraphicPosition.TABLE_2);
                }
            } catch (InterruptedException e) {
                e.getMessage();
            }

            System.out.println("Player " + player + " played " + table.sequence.get(table.sequence.size() - 1).getCard() + "\n");
        }

        table.setLeadSuit();
        System.out.println("The lead suit is " + table.leadSuit);

        trickWinner = table.findTrickWinner();
        List<Card> tableCards = table.getCards();
        trickWinner.collectCards(tableCards);

        try {
            if (trickWinner == player1) {
                tableCards.get(0).getGraphicCard().move(GraphicPosition.PLAYER_A_PILE);
                tableCards.get(1).getGraphicCard().move(GraphicPosition.PLAYER_A_PILE);
            } else {
                tableCards.get(0).getGraphicCard().move(GraphicPosition.PLAYER_B_PILE);
                tableCards.get(1).getGraphicCard().move(GraphicPosition.PLAYER_B_PILE);
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        System.out.println(trickWinner);

        // Use Collections.rotate() with negative shift to change the order of the sequence for the next round
        table.setupOrderForNextTrick(trickWinner);
        System.out.println("Completed trick. The trick winner is " + trickWinner.getName() + "\n" +
                "The next trick will be played in the following order: \n" + table.printOrderOfPlay() + "\n");
    }

    public Player run () {

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

        graphic.getCoverCards().get(0).delete();
        // Last 3 tricks of the game, in which each player plays each of its remaining cards
        while (player1.getHandSize() > 0) {
            runTrick();
            table.resetSequence();
        }

        gameWinner = findGameWinner();

        printWinner(gameWinner);

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

    public GraphicPosition getHumanPick() {
        return humanPick;
    }

    public boolean hasHumanPicked() {
        return this.humanPicked;
    }

    public void setHumanPicked(boolean picked) {
        this.humanPicked = picked;
    }

    public String printWinner(Player winner) {
        Text text;
        text = new Text(GraphicPosition.TABLE_1.getX(),GraphicPosition.TABLE_1.getY(), winner.getName() + " WON!!!");
        text.grow(300, 300);
        text.draw();
        text.setColor(Color.RED);
        return winner.getName();
    }

    @Override
    public String toString() {
        return player1 + " vs " + player2;
    }
}
