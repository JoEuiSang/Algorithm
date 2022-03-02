package algorithm.πÈ¡ÿ.BFSDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Boj2589∫∏π∞º∂ {

	static int R, C, answer = 0;
	static char map[][];
	static boolean visited[][];

	static class Point {
		int r, c, dis;

		public Point(int r, int c, int dis) {
			super();
			this.r = r;
			this.c = c;
			this.dis = dis;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input[] = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);

		map = new char[R][C];
		visited = new boolean[R][C];

		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'L')
					bfs(r, c);
			}
		}
		
		System.out.println(answer);

	}

	// ªÛ«œ¡¬øÏ
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	public static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<Point>();
		initVisit();

		q.add(new Point(r, c, 0));
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Point now = q.poll();
			if (now.dis > answer)
				answer = now.dis;

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && map[nr][nc] == 'L') {
					visited[nr][nc] = true;
					q.add(new Point(nr, nc, now.dis + 1));
				}
			}
		}
	}

	public static void initVisit() {
		for (boolean[] r : visited) {
			Arrays.fill(r, false);
		}
	}

}
