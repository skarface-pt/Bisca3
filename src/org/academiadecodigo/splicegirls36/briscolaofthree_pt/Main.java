package org.academiadecodigo.splicegirls36.briscolaofthree_pt;

import org.academiadecodigo.splicegirls36.briscolaofthree_pt.player.ComputerPlayer;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();

        System.out.println(" The winner is " + game.run());
        //System.out.println(game.printWinner(new ComputerPlayer("dsggv")));
    }
}
