package org.academiadecodigo.splicegirls36.briscolaofthree_pt;

public class Randomizer {

    /** Formula to get random integers between Min and Max using Math.random():
     * Min + (int)(Math.random() * ((Max - Min) + 1))
     * @param max The maximum integer to return
     * @return random integer between 0 and max (not included)
     */
    public static int getRandom(int max) {
        return (int) (Math.random() * max);
    }
}
