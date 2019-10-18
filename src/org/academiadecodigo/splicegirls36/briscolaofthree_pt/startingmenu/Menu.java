package org.academiadecodigo.splicegirls36.briscolaofthree_pt.startingmenu;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Menu implements KeyboardHandler {

    Rectangle rectangle;
    private int padding;
    Canvas canvas;
    private Picture picture;



    public Menu () {

        padding = 10;
        rectangle = new Rectangle(padding, padding, 1500, 900);
        rectangle.draw();
        rectangle.setColor(Color.LIGHT_GRAY);
        rectangle.fill();
        rectangle = new Rectangle(100,100, 1300, 700);
        rectangle.draw();
        rectangle.setColor(Color.BLACK);
        rectangle.fill();



    } public void logo () {

        picture = new Picture(275,200, "aces.png");
        picture.draw();




        keyBoardInit();
    }




    private void keyBoardInit() {

        Keyboard keyboard = new Keyboard(this);



    }





    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {



    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
