package org.academiadecodigo.splicegirls36.briscolaofthree_pt.graphics;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Card;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphicCardFactory {

    public static List<GraphicCard> createGraphicCards(List<Card> deck) {

        List<GraphicCard> graphicCards = new LinkedList<>();
        GraphicCard graphicCard;

        for (int i = 0; i < deck.size(); i++) {
            graphicCard = newGraphicCard(deck.get(i).getRank(), deck.get(i).getSuit());
            graphicCards.add(graphicCard);
            deck.get(i).setGraphicCard(graphicCard);
        }
        return graphicCards;
    }

    public static List<GraphicCard> createCoverCards() {

        List<GraphicCard> coverCards = new ArrayList<>();

        for (int i = 0; i < Graphic.NUMBER_COVER_CARDS; i++) {

            coverCards.add(newCoverCard());
        }
        return coverCards;
    }

    //  Method to produce and return a card
    public static GraphicCard newGraphicCard(Card.Rank rank, Card.Suit graphicSuit) {
        String imageURL;
        imageURL = Graphic.PICTURES_PATH + rank.getRankChar() + graphicSuit.getSuitChar() + Graphic.PICTURE_EXTENSION;
        System.out.println(imageURL);
        GraphicCard graphicCard = new GraphicCard(graphicSuit, rank, imageURL, GraphicPosition.DECK);
        return graphicCard;
    }

    //  Method to produce and return the covered card
    public static GraphicCard newCoverCard() {
        GraphicCard coverCard = new GraphicCard(Card.Suit.HEARTS, Card.Rank.TWO, Graphic.PICTURES_PATH + "/Cover" + Graphic.PICTURE_EXTENSION, GraphicPosition.DECK);
        return coverCard;
    }

}

