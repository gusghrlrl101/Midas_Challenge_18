package Midas;

import java.awt.Color;
import java.awt.Graphics;

public class circle extends shape {

    public circle(Color c, boolean fill1, int i) {
        super(c, fill1, i);
    }
    @Override
    void draw(Graphics g)
    {  g.setColor(shapecolor);
       g.fillOval(start_x-thickness/2,start_y-thickness/2,thickness,thickness);
    }
}