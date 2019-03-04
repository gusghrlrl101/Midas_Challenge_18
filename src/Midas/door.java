package Midas;

import java.awt.Color;
import java.awt.Graphics;

public class door extends shape {

	public door(Color c, boolean fill1, int i) {
		super(c, fill1, i);
	}

	@Override
	void draw(Graphics g) {
		g.setColor(shapecolor);
		if (thickness == 20) {
			g.fillRect(start_x - thickness, start_y - thickness / 2, thickness * 2, thickness / 2);
		} else {
			int temp = 20;
			g.fillRect(start_x - temp / 2, start_y - temp, temp / 2, temp * 2);
		}
	}

	boolean on_the_shape(int x, int y) {
		int min_x, min_y, max_x, max_y;
		if (thickness == 20) {
			min_x = start_x - thickness;
			min_y = start_y - thickness / 2;
			max_x = min_x + thickness * 2;
			max_y = min_y + thickness / 2;
		} else {
			int temp = 20;
			min_x = start_x - temp;
			min_y = start_y - temp / 2;
			max_x = min_x + temp * 2;
			max_y = min_y + temp / 2;
		}
		if (min_x <= x && x <= max_x && min_y <= y && y <= max_y) {
			return true;
		}
		return false;
	}
}