package org.academiadecodigo.splicegirls36.briscolaofthree_pt.graphics;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;


public class Graphic {

    public static final String PICTURES_PATH = "resources/card_pictures/";
    public static final String PICTURE_EXTENSION = ".png";
    public static final int NUMBER_COVER_CARDS = 6;

    private int canvasWidth = 1500;
    private int canvasHeight = 900;

    private Rectangle rectangle;

    private List<GraphicCard> graphicDeck;
    private List<GraphicCard> coverCards;

    public Graphic() {
        rectangle = new Rectangle(10, 10, canvasWidth, canvasHeight);
        this.coverCards = GraphicCardFactory.createCoverCards();
        init();
        grid();
    }

    public void init() {

        rectangle.setColor(Color.LIGHT_GRAY);
        rectangle.fill();

        //coverCards.get(0).draw();
    }

    public void grid() {
        Rectangle[] cells = new Rectangle[GraphicPosition.values().length];
        final int BORDER = 5; // border between picture and cell
        int index = 0;
        for (GraphicPosition cell : GraphicPosition.values()) {
            cells[index] = new Rectangle(cell.getX() + BORDER, cell.getY() + BORDER, 117 - 2 * BORDER, 179 - 2 * BORDER);
            cells[index].setColor(Color.WHITE);
            cells[index].draw();
        }
    }

    public void setGraphicDeck(List<GraphicCard> deck) {
        this.graphicDeck = deck;
    }

    public List<GraphicCard> getGraphicDeck() {
        return graphicDeck;
    }

    public List<GraphicCard> getCoverCards() {
        return coverCards;
    }

    public GraphicCard pop() {
        return graphicDeck.remove(graphicDeck.size() - 1);
    }

    public void setupGraphicDeck(List<Card> deck) {

        graphicDeck = GraphicCardFactory.createGraphicCards(deck);
    }
}
