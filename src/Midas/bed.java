package Midas;

import java.awt.Color;
import java.awt.Graphics;

public class bed extends shape {

	public bed(Color c, boolean fill1, int i) {
		super(c, fill1, i);
		this.name = "Bed";
	}

	@Override
	void draw(Graphics g) {
		g.setColor(shapecolor);
		g.fillRect(start_x - thickness / 2, start_y - thickness / 2, 30, 50);
		g.setColor(Color.WHITE);
		g.fillRect(start_x - thickness / 4 + 1, start_y - thickness / 4, 15, 10);
		g.fillRect(start_x - thickness / 4, start_y + thickness / 2, 20, 25);
	}

	boolean on_the_shape(int x, int y) {
		int min_x = start_x - thickness / 2;
		int min_y = start_y - thickness / 2;
		int max_x = min_x + 30;
		int max_y = min_y + 50;

		if (min_x <= x && x <= max_x && min_y <= y && y <= max_y) {
			return true;
		}
		return false;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
}