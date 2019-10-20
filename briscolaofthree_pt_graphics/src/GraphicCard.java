import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GraphicCard {

    private Suit suit;
    private Rank rank;
    private String imageURL;
    private Picture picture;
    private GraphicPosition initialPosition;
    private GraphicPosition finalPosition;
    private final int DELAY = 4; // milliseconds
    private final double ITERATIONS = 100.0; // positions along the movement

    public GraphicCard(Suit suit, Rank rank, String imageURL, GraphicPosition position) {
        this.suit = suit;
        this.rank = rank;
        this.imageURL = imageURL;
        this.initialPosition = position;
        picture = new Picture(initialPosition.getX(),initialPosition.getY(), imageURL);
    }

    public void draw() {
        picture.draw();
    }

    public void delete() {
        picture.delete();
    }

    /*public void translate(int xPixels, int yPixels) {
        picture.translate(xPixels, yPixels);
    }*/

    public GraphicPosition getPosition() {
        return finalPosition;
    }

    public void move(GraphicPosition position) throws InterruptedException {
        finalPosition = position;
        double xMove = (finalPosition.getX() - picture.getX())/ITERATIONS;
        double yMove = (finalPosition.getY() - picture.getY())/ITERATIONS;
        for(int i = 0; i < ITERATIONS; i++) {
            picture.translate((int) (i * xMove) - (int) ((i - 1) * xMove), (int) (i * yMove) - (int) ((i - 1) * yMove));
            Thread.sleep(DELAY);
        }
    }
}


