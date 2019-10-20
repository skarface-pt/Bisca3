package org.academiadecodigo.splicegirls36.briscolaofthree_pt.player;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.Game;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Card;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.graphics.GraphicPosition;

public class HumanPlayer extends Player {


    private Game game;

    public HumanPlayer(String name, Game game) {
        super(name);
        this.game = game;
    }

    @Override
    public Card play() {

        GraphicPosition pick = null;
        Card result = null;
        int index = 0;

        while (!game.hasHumanPicked()) {
            System.out.println("Pick something please!");
        }

        pick = game.getHumanPick();
        switch (pick) {
            case PLAYER_A_CARD_1:
                index = hand.indexOf(findCardForGraphicPosition(GraphicPosition.PLAYER_A_CARD_1));
                result = hand.remove(index);
                break;
            case PLAYER_A_CARD_2:
                index = hand.indexOf(findCardForGraphicPosition(GraphicPosition.PLAYER_A_CARD_2));
                result = hand.remove(index);
                break;
            case PLAYER_A_CARD_3:
                index = hand.indexOf(findCardForGraphicPosition(GraphicPosition.PLAYER_A_CARD_3));
                result = hand.remove(index);
                break;
            default:
                System.err.println("Wtf this is not possible");
        }
        game.setHumanPicked(false);
        return result;
    }

    private Card findCardForGraphicPosition(GraphicPosition pos) {

        for (Card card: hand) {
            if (card.getGraphicCard().getPosition().equals(pos)) {
                return card;
            }
        }
        return null;
    }
}
