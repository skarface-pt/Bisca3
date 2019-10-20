public enum Suit {
    CLUBS("C"),
    DIAMONDS("D"),
    HEARTS("H"),
    SPADES("S");

    private String suitChar;

    Suit(String suitChar) {
        this.suitChar = suitChar;
    }

    public String getSuitChar() {
        return suitChar;
    }
}
