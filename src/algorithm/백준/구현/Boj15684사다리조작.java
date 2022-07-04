package algorithm.백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15684사다리조작 {
	static int N, M, H, answer = 4;
	static int vertical[]; // 각 줄에 연결되 가로선
	static Point[][] map; // 높이, 세로번호

	static class Point {
		int height;
		boolean left, right;

		public Point(int height, boolean left, boolean right) {
			this.height = height;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return "" + right;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new Point[H + 1][N + 1];
		vertical = new int[H + 1];
		for (int r = 1; r <= H; r++) {
			// 세로줄
			for (int c = 1; c <= N; c++) {
				map[r][c] = new Point(r, false, false);
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int height = Integer.parseInt(st.nextToken());
			int verticalNum = Integer.parseInt(st.nextToken());

			map[height][verticalNum].right = true;
			map[height][verticalNum + 1].left = true;
			vertical[verticalNum]++;
			vertical[verticalNum + 1]++;
		}

		if (check()) {
			System.out.println(0);
			return;
		}

		for (int addLine = 1; addLine <= 3; addLine++) {
			// 가로줄
			for (int r = 1; r <= H; r++) {
				// 세로줄
				for (int c = 1; c < N; c++) {
					if (!map[r][c].right) {
						connect(r, c, 0, addLine);
					}
				}
			}
		}

		if (answer > 3)
			System.out.println(-1);
		else
			System.out.println(answer);

	}

	static boolean check() {
		// 각 라인 맨 위에서부터 출발하기
		for (int start = 1; start <= N; start++) {
			int r = 1;
			int c = start;

			// 맨 아래까지 가기
			while (r <= H) {
				// 왼쪽이 연결되어 있으면
				if (map[r][c].left) {
					if (c == 1) {
						r++;
						continue;
					} else {
						c--;
						r++;
						continue;
					}
				} else if (map[r][c].right) {
					if (c == N) {
						r++;
						continue;
					} else {
						c++;
						r++;
						continue;
					}
				} else {
					r++;
				}
			}
			if (c != start)
				return false;
		}
		return true;
	}

	static void connect(int startR, int startC, int count, int max) {
		if (count == max) {
			// 각 세로선에 연결된 가로선이 홀 수이면 i->i로 갈 수 없다
			for (int num : vertical) {
				if (num % 2 == 1)
					return;
			}

			if (!check())
				return;

			answer = Math.min(answer, count);
			return;
		}

		// 가로줄

		for (int r = startR; r <= H; r++) {
			// 세로줄
			for (int c = 1; c < N; c++) {
				if (!map[r][c].right && !map[r][c].right) {
					map[r][c].right = true;
					map[r][c + 1].left = true;
					vertical[c]++;
					vertical[c + 1]++;

					connect(r, c, count + 1, max);

					map[r][c].right = false;
					map[r][c + 1].left = false;
					vertical[c]--;
					vertical[c + 1]--;
				}
			}
		}
	}
}
