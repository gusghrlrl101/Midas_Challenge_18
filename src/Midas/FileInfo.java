package Midas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class FileInfo {
	UI ui;
	int num_bed, num_tv;
	ArrayList<shape> mylist = new ArrayList<>();

	FileInfo(UI ui) {
		this.ui = ui;
	}

	public void saveFile() {
		JFileChooser fileChoose = new JFileChooser();
		int option = fileChoose.showSaveDialog(this.ui);

		if (option == JFileChooser.APPROVE_OPTION) {
			try {
				File openFile = fileChoose.getSelectedFile();
				this.ui.setTitle(openFile.getName() + " | " + this.ui.NAME);
				BufferedWriter out = new BufferedWriter(new FileWriter(openFile.getPath()));

				out.write(Integer.toString((this.ui.board.first_wall ? 1 : 0)));
				out.newLine();

				out.write(Integer.toString((this.ui.board.making_wall ? 1 : 0)));
				out.newLine();

				out.write(Integer.toString(this.ui.board.real_index));
				out.newLine();

				out.write(Integer.toString(this.ui.board.mylist.size()));
				out.newLine();

				for (int i = 0; i < this.ui.board.mylist.size(); i++) {
					out.write(Integer.toString(this.ui.board.mylist.get(i).shape));
					out.newLine();
					out.write(Integer.toString(this.ui.board.mylist.get(i).thickness));
					out.newLine();
					out.write(this.ui.board.mylist.get(i).name);
					out.newLine();
					out.write(Integer.toString(this.ui.board.mylist.get(i).start_x));
					out.newLine();
					out.write(Integer.toString(this.ui.board.mylist.get(i).start_y));
					out.newLine();
					out.write(Integer.toString(this.ui.board.mylist.get(i).end_x));
					out.newLine();
					out.write(Integer.toString(this.ui.board.mylist.get(i).end_y));
					out.newLine();
				}
				out.flush();
				out.close();

				ui.edit = false;
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public void openFile() {
		JFileChooser fileChoose = new JFileChooser();
		int option = fileChoose.showOpenDialog(this.ui);
		
		if (option == JFileChooser.APPROVE_OPTION) {
			try {
				File openFile = fileChoose.getSelectedFile();
				this.ui.setTitle(openFile.getName() + " | " + this.ui.NAME);
				BufferedReader in = new BufferedReader(new FileReader(openFile.getPath()));

				this.ui.board.mylist.clear();
				String st = null;

				st = in.readLine();
				this.ui.board.first_wall = (Integer.parseInt(st) != 0);

				st = in.readLine();
				this.ui.board.making_wall = (Integer.parseInt(st) != 0);

				st = in.readLine();
				this.ui.board.real_index = Integer.parseInt(st);

				st = in.readLine();
				int mylist_size = Integer.parseInt(st);
				shape s = null;

				for (int i = 0; i < mylist_size; i++) {
					st = in.readLine();
					int shape_num = Integer.parseInt(st);

					switch (shape_num) {
					case 1:
						s = new wall(Color.BLACK, true, shape_num);
						break;
					case 2:
						s = new door(new Color(139, 69, 19), true, shape_num);
						break;
					case 3:
						s = new window(Color.BLUE, true, shape_num);
						break;
					case 4:
						s = new bed(Color.BLACK, true, shape_num);
						break;
					case 5:
						s = new tv(Color.BLACK, true, shape_num);
						break;
					}

					st = in.readLine();
					s.thickness = Integer.parseInt(st);

					st = in.readLine();
					s.name = st;

					st = in.readLine();
					s.start_x = Integer.parseInt(st);

					st = in.readLine();
					s.start_y = Integer.parseInt(st);

					st = in.readLine();
					s.end_x = Integer.parseInt(st);

					st = in.readLine();
					s.end_y = Integer.parseInt(st);

					this.ui.board.mylist.add(s);
				}
				this.ui.repaint();
				ui.edit = false;
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public void printFile() {
		JFileChooser fileChoose = new JFileChooser();
		int option = fileChoose.showSaveDialog(this.ui);

		if (option == JFileChooser.APPROVE_OPTION) {
			try {
				File openFile = fileChoose.getSelectedFile();
				this.ui.setTitle(openFile.getName() + " | " + this.ui.NAME);

				BufferedImage image = new BufferedImage(this.ui.board.getWidth(), this.ui.board.getHeight(),
						BufferedImage.TYPE_INT_ARGB);
				Graphics2D graphics2D = image.createGraphics();

				this.ui.board.paintComponent(graphics2D);
				graphics2D.dispose();
				try {
					ImageIO.write(image, "png", new File(openFile.getPath() + ".png"));
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				ui.edit = false;
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
}