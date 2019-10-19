public enum Rank {
    ACE("A"),
    SEVEN("7"),
    KING("K"),
    QUEEN("Q"),
    JACK("J"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2");

    private String rankChar;

    Rank(String rankChar) {
        this.rankChar = rankChar;
    }

    public String getRankChar() {
        return rankChar;
    }
}
