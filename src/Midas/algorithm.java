package Midas;

import java.util.ArrayList;

public class algorithm {
	public static final int H = 1000;
	public static final int W = 2000;

	public int[][] algorithm_map = new int[H][W];

	public int[][] get_map() {
		return algorithm_map;
	}

	public void set_algorithm_map(ArrayList<shape> mylist, int index) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				algorithm_map[i][j] = 0;
			}
		}
		for (int j = 0; j < index; j++) {
			int x1 = mylist.get(j).start_x;
			int y1 = mylist.get(j).start_y;
			int x2 = mylist.get(j).end_x;
			int y2 = mylist.get(j).end_y;

			int large, small;

			if (x1 == x2) {
				if (y1 < y2) {
					large = y2;
					small = y1;
				} else {
					large = y1;
					small = y2;
				}
				for (int i = small; i <= large; i++)
					algorithm_map[i][x1] = 1;
			} else if (y1 == y2) {
				if (x1 < x2) {
					large = x2;
					small = x1;
				} else {
					large = x1;
					small = x2;
				}
				for (int i = small; i <= large; i++)
					algorithm_map[y1][i] = 1;
			}
		}
	}

	public void decrease(int x, int y, int temp) {
		if (x + 1 <= W - 1) {
			if (algorithm_map[y][x + 1] == temp) {
				algorithm_map[y][x + 1]--;
				decrease(x + 1, y, temp);
			} else if (algorithm_map[y][x + 1] > 0)
				algorithm_map[y][x + 1]--;
		}
		if (x - 1 >= 0) {
			if (algorithm_map[y][x - 1] == temp) {
				algorithm_map[y][x - 1]--;
				decrease(x - 1, y, temp);
			} else if (algorithm_map[y][x - 1] > 0)
				algorithm_map[y][x - 1]--;
		}
		if (y + 1 <= H - 1) {
			if (algorithm_map[y + 1][x] == temp) {
				algorithm_map[y + 1][x]--;
				decrease(x, y + 1, temp);
			} else if (algorithm_map[y + 1][x] > 0)
				algorithm_map[y + 1][x]--;
		}
		if (y - 1 >= 0) {
			if (algorithm_map[y - 1][x] == temp) {
				algorithm_map[y - 1][x]--;
				decrease(x, y - 1, temp);
			} else if (algorithm_map[y - 1][x] > 0)
				algorithm_map[y - 1][x]--;
		}
	}

	public boolean check(ArrayList<shape> mylist, int index) {
		set_algorithm_map(mylist, index);
		int temp_x = 0, temp_y = 0;
		for (int i = index; i < mylist.size(); i++) {
			if (mylist.get(i).shape == 1) {
				int large, small;
				if (mylist.get(i).start_x == mylist.get(i).end_x) {
					if (mylist.get(i).start_y < mylist.get(i).end_y) {
						large = mylist.get(i).end_y;
						small = mylist.get(i).start_y;
					} else {
						large = mylist.get(i).start_y;
						small = mylist.get(i).end_y;
					}
					for (int j = small; j <= large; j++) {
						if (j == small) {
							if (temp_x == mylist.get(i).start_x && temp_y == j)
								algorithm_map[temp_y][temp_x]--;
							else {
								temp_x = 0;
								temp_y = 0;
							}
						}
						if (j == large) {
							temp_x = mylist.get(i).start_x;
							temp_y = j;
						}
						algorithm_map[j][mylist.get(i).start_x]++;
					}
					continue;
				}

				if (mylist.get(i).start_y == mylist.get(i).end_y) {
					if (mylist.get(i).start_x < mylist.get(i).end_x) {
						large = mylist.get(i).end_x;
						small = mylist.get(i).start_x;
					} else {
						large = mylist.get(i).start_x;
						small = mylist.get(i).end_x;
					}
					for (int j = small; j <= large; j++) {
						if (j == small) {
							if (temp_y == mylist.get(i).start_y && temp_x == j)
								algorithm_map[temp_y][temp_x]--;
							else {
								temp_x = 0;
								temp_y = 0;
							}
						}
						if (j == large) {
							temp_x = j;
							temp_y = mylist.get(i).start_y;
						}
						algorithm_map[mylist.get(i).start_y][j]++;
					}
					continue;
				}
			}
		}
		for (int i = index; i < mylist.size(); i++) {
			if (mylist.get(i).shape == 2 || mylist.get(i).shape == 3) {
				int temp = algorithm_map[mylist.get(i).start_y][mylist.get(i).start_x];
				if (temp > 0) {
					algorithm_map[mylist.get(i).start_y][mylist.get(i).start_x]--;
					decrease(mylist.get(i).start_x, mylist.get(i).start_y, temp);
				}
			}
		}
		boolean isOpen = true;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (algorithm_map[i][j] > 0) {
					isOpen = false;
					break;
				}
			}
		}
		return isOpen;
	}
}