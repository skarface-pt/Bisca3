package org.academiadecodigo.splicegirls36.briscolaofthree_pt.graphics;

public class AnimationTwo {

    AnimationTwo() throws InterruptedException {
        GraphicCard[] graphicCards = new GraphicCard[44];
        int index = 0;
        for(Suit suit: Suit.values()) {
            for (Rank rank : Rank.values()) {
                graphicCards[index] = GraphicCardFactory.newGraphicCard(rank, suit);
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
