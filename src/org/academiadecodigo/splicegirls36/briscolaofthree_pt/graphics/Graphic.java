package org.academiadecodigo.splicegirls36.briscolaofthree_pt.graphics;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;


public class Graphic {

    private int canvasWidth = 1500;
    private int canvasHigh = 900;

    public Graphic() {
            init();
            grid();
    }

    public void init() {
        Rectangle rect1 = new Rectangle(10, 10, canvasWidth, canvasHigh);
        rect1.setColor(Color.LIGHT_GRAY);
        rect1.fill();
    }

    public void grid() {
        Rectangle[] cells = new Rectangle[GraphicPosition.values().length];
        final int BORDER = 5; // border between picture and cell
        int index = 0;
        for (GraphicPosition cell : GraphicPosition.values()) {
            cells[index] = new Rectangle(cell.getX() + BORDER, cell.getY() + BORDER, 117 - 2 * BORDER, 179 - 2 * BORDER);
            cells[index].setColor(Color.WHITE);
            cells[index].draw();
        }
    }
}
