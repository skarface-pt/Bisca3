package org.academiadecodigo.splicegirls36.briscolaofthree_pt.graphics;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Card;

public class AnimationTwo {

    AnimationTwo() throws InterruptedException {
        GraphicCard[] graphicCards = new GraphicCard[44];
        int index = 0;
        for(Card.Suit graphicSuit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                graphicCards[index] = GraphicCardFactory.newGraphicCard(rank, graphicSuit);
                index ++;
            }
        }

        graphicCards[40] = GraphicCardFactory.newCoverCard();
        graphicCards[41] = GraphicCardFactory.newCoverCard();
        graphicCards[42] = GraphicCardFactory.newCoverCard();
        graphicCards[43] = GraphicCardFactory.newCoverCard();

        while (true) {
            for(int i = 0; i < 44; i++) {
                graphicCards[i].move(GraphicPosition.values()[(int) (Math.random() * 12)]);
                graphicCards[i].draw();
            }
        }
    }



}
