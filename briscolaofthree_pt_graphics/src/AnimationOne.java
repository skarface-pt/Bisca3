public class AnimationOne {

    public AnimationOne() throws InterruptedException {
    GraphicCard card1 = new GraphicCard(Suit.DIAMONDS,Rank.KING, "PNG/KD.png", GraphicPosition.TABLE_1);
        card1.draw();
        card1.move(GraphicPosition.PLAYER_B_CARD_3);

        while (true) {
            card1.move(GraphicPosition.values()[(int) (Math.random() * 12)]);
            System.out.println(card1.getPosition());
            Thread.sleep(200);
        }
    }
}
