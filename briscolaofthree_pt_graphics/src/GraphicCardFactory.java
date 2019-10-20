public class GraphicCardFactory {

    //  Method to produce and return a card
    public static GraphicCard newGraphicCard(Rank rank, Suit suit) {
        String imageURL;
        imageURL = "PNG/" + rank.getRankChar() + suit.getSuitChar() + ".png";
        GraphicCard graphicCard = new GraphicCard(suit, rank, imageURL, GraphicPosition.DECK);
        return graphicCard;
    }

    //  Method to produce and return the covered card
    public static GraphicCard newCoverCard() {
        GraphicCard coverCard = new GraphicCard(Suit.HEARTS, Rank.TWO, "PNG/Cover.png", GraphicPosition.DECK);
        return coverCard;
    }

}

