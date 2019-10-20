package org.academiadecodigo.splicegirls36.briscolaofthree_pt.graphics;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Card;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Graphic screen = new Graphic();

        // AnimationOne ani = new AnimationOne();
        AnimationTwo ani = new AnimationTwo();

        // GraphicCard card1 = GraphicCardFactory.newGraphicCard(Rank.ACE, Suit.DIAMONDS);
        // card1.draw();
        // card1.move(GraphicPosition.PLAYER_A_CARD_3);


        GraphicCard card1 = GraphicCardFactory.newGraphicCard(Card.Rank.KING, Card.Suit.CLUBS);
        card1.draw();
        Thread.sleep(500);
        card1.move(GraphicPosition.PLAYER_A_PILE);




    }
}
