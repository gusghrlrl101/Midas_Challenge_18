package Midas;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class UI extends JFrame implements ActionListener, MouseListener {
	private final JMenuBar menuBar;
	private final JMenu menuFile;
	private final JToolBar mainToolbar;
	private final JMenuItem newFile, openFile, saveFile, printFile;
	JButton wallButton, doorButton, windowButton, bedButton, tvButton, checkButton, moveButton, removeButton;

	Board board = new Board(this);

	FileInfo fileinfo = new FileInfo(this);
	public final static String NAME = "Self Interior Program";
	boolean edit = false;
	String[] furniture_list = { "Bed", "TV" };
	int i_bed;
	boolean check = false;

	ImageIcon icon1 = new ImageIcon("image/new.png");
	Image image1 = icon1.getImage();
	Image newimg1 = image1.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private final ImageIcon newIcon = new ImageIcon(newimg1);
	ImageIcon icon2 = new ImageIcon("image/open.png");
	Image image2 = icon2.getImage();
	Image newimg2 = image2.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private final ImageIcon openIcon = new ImageIcon(newimg2);
	ImageIcon icon3 = new ImageIcon("image/save.png");
	Image image3 = icon3.getImage();
	Image newimg3 = image3.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private final ImageIcon saveIcon = new ImageIcon(newimg3);
	ImageIcon icon4 = new ImageIcon("image/wall_icon.png");
	Image image4 = icon4.getImage();
	Image newimg4 = image4.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private final ImageIcon wallIcon = new ImageIcon(newimg4);
	ImageIcon icon5 = new ImageIcon("image/door_icon.png");
	Image image5 = icon5.getImage();
	Image newimg5 = image5.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private final ImageIcon doorIcon = new ImageIcon(newimg5);
	ImageIcon icon6 = new ImageIcon("image/window_icon.png");
	Image image6 = icon6.getImage();
	Image newimg6 = image6.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private final ImageIcon windowIcon = new ImageIcon(newimg6);
	ImageIcon icon7 = new ImageIcon("image/bed_icon.png");
	Image image7 = icon7.getImage();
	Image newimg7 = image7.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private final ImageIcon bedIcon = new ImageIcon(newimg7);
	ImageIcon icon8 = new ImageIcon("image/tv_icon.png");
	Image image8 = icon8.getImage();
	Image newimg8 = image8.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private final ImageIcon tvIcon = new ImageIcon(newimg8);
	ImageIcon icon9 = new ImageIcon("image/print_icon.png");
	Image image9 = icon9.getImage();
	Image newimg9 = image9.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private final ImageIcon printIcon = new ImageIcon(newimg9);
	ImageIcon icon10 = new ImageIcon("image/check_icon.png");
	Image image10 = icon10.getImage();
	Image newimg10 = image10.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private final ImageIcon checkIcon = new ImageIcon(newimg10);
	ImageIcon icon11 = new ImageIcon("image/move_icon.png");
	Image image11 = icon11.getImage();
	Image newimg11 = image11.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private final ImageIcon moveIcon = new ImageIcon(newimg11);
	ImageIcon icon12 = new ImageIcon("image/remove_icon.png");
	Image image12 = icon12.getImage();
	Image newimg12 = image12.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private final ImageIcon removeIcon = new ImageIcon(newimg12);

	UI() {
		this.addMouseListener(board);
		this.addMouseMotionListener(board);
		this.getContentPane().add(board);
		this.board.addMouseListener(board);
		this.board.addMouseMotionListener(board);
		// this.add(board);
		this.fileinfo = new FileInfo(this);
		setSize(1000, 700);
		setTitle("new file" + " | " + this.NAME);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.addMouseListener(this);

		menuBar = new JMenuBar();
		doorButton = new JButton();
		windowButton = new JButton();
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		this.setJMenuBar(menuBar);
		menuBar.add(menuFile);

		newFile = new JMenuItem("New", newIcon);
		openFile = new JMenuItem("Open", openIcon);
		saveFile = new JMenuItem("Save", saveIcon);
		printFile = new JMenuItem("Print", printIcon);

		newFile.addActionListener(this);
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		menuFile.add(newFile);

		openFile.addActionListener(this);
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		menuFile.add(openFile);

		saveFile.addActionListener(this);
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuFile.add(saveFile);

		printFile.addActionListener(this);
		printFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		menuFile.add(printFile);

		mainToolbar = new JToolBar();
		this.add(mainToolbar, BorderLayout.NORTH);

		wallButton = new JButton(wallIcon);
		wallButton.setToolTipText("Wall");
		wallButton.addActionListener(this);
		mainToolbar.add(wallButton);
		mainToolbar.addSeparator();

		doorButton = new JButton(doorIcon);
		doorButton.setToolTipText("Door");
		doorButton.addActionListener(this);
		mainToolbar.add(doorButton);
		mainToolbar.addSeparator();

		windowButton = new JButton(windowIcon);
		windowButton.setToolTipText("Window");
		windowButton.addActionListener(this);
		mainToolbar.add(windowButton);
		mainToolbar.addSeparator();

		bedButton = new JButton(bedIcon);
		bedButton.setToolTipText("Bed");
		bedButton.addActionListener(this);
		mainToolbar.add(bedButton);
		mainToolbar.addSeparator();

		tvButton = new JButton(tvIcon);
		tvButton.setToolTipText("TV");
		tvButton.addActionListener(this);
		mainToolbar.add(tvButton);
		mainToolbar.addSeparator();

		checkButton = new JButton(checkIcon);
		checkButton.setToolTipText("Check");
		checkButton.addActionListener(this);
		mainToolbar.add(checkButton);
		mainToolbar.addSeparator();

		moveButton = new JButton(moveIcon);
		moveButton.setToolTipText("TV");
		moveButton.addActionListener(this);
		mainToolbar.add(moveButton);
		mainToolbar.addSeparator();

		removeButton = new JButton(removeIcon);
		removeButton.setToolTipText("TV");
		removeButton.addActionListener(this);
		mainToolbar.add(removeButton);
		mainToolbar.addSeparator();
	}

	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			if (edit) {
				Object[] options = { "Save and exit", "No Save and exit", "Return" };
				int n = JOptionPane.showOptionDialog(this, "Do you want to save the file ?", "Question",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if (n == 0) {
					fileinfo.saveFile();
					this.dispose();
				} else if (n == 1) {
					this.dispose();
				}
			} else {
				System.exit(99);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newFile) {
			if (edit) {
				Object[] options = { "Save", "No Save", "Return" };
				int n = JOptionPane.showOptionDialog(this, "Do you want to save the file at first ?", "Question",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
				if (n == 0) {
					fileinfo.saveFile();
					edit = false;
				} else if (n == 1) {
					edit = false;
					this.board.mylist.clear();
					this.board.repaint();
				}
			} else {
				this.board.mylist.clear();
				this.board.repaint();
			}
		} else if (e.getSource() == openFile) {
			fileinfo.openFile();
		} else if (e.getSource() == saveFile) {
			fileinfo.saveFile();
		} else if (e.getSource() == printFile) {
			fileinfo.printFile();
		} else if (e.getSource() == wallButton) {
			this.board.shape = 1;
		} else if (e.getSource() == doorButton) {
			this.board.shape = 2;
		} else if (e.getSource() == windowButton) {
			this.board.shape = 3;
		} else if (e.getSource() == bedButton) {
			if (this.board.checc)
				this.board.shape = 4;
			else
				JOptionPane.showMessageDialog(null, "Add Door or Window");
		} else if (e.getSource() == moveButton) {
			this.board.movecheck = 1;
		} else if (e.getSource() == removeButton) {
			this.board.removecheck = 1;
		} else if (e.getSource() == tvButton) {
			if (this.board.checc)
				this.board.shape = 5;
			else
				JOptionPane.showMessageDialog(null, "Add Door or Window");
		} else if (e.getSource() == checkButton) {
			this.board.al.check(this.board.mylist, this.board.real_index);
			if (this.board.checc)
				JOptionPane.showMessageDialog(null, "Open!");
			else
				JOptionPane.showMessageDialog(null, "Close!");
		}

	}

	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
