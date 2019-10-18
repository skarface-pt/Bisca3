package org.academiadecodigo.splicegirls36.briscolaofthree_pt.card;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.player.Player;

public class Pick {

    private Player player;
    private Card card;

    public Pick(Player player, Card card) {

        this.player = player;
        this.card = card;
    }

    public Player getPlayer() {
        return player;
    }

    public Card getCard() {
        return card;
    }
}
