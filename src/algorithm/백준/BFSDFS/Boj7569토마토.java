package algorithm.백준.BFSDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Boj7569토마토 {
	static int R, C, N, M, H, map[][][], answer;
	static int yetTomato;
	static Queue<Point> redTomato = new LinkedList<>();

	static class Point {
		int h, r, c, day;

		public Point(int h, int r, int c, int day) {
			super();
			this.h = h;
			this.r = r;
			this.c = c;
			this.day = day;
		}
	}

	static int[] dh = { 0, 0, 0, 0, -1, 1 };
	static int[] dr = { -1, 1, 0, 0, 0, 0, };
	static int[] dc = { 0, 0, -1, 1, 0, 0, };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		C = M = Integer.parseInt(line[0]);
		R = N = Integer.parseInt(line[1]);
		H = Integer.parseInt(line[2]);

		map = new int[H][R][C];

		for (int h = 0; h < H; h++) {
			for (int r = 0; r < R; r++) {
				int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for (int c = 0; c < C; c++) {
					map[h][r][c] = arr[c];

					if (arr[c] == 0)
						yetTomato++;
					else if (arr[c] == 1) {
						redTomato.add(new Point(h, r, c, 0));
					}
				}
			}
		}

		if (yetTomato == 0) {
			System.out.println(0);
			return;
		}

		while (!redTomato.isEmpty()) {
			Point now = redTomato.poll();
			answer = now.day;
			for (int i = 0; i < 6; i++) {
				int nh = now.h + dh[i];
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isIn(nh, nr, nc) && map[nh][nr][nc] == 0) {
					map[nh][nr][nc] = 1;
					yetTomato--;
					redTomato.add(new Point(nh, nr, nc, now.day + 1));
				}
			}
		}

		if (yetTomato == 0)
			System.out.println(answer);
		else
			System.out.println(-1);

	}

	static boolean isIn(int h, int r, int c) {
		if (h >= 0 && h < H && r >= 0 && r < R && c >= 0 && c < C)
			return true;
		return false;
	}

}
