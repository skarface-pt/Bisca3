package org.academiadecodigo.splicegirls36.briscolaofthree_pt.player;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.Randomizer;
import org.academiadecodigo.splicegirls36.briscolaofthree_pt.card.Card;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name) {
        super(name);
    }

    @Override
    public Card play() {

        Card pick = hand.get(Randomizer.getRandom(hand.size()));
        return pick;

    }
}
