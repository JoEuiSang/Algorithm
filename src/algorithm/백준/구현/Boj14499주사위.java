package algorithm.백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14499주사위 {
	static int R, C, x, y, K, map[][], opers[];

	static class Dice {
		int north;
		int west, up, east;
		int south;
		int down;

		void checkFloorAndChange() {
			if (map[x][y] == 0)
				map[x][y] = this.down;
			else {
				this.down = map[x][y];
				map[x][y] = 0;
			}
		}

		void rotateEast() {
			int tmp = this.down;
			this.down = this.east;
			this.east = this.up;
			this.up = this.west;
			this.west = tmp;

			checkFloorAndChange();
		}

		void rotateWest() {
			int tmp = this.down;
			this.down = this.west;
			this.west = this.up;
			this.up = this.east;
			this.east = tmp;

			checkFloorAndChange();
		}

		void rotateSouth() {
			int tmp = this.down;
			this.down = this.south;
			this.south = this.up;
			this.up = this.north;
			this.north = tmp;

			checkFloorAndChange();
		}

		void rotateNorth() {
			int tmp = this.down;
			this.down = this.north;
			this.north = this.up;
			this.up = this.south;
			this.south = tmp;

			checkFloorAndChange();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		opers = new int[K];

		Dice dice = new Dice();

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			opers[i] = Integer.parseInt(st.nextToken());
		}

		for (int oper : opers) {
			if (oper == 1 && isIn(x, y + 1)) {
				y++;
				dice.rotateEast();
				sb.append(dice.up + "\n");
			} else if (oper == 2 && isIn(x, y - 1)) {
				y--;
				dice.rotateWest();
				sb.append(dice.up + "\n");
			} else if (oper == 3 && isIn(x - 1, y)) {
				x--;
				dice.rotateNorth();
				sb.append(dice.up + "\n");
			} else if (oper == 4 && isIn(x + 1, y)) {
				x++;
				dice.rotateSouth();
				sb.append(dice.up + "\n");
			}
		}

		System.out.println(sb.toString());

	}

	static boolean isIn(int r, int c) {
		if (r >= 0 && r < R && c >= 0 && c < C)
			return true;
		return false;
	}
}
