package org.academiadecodigo.splicegirls36.briscolaofthree_pt.graphics;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Card;

public class AnimationOne {

    public AnimationOne() throws InterruptedException {
        GraphicCard card1 = new GraphicCard(Card.Suit.DIAMONDS, Card.Rank.KING, Graphic.PICTURES_PATH + "KD" + Graphic.PICTURE_EXTENSION, GraphicPosition.TABLE_1);
        card1.draw();
        card1.move(GraphicPosition.PLAYER_B_CARD_3);

        while (true) {
            card1.move(GraphicPosition.values()[(int) (Math.random() * 12)]);
            System.out.println(card1.getPosition());
            Thread.sleep(200);
        }
    }
}
