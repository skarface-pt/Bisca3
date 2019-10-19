package org.academiadecodigo.splicegirls36.briscolaofthree_pt.startingmenu;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Menu implements KeyboardHandler {

    Rectangle rectangle;
    private int padding;
    Picture picture;
    Text text;



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

        picture = new Picture(150, 250, "aces.png");
        picture.draw();

        picture = new Picture(1000, 600, "start.png");
        picture.draw();

        text = new Text(1015,700, "Please press 'SPACEBAR' to start or 'Q' to quit");
        text.draw();
        text.setColor(Color.RED);

        picture = new Picture(150, 115, "biscados3.png");
        picture.draw();

        picture = new Picture(1300,130, "rsz_academia.jpg");
        picture.draw();

        text = new Text(1300, 180, "Dominor");
        text.draw();
        text.setColor(Color.WHITE);

        text = new Text(1300, 195, "Manuel");
        text.draw();
        text.setColor(Color.WHITE);

        text = new Text(1300, 210, "Skarface");
        text.draw();
        text.setColor(Color.WHITE);






        keyBoardInit();



    }

    private void keyBoardInit() {

        Keyboard keyboard = new Keyboard(this);
        KeyboardEvent [] events = new KeyboardEvent[2];

        for (int i = 0; i < events.length; i++) {
            events[i] = new KeyboardEvent();

        }

        events[0].setKey(KeyboardEvent.KEY_SPACE);
        events[0].setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        events[1].setKey(KeyboardEvent.KEY_Q);
        events[1].setKeyboardEventType((KeyboardEventType.KEY_PRESSED));


        for (int i = 0; i < events.length; i++) {
            keyboard.addEventListener((events[i]));

        }


    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_SPACE:
                //Start game;
                break;

                case KeyboardEvent.KEY_Q:
                    //Exit game;
                    break;

        }


    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
