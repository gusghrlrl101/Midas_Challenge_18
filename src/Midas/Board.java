package Midas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener, MouseMotionListener {
	UI ui;
	algorithm al;
	boolean checc = false;
	BufferedImage paintImage = new BufferedImage(800, 500, BufferedImage.TYPE_3BYTE_BGR);

	ArrayList<shape> mylist = new ArrayList<>();
	ArrayList<shape> templist = new ArrayList<>();
	int shape = 1;
	boolean move = false;
	boolean fill = false;

	boolean wall_button = false;

	boolean first_wall = false;
	boolean making_wall = false;

	int real_index = 0;
	int movecheck = 0;
	int removecheck = 0;
	String temp;

	Color shapecolor = new Color(0, 0, 0);

	Board(UI ui) {
		al = new algorithm();
		this.ui = ui;
		this.setSize(800, 500);
		this.setLayout(null);
		this.setFocusable(true);
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1000, 800);
		for (int i = 0; i < mylist.size(); i++) {
			mylist.get(i).draw(g);
			g.setColor(Color.BLACK);
			if (mylist.get(i).shape == 4)
				g.drawString(mylist.get(i).getName(), mylist.get(i).start_x - 4, mylist.get(i).start_y + 50);
			if (mylist.get(i).shape == 5)
				g.drawString(mylist.get(i).getName(), mylist.get(i).start_x - 4, mylist.get(i).start_y + 30);
		}
		// g.drawImage(paintImage, 0, 0, null);
		for (int i = 0; i < templist.size(); i++) {
			templist.get(i).draw(g);
		}
		if (shape != 0)
			templist.clear();
	}

	public void mousePressed(MouseEvent e) {

		if (move == true) {
		}

		if (move == false) {
			switch (shape) {
			case 0: {
				break;
			}

			case 1: {
				int wall_size = 0;
				for (int i = 0; i < mylist.size(); i++) {
					if (mylist.get(i).shape == 1) {
						wall_size++;
					}
				}
				if (wall_size == 0) {
					circle c = new circle(shapecolor, fill, 0);
					mylist.add(c);
					mylist.get(mylist.size() - 1).start_x = e.getX();
					mylist.get(mylist.size() - 1).start_y = e.getY();
					mylist.get(mylist.size() - 1).end_x = e.getX();
					mylist.get(mylist.size() - 1).end_y = e.getY();
					mylist.get(mylist.size() - 1).thickness = 20;
					wall w = new wall(shapecolor, fill, shape);
					w.start_x = mylist.get(mylist.size() - 1).end_x;
					w.start_y = mylist.get(mylist.size() - 1).end_y;
					w.end_x = mylist.get(mylist.size() - 1).end_x;
					w.end_y = mylist.get(mylist.size() - 1).end_y;
					w.thickness = 8;
					mylist.add(w);

					first_wall = true;
					making_wall = true;
					break;

				} else if (first_wall == false && making_wall == false) {
					int on_the_wall_index = 0;
					boolean on_the_wall = false;
					for (int i = 0; i < mylist.size(); i++) {
						if (mylist.get(i).on_the_shape(e.getX(), e.getY()) && mylist.get(i).shape == 1) {
							on_the_wall = true;
							on_the_wall_index = i;
							break;
						}
					}
					if (on_the_wall) {
						making_wall = true;
						wall w = new wall(shapecolor, fill, shape);
						if (mylist.get(on_the_wall_index).end_x == mylist.get(on_the_wall_index).start_x) {
							w.start_x = mylist.get(on_the_wall_index).end_x;
							w.start_y = e.getY();
							w.end_x = mylist.get(on_the_wall_index).end_x;
							w.end_y = e.getY();
						} else {
							w.start_x = e.getX();
							w.start_y = mylist.get(on_the_wall_index).end_y;
							w.end_x = e.getX();
							w.end_y = mylist.get(on_the_wall_index).end_y;
						}
						w.thickness = 8;
						mylist.add(w);
					} else {
						break;
					}
				} else if (first_wall == false && making_wall == true) {
					mylist.get(mylist.size() - 1).end_x = e.getX();
					mylist.get(mylist.size() - 1).end_y = e.getY();
					mylist.get(mylist.size() - 1).update();

					boolean object_cross = false;
					for (int i = 0; i < mylist.size(); i++) {
						if (mylist.get(i).shape >= 4) {
							if (mylist.get(mylist.size() - 1).end_y == mylist.get(mylist.size() - 1).start_y) {
								for (int j = mylist.get(mylist.size() - 1).start_x; j <= mylist
										.get(mylist.size() - 1).end_x; j++) {
									if (mylist.get(i).on_the_shape(j, mylist.get(mylist.size() - 1).end_y
											+ mylist.get(mylist.size() - 1).thickness / 2)) {
										object_cross = true;
										break;
									} else if (mylist.get(i).on_the_shape(j, mylist.get(mylist.size() - 1).end_y
											- mylist.get(mylist.size() - 1).thickness / 2)) {
										object_cross = true;
										break;
									}
								}
								if (object_cross) {
									break;
								}
							}
							if (mylist.get(mylist.size() - 1).end_x == mylist.get(mylist.size() - 1).start_x) {
								for (int j = mylist.get(mylist.size() - 1).start_y; j <= mylist
										.get(mylist.size() - 1).end_y; j++) {
									if (mylist.get(i).on_the_shape(mylist.get(mylist.size() - 1).end_x
											+ mylist.get(mylist.size() - 1).thickness / 2, j)) {
										object_cross = true;
										break;
									} else if (mylist.get(i).on_the_shape(mylist.get(mylist.size() - 1).end_x
											- mylist.get(mylist.size() - 1).thickness / 2, j)) {
										object_cross = true;
										break;
									}
								}
								if (object_cross) {
									break;
								}
							}
						}
					}
					if (object_cross) {
						break;
					}
					int on_the_wall_index = 0;
					boolean on_the_wall = false;
					for (int i = 0; i < mylist.size() - 1; i++) {
						if (mylist.get(i).on_the_shape(mylist.get(mylist.size() - 1).end_x,
								mylist.get(mylist.size() - 1).end_y) && mylist.get(i).shape == 1) {
							on_the_wall = true;
							on_the_wall_index = i;
							break;
						}
					}
					if (on_the_wall) {
						if (mylist.get(on_the_wall_index).start_x == mylist.get(on_the_wall_index).end_x) {
							mylist.get(mylist.size() - 1).end_x = mylist.get(on_the_wall_index).start_x;
							mylist.get(mylist.size() - 1).end_y = mylist.get(mylist.size() - 1).end_y;
						} else {
							mylist.get(mylist.size() - 1).end_x = mylist.get(mylist.size() - 1).end_x;
							mylist.get(mylist.size() - 1).end_y = mylist.get(on_the_wall_index).start_y;
						}
						making_wall = false;
						checc = al.check(mylist, real_index);
						break;
					} else {
						wall w = new wall(shapecolor, fill, shape);
						w.start_x = mylist.get(mylist.size() - 1).end_x;
						w.start_y = mylist.get(mylist.size() - 1).end_y;
						w.end_x = mylist.get(mylist.size() - 1).end_x;
						w.end_y = mylist.get(mylist.size() - 1).end_y;
						w.thickness = 8;
						mylist.add(w);
					}
				}
				if (first_wall == true && making_wall == true) {
					mylist.get(mylist.size() - 1).end_x = e.getX();
					mylist.get(mylist.size() - 1).end_y = e.getY();
					mylist.get(mylist.size() - 1).update();
					boolean end_making_wall = false;

					int circle_index = 0;
					for (int i = 0; i < mylist.size(); i++) {
						if (mylist.get(i).shape == 0) {
							circle_index = i;
							mylist.get(i).shapecolor = new Color(123, 123, 123);
							break;
						}
					}
					int C_X = mylist.get(circle_index).start_x - (mylist.get(circle_index).thickness / 2) - 4;
					int C_Y = mylist.get(circle_index).start_y - (mylist.get(circle_index).thickness / 2) - 4;
					int E_X = mylist.get(circle_index).start_x + (mylist.get(circle_index).thickness / 2) + 4;
					int E_Y = mylist.get(circle_index).start_y + (mylist.get(circle_index).thickness / 2) + 4;

					int[] candi_x = { -1, -1 };
					int[] candi_y = { -1, -1 };
					if (mylist.get(mylist.size() - 1).end_x == mylist.get(mylist.size() - 1).start_x)// veri
					{
						candi_x[0] = mylist.get(mylist.size() - 1).end_x;
						candi_x[1] = mylist.get(mylist.size() - 1).end_x;
						candi_y[0] = mylist.get(mylist.size() - 1).end_y
								+ (mylist.get(mylist.size() - 1).thickness / 2);
						candi_y[1] = mylist.get(mylist.size() - 1).end_y
								- (mylist.get(mylist.size() - 1).thickness / 2);
					} else {
						candi_x[0] = mylist.get(mylist.size() - 1).end_x
								+ (mylist.get(mylist.size() - 1).thickness / 2);
						candi_x[1] = mylist.get(mylist.size() - 1).end_x
								- (mylist.get(mylist.size() - 1).thickness / 2);
						candi_y[0] = mylist.get(mylist.size() - 1).end_y;
						candi_y[1] = mylist.get(mylist.size() - 1).end_y;
					}

					for (int i = 0; i < 2; i++) {
						if (C_X <= candi_x[i] && candi_x[i] <= E_X && C_Y <= candi_y[i] && candi_y[i] <= E_Y) {
							end_making_wall = true;
							shape = 0;
						}
					}
					if (end_making_wall) {
						if (mylist.get(mylist.size() - 1).start_x == mylist.get(mylist.size() - 1).end_x) {
							int index = mylist.size() - 1;
							for (int i = mylist.size() - 1; i >= 0; i--) {
								if (mylist.get(i).start_x == mylist.get(i - 1).start_x) {
									continue;
								} else {
									index = i - 1;
									break;
								}
							}
							for (int i = index + 1; i < mylist.size();) {
								if (mylist.get(i).shape != 1) {
									i++;
									continue;
								}
								mylist.remove(i);
							}
							mylist.get(index).end_x = mylist.get(circle_index).start_x;
							mylist.get(index).end_y = mylist.get(index).start_y;
							wall w = new wall(shapecolor, fill, 1);
							w.start_x = mylist.get(index).end_x;
							w.start_y = mylist.get(index).end_y;
							w.end_x = mylist.get(circle_index).end_x;
							w.end_y = mylist.get(circle_index).end_y;
							w.thickness = 8;
							mylist.add(w);
						} else {
							int index = mylist.size() - 1;
							for (int i = mylist.size() - 1; i >= 0; i--) {
								if (mylist.get(i).start_y == mylist.get(i - 1).start_y) {
									if (mylist.get(i).shape != 1) {
										i++;
										continue;
									}
									continue;
								} else {
									index = i - 1;
									break;
								}
							}
							for (int i = index + 1; i < mylist.size();) {
								mylist.remove(i);
							}
							mylist.get(index).end_y = mylist.get(circle_index).start_y;
							mylist.get(index).end_x = mylist.get(index).start_x;

							wall w = new wall(shapecolor, fill, 1);
							w.start_x = mylist.get(index).end_x;
							w.start_y = mylist.get(index).end_y;
							w.end_x = mylist.get(circle_index).end_x;
							w.end_y = mylist.get(circle_index).end_y;
							w.thickness = 8;
							mylist.add(w);
						}
						mylist.remove(circle_index);

						making_wall = false;
						first_wall = false;

						real_index = mylist.size();
						checc = al.check(mylist, real_index);
					} else {
						wall w = new wall(shapecolor, fill, 1);
						w.start_x = mylist.get(mylist.size() - 1).end_x;
						w.start_y = mylist.get(mylist.size() - 1).end_y;
						w.end_x = mylist.get(mylist.size() - 1).end_x;
						w.end_y = mylist.get(mylist.size() - 1).end_y;
						w.thickness = 8;
						mylist.add(w);
					}
					break;
				}
				break;
			}
			case 2: {
				int on_the_wall_index = 0;
				boolean on_the_wall = false;
				for (int i = 0; i < mylist.size(); i++) {
					if (mylist.get(i).on_the_shape(e.getX(), e.getY()) && mylist.get(i).shape == 1) {
						on_the_wall = true;
						on_the_wall_index = i;
						break;
					}
				}
				if (on_the_wall) {
					door d = new door(new Color(139, 69, 19), fill, 2);
					mylist.add(d);
					
		               if(mylist.get(on_the_wall_index).start_x==mylist.get(on_the_wall_index).end_x)
		                    {
		                       mylist.get(mylist.size() - 1).start_x = mylist.get(on_the_wall_index).start_x;
		                      mylist.get(mylist.size() - 1).start_y = e.getY();
		                      mylist.get(mylist.size() - 1).thickness = -20;
		                    }
		                    else
		                    {
		                       mylist.get(mylist.size() - 1).start_x =  e.getX();
		                      mylist.get(mylist.size() - 1).start_y =mylist.get(on_the_wall_index).start_y;
		                      mylist.get(mylist.size() - 1).thickness = 20;

		                    }
					shape = 0;
					checc = al.check(mylist, real_index);
				}
				break;
			}
			case 3: {
				int on_the_wall_index = 0;
				boolean on_the_wall = false;
				for (int i = 0; i < mylist.size(); i++) {
					if (mylist.get(i).on_the_shape(e.getX(), e.getY()) && mylist.get(i).shape == 1) {
						on_the_wall = true;
						on_the_wall_index = i;
						break;
					}
				}
				if (on_the_wall) {
					window w = new window(Color.BLUE, fill, 3);
					mylist.add(w);
					 if(mylist.get(on_the_wall_index).start_x==mylist.get(on_the_wall_index).end_x)
	                    {
	                       mylist.get(mylist.size() - 1).start_x = mylist.get(on_the_wall_index).start_x;
	                      mylist.get(mylist.size() - 1).start_y = e.getY();
	                      mylist.get(mylist.size() - 1).thickness = -20;
	                    }
	                    else
	                    {
	                       mylist.get(mylist.size() - 1).start_x =  e.getX();
	                      mylist.get(mylist.size() - 1).start_y =mylist.get(on_the_wall_index).start_y;
	                      mylist.get(mylist.size() - 1).thickness = 20;
	                    }
					shape = 0;
					checc = al.check(mylist, real_index);
				}
				break;
			}
			case 4: {
				boolean shape_cross=false;
				for(int i=0;i<mylist.size();i++)
				{
					if(mylist.get(i).shape<=3)
					{
						int temp_x= e.getX()-10;
						int temp_y= e.getY()-10;
						for(int j=temp_x;j<=temp_x+30;j++)
						{
							if(mylist.get(i).on_the_shape(j,temp_y))
							{
								shape_cross=true;
								break;
							}
							else if(mylist.get(i).on_the_shape(j,temp_y+50))
							{
								shape_cross=true;
								break;
							}
						}
						if(shape_cross)
						{break;}
						for(int j=temp_y;j<=temp_y+50;j++)
						{
							if(mylist.get(i).on_the_shape(temp_x,j))
							{
								shape_cross=true;
								break;
							}
							else if(mylist.get(i).on_the_shape(temp_x+30,j))
							{
								shape_cross=true;
								break;
							}
						}
						if(shape_cross)
						{break;}
					}
				}
				if(shape_cross)
				{break;}
				bed b = new bed(shapecolor, fill, 4);
				mylist.add(b);
				mylist.get(mylist.size() - 1).start_x = e.getX();
				mylist.get(mylist.size() - 1).start_y = e.getY();
				mylist.get(mylist.size() - 1).thickness = 20;
				if(movecheck == 1) {
					mylist.get(mylist.size() - 1).setName(temp);
					movecheck=0;
					temp = "";
				}
				shape = 0;
				break;
			}
			case 5: {
				boolean shape_cross=false;
				for(int i=0;i<mylist.size();i++)
				{
					if(mylist.get(i).shape<=3)
					{
						int temp_x= e.getX()-10;
						int temp_y= e.getY()-10;
						for(int j=temp_x;j<=temp_x+40;j++)
						{
							if(mylist.get(i).on_the_shape(j,temp_y))
							{
								shape_cross=true;
								break;
							}
							else if(mylist.get(i).on_the_shape(j,temp_y+30))
							{
								shape_cross=true;
								break;
							}
						}
						if(shape_cross)
						{break;}
						for(int j=temp_y;j<=temp_y+30;j++)
						{
							if(mylist.get(i).on_the_shape(temp_x,j))
							{
								shape_cross=true;
								break;
							}
							else if(mylist.get(i).on_the_shape(temp_x+40,j))
							{
								shape_cross=true;
								break;
							}
						}
						if(shape_cross)
						{break;}
					}
				}
				if(shape_cross)
				{break;}
				tv t = new tv(shapecolor, fill, 5);
				mylist.add(t);
				mylist.get(mylist.size() - 1).start_x = e.getX();
				mylist.get(mylist.size() - 1).start_y = e.getY();
				mylist.get(mylist.size() - 1).thickness = 20;
				if(movecheck == 1) {
					mylist.get(mylist.size() - 1).setName(temp);
					movecheck=0;
					temp = "";
				}
				shape = 0;
				break;
			}
			}
		}
		this.ui.repaint();
	}
	
	public void mouseDragged(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {
		if (move == false) {
		} else if (move == true) {}
		this.ui.repaint();
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseClicked(MouseEvent me) {
		if (me.getClickCount() == 2) {
			boolean on_shape = false;
			int shape_index = 0;
			for (int i = 0; i < mylist.size(); i++) {
				if (mylist.get(i).shape >= 4 && mylist.get(i).on_the_shape(me.getX(), me.getY())) {
					shape_index = i;
					on_shape = true;
				}
			}
			if (on_shape) {
				String sp = JOptionPane
						.showInputDialog("Current Name : " + mylist.get(shape_index).getName() + "\nEnter new name");
				if (!sp.equals(""))
					mylist.get(shape_index).setName(sp);
			}
		}
		if(movecheck == 1) {
			boolean on_shape = false;
			int shape_index = 0;
			for (int i = 0; i < mylist.size(); i++) {
				if (mylist.get(i).shape >= 4 && mylist.get(i).on_the_shape(me.getX(), me.getY())) {
					shape_index = mylist.get(i).shape;
					on_shape = true;
					temp = mylist.get(i).getName();
					mylist.remove(i);
				}
			}
			if (on_shape) {
				this.shape = shape_index;
			}
		}
		if(removecheck == 1) {
			boolean on_shape = false;
			int shape_index = 0;
			for (int i = 0; i < mylist.size(); i++) {
				if (mylist.get(i).shape >= 4 && mylist.get(i).on_the_shape(me.getX(), me.getY())) {
					shape_index = mylist.get(i).shape;
					on_shape = true;
					mylist.remove(i);
				}
			}
			if (on_shape) {
				removecheck = 0;
			}
		}
	}

	public void mouseExited(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {
		if (move == false) {
			if (making_wall) {
				mylist.get(mylist.size() - 1).end_x = e.getX();
				mylist.get(mylist.size() - 1).end_y = e.getY();
			}
		} else if (move == true) {}

		if (shape == 1) {
			circle c = new circle(shapecolor, fill, 0);
			templist.add(c);
			templist.get(templist.size() - 1).start_x = e.getX();
			templist.get(templist.size() - 1).start_y = e.getY();
			templist.get(templist.size() - 1).thickness = 20;
		} else if (shape == 2) {
			door d = new door(new Color(139, 69, 19), fill, 2);
			templist.add(d);
			templist.get(templist.size() - 1).start_x = e.getX();
			templist.get(templist.size() - 1).start_y = e.getY();
			int temp=20;
			for(int i=0;i<mylist.size();i++)
			{
				if(mylist.get(i).shape==1&&mylist.get(i).on_the_shape(templist.get(templist.size() - 1).start_x, templist.get(templist.size() - 1).start_y ))
				{
					if(mylist.get(i).start_y!=mylist.get(i).end_y)
					{
						temp=-20;
					}
				}
			}
			templist.get(templist.size() - 1).thickness = temp;
		} else if (shape == 3) {
			window w = new window(Color.BLUE, fill, 2);
			templist.add(w);
			templist.get(templist.size() - 1).start_x = e.getX();
			templist.get(templist.size() - 1).start_y = e.getY();
			int temp=20;
			for(int i=0;i<mylist.size();i++)
			{
				if(mylist.get(i).shape==1&&mylist.get(i).on_the_shape(templist.get(templist.size() - 1).start_x, templist.get(templist.size() - 1).start_y ))
				{
					if(mylist.get(i).start_y!=mylist.get(i).end_y)
					{
						temp=-20;
					}
				}
			}
			templist.get(templist.size() - 1).thickness = temp;
		} else if (shape == 4) {
			bed b = new bed(shapecolor, fill, 2);
			templist.add(b);
			templist.get(templist.size() - 1).start_x = e.getX();
			templist.get(templist.size() - 1).start_y = e.getY();
			templist.get(templist.size() - 1).thickness = 20;
		} else if (shape == 5) {
			tv t = new tv(shapecolor, fill, 2);
			templist.add(t);
			templist.get(templist.size() - 1).start_x = e.getX();
			templist.get(templist.size() - 1).start_y = e.getY();
			templist.get(templist.size() - 1).thickness = 20;
		}
		this.ui.repaint();
	}
}