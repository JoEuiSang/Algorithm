package algorithm.����.����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15684��ٸ����� {
	static int N, M, H, answer = 4;
	static int vertical[]; // �� �ٿ� ����� ���μ�
	static Point[][] map; // ����, ���ι�ȣ

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
			// ������
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
			// ������
			for (int r = 1; r <= H; r++) {
				// ������
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
		// �� ���� �� ���������� ����ϱ�
		for (int start = 1; start <= N; start++) {
			int r = 1;
			int c = start;

			// �� �Ʒ����� ����
			while (r <= H) {
				// ������ ����Ǿ� ������
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
			// �� ���μ��� ����� ���μ��� Ȧ ���̸� i->i�� �� �� ����
			for (int num : vertical) {
				if (num % 2 == 1)
					return;
			}

			if (!check())
				return;

			answer = Math.min(answer, count);
			return;
		}

		// ������

		for (int r = startR; r <= H; r++) {
			// ������
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
