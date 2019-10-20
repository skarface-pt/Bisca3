import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class SimpleGraphics {

    public static void main(String[] args) {

        int canvasWidth = 1500;
        int canvasHigh = 900;

        Rectangle rect1 = new Rectangle(10, 10, canvasWidth, canvasHigh);
        //Rectangle rect2 = new Rectangle(538, 30, 115, 160);
        //Rectangle rect3 = new Rectangle(693, 30, 115, 160);
        //Rectangle rect4 = new Rectangle(838, 30, 115, 160);
        Rectangle rect5 = new Rectangle(1327, 30, 115, 160);

        Rectangle rect6 = new Rectangle(60, 370, 115, 160);
        Rectangle rect7 = new Rectangle(620, 370, 115, 160);
        Rectangle rect8 = new Rectangle(765, 370, 115, 160);

        Rectangle rect9 = new Rectangle(90, 400, 115, 160);

        Rectangle rect10 = new Rectangle(538, 710, 115, 160);
        Rectangle rect11 = new Rectangle(693, 710, 115, 160);
        Rectangle rect12 = new Rectangle(838, 710, 115, 160);
        Rectangle rect13 = new Rectangle(1327, 710, 115, 160);


        rect1.setColor(Color.LIGHT_GRAY);
        //rect2.setColor(Color.BLUE);
        //rect3.setColor(Color.BLUE);
        //rect4.setColor(Color.BLUE);
        rect5.setColor(Color.BLUE);
        rect7.setColor(Color.BLUE);
        rect8.setColor(Color.BLUE);
        rect9.setColor(Color.WHITE);
        rect10.setColor(Color.BLUE);
        rect11.setColor(Color.BLUE);
        rect12.setColor(Color.BLUE);
        rect13.setColor(Color.BLUE);


        rect1.fill();
        //rect2.fill();
        //rect3.fill();
        //rect4.fill();
        rect5.fill();
        rect6.fill();
        rect7.fill();
        rect8.fill();
        rect9.fill();
        rect10.fill();
        rect11.fill();
        rect12.fill();
        rect13.fill();

        //Picture pic = new Picture(60, 370, "/Users/codecadet/Documents/briscolaofthree_pt/PNG/little.png");
        Picture pic = new Picture(688, 30, "PNG/1.png");
        pic.draw();

        Picture pic2 = new Picture(538, 30, "PNG/2.png");
        pic2.draw();

        Picture pic3 = new Picture(838, 30, "PNG/3.png");
        pic3.draw();

        Picture pic4 = new Picture(340, 330, "PNG/4.png");
        pic4.draw();

        Picture pic5 = new Picture(300, 370, "PNG/RedCover.png");
        pic5.draw();


      


        System.out.println(pic.pixels());


    }


}