package org.academiadecodigo.splicegirls36.briscolaofthree_pt.player;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.Game;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.Randomizer;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Card;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.player.Player;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name) {
        super(name);
    }

    @Override
    public void play() {

        this.pick = hand[Randomizer.getRandom(Game.STARTING_NUMBER_CARDS_HAND)];

    }
}
