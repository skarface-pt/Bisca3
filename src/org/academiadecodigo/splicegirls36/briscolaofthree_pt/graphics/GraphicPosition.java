package org.academiadecodigo.splicegirls36.briscolaofthree_pt.graphics;

public enum GraphicPosition {
    PLAYER_A_CARD_1(538,30),
    PLAYER_A_CARD_2(688,30),
    PLAYER_A_CARD_3(838,30),
    PLAYER_A_PILE(1327,30),
    PLAYER_B_CARD_1(538,710),
    PLAYER_B_CARD_2(688,710),
    PLAYER_B_CARD_3(838,710),
    PLAYER_B_PILE(1327,710),
    DECK(60,370),
    TRUMP(90,400),
    TABLE_1(620,370),
    TABLE_2(765,370);

    private int pixelX;
    private int pixelY;

    GraphicPosition(int pixelX, int pixelY) {
        this.pixelX = pixelX;
        this.pixelY = pixelY;
    }

    public int getX() {
        return pixelX;
    }

    public int getY() {
        return pixelY;
    }

}
