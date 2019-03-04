package Midas;

import java.awt.Color;
import java.awt.Graphics;

public class wall extends shape {
	int group = -1;

	public wall(Color c, boolean fill1, int i) {
		super(c, fill1, i);
	}
	void set_group(int q) {
		group = q;
	}

	void update() {
		int wide = start_x - end_x;
		int width = start_y - end_y;

		if (wide < 0) {
			wide = -wide;
		}
		if (width < 0) {
			width = -width;
		}
		if (wide < width) {
			end_x = start_x;
		} else {
			end_y = start_y;
		}
	}

	void draw(Graphics g) {

		int wide = start_x - end_x;
		int width = start_y - end_y;

		if (wide < 0) {
			wide = -wide;
		}
		if (width < 0) {
			width = -width;
		}

		int[] x = { start_x, start_x, end_x, end_x };
		int[] y = { start_y, end_y, end_y, start_y };
		if (wide < width) {
			end_x = start_x;
			x[2] = x[1];
			x[3] = x[1];
			x[0] = x[0] + thickness / 2;
			x[1] = x[1] + thickness / 2;
			x[2] = x[2] - thickness / 2;
			x[3] = x[3] - thickness / 2;

		} else {
			end_y = start_y;
			y[1] = y[0];
			y[2] = y[0];
			y[0] = y[0] + thickness / 2;
			y[3] = y[3] + thickness / 2;
			y[1] = y[1] - thickness / 2;
			y[2] = y[2] - thickness / 2;
		}
		g.setColor(shapecolor);

		g.drawPolygon(x, y, 4);
		g.fillPolygon(x, y, 4);
	}

	boolean on_the_shape(int x, int y) {
		int min_x, min_y, max_x, max_y = 0;
		if (start_x == end_x) {
			min_x = start_x - thickness / 2;
			max_x = start_x + thickness / 2;
			if (start_y < end_y) {
				max_y = end_y;
				min_y = start_y;
			} else {
				max_y = start_y;
				min_y = end_y;
			}
		} else {
			min_y = start_y - thickness / 2;
			max_y = start_y + thickness / 2;
			if (start_x < end_x) {
				max_x = end_x;
				min_x = start_x;
			} else {
				max_x = start_x;
				min_x = end_x;
			}
		}
		if (min_x <= x && x <= max_x && min_y <= y && y <= max_y) {
			return true;
		}
		return false;
	}
}
