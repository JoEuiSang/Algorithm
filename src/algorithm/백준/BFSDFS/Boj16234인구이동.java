package algorithm.백준.BFSDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16234인구이동 {
	static int N, L, R, popular[][], world[][], answer;
	static boolean visited[][], flag = true;
	static ArrayList<ArrayList<Point>> worldList = new ArrayList<>(); // 연합을 표현할 이중 리스트 , 연합<좌표>

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		popular = new int[N][N];
		world = new int[N][N];
		visited = new boolean[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				popular[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// flag는 연합된 나라가 있는지 확인하는 변수
		while (true) {
			flag = false;

			initVisit();
			worldList.clear();

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c]) {
						worldList.add(new ArrayList<Point>());
						unionWorld(r, c, worldList.get(worldList.size() - 1));
					}
				}
			}
			if (flag) {
				movePopular();
				answer++;
			} else
				break;
		}

		System.out.println(answer);
	}

	static void movePopular() {
		for (ArrayList<Point> pointList : worldList) {
			int size = pointList.size();
			int sum = 0;

			for (Point p : pointList) {
				sum += popular[p.r][p.c];
			}

			int value = sum / size;

			for (Point p : pointList) {
				popular[p.r][p.c] = value;
			}
		}
	}

	static boolean unionWorld(int r, int c, ArrayList<Point> pointList) {
		Queue<Point> q = new LinkedList<>();

		q.add(new Point(r, c));
		pointList.add(new Point(r, c));
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];

				if (isIn(nr, nc) && !visited[nr][nc] && checkSub(now.r, now.c, nr, nc)) {
					flag = true;
					visited[nr][nc] = true;
					q.add(new Point(nr, nc));
					pointList.add(new Point(nr, nc));
				}
			}
		}

		return true;
	}

	static void initVisit() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				visited[r][c] = false;
			}
		}
	}

	static void initWorld() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				world[r][c] = 0;
			}
		}
	}

	static boolean checkSub(int r, int c, int nr, int nc) {
		int sub = Math.abs(popular[r][c] - popular[nr][nc]);
		if (L <= sub && sub <= R)
			return true;
		return false;
	}

	static boolean isIn(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N)
			return true;
		return false;
	}

}
