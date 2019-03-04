package Midas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

public class shape extends JPanel {
	String name = "a";

	int start_x;
	int start_y;
	int end_x;
	int end_y;
	int move_start_x;
	int move_start_y;
	int move_end_x;
	int move_end_y;
	
	int shape;
	int thickness;
	Vector<Integer> point = new Vector<>();

	boolean fill = false;
	boolean move = false;
	boolean size = false;

	Color shapecolor;

	public shape(Color c, boolean fill1, int i) {
		shapecolor = c;
		fill = fill1;
		shape = i;
	}

	void update() {
	}

	int getwidth() {
		int answer = start_x - end_x;
		if (answer < 0) {
			answer = -answer;
		}
		return answer;
	}

	int getheigh() {
		int answer = start_y - end_y;
		if (answer < 0) {
			answer = -answer;
		}
		return answer;
	}

	int get_distance_move_x() {
		int distancex = move_end_x - move_start_x;
		return distancex;
	}

	int get_distance_move_y() {
		int distancey = move_end_y - move_start_y;
		return distancey;
	}

	int getpoint(int i) {
		return point.get(i);
	}

	int getpointsize() {
		return point.size();
	}

	void draw(Graphics g) {}

	void movepoint() {}

	boolean on_the_shape(int x,int y)
	{   
	   return false;
	}
}