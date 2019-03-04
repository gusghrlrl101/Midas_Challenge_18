package Midas;

import java.awt.Color;
import java.awt.Graphics;

public class tv extends shape {

	public tv(Color c, boolean fill1, int i) {
		super(c, fill1, i);
		this.name = "Tv";
	}

	@Override
	void draw(Graphics g) {
		g.setColor(shapecolor);
		g.fillRect(start_x - thickness / 2, start_y - thickness / 2, 40, 30);
		g.setColor(Color.WHITE);
		g.fillRect(start_x - thickness / 4 + 1, start_y - thickness / 4, 15, 10);
	}

	boolean on_the_shape(int x, int y) {
		int min_x = start_x - thickness / 2;
		int min_y = start_y - thickness / 2;
		int max_x = min_x + 40;
		int max_y = min_y + 30;

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