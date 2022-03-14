package algorithm.����.����;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj21608����ʵ��б� {
	static int N, map[][], students[][], answer;
	static HashMap<Integer, Integer> numberMap = new HashMap<>(); // �л���ȣ:�ε�����ȣ

	static class Point implements Comparable<Point> {
		int r, c, likeCnt, emptyCnt;

		public Point(int r, int c, int likeCnt, int emptyCnt) {
			this.r = r;
			this.c = c;
			this.likeCnt = likeCnt;
			this.emptyCnt = emptyCnt;
		}

		@Override
		public int compareTo(Point o) {
			if (this.likeCnt != o.likeCnt)
				return o.likeCnt - this.likeCnt;
			if (this.emptyCnt != o.emptyCnt)
				return o.emptyCnt - this.emptyCnt;
			if (this.r != o.r)
				return this.r - o.r;
			return this.c - o.c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];

		students = new int[N * N][5];

		// �л��� ��ȣ��, �����ϴ� ����Է�
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int like = 0; like < 5; like++) {
				students[i][like] = Integer.parseInt(st.nextToken());
				if (like == 0)
					numberMap.put(students[i][0], i);
			}
		}

		// �Է� ������� �ڸ��� ��ġ
		PriorityQueue<Point> list = new PriorityQueue();

		for (int[] student : students) {
			list.clear();

			// �ڸ� Ȯ���ϱ�
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (map[r][c] != 0)
						continue;

					int[] result = countNear(r, c, student);

					// ����
					// 1. �����ϴ� ��� �ִ�, ����Ʈ�� �߰�
					// 2. �����ϴ� ����� �� ��ĭ�� ���� ĭ�� ����
					// 3. 2�� �������� ���ʿ켱 ���ʿ켱 ����
					// ���� ��ĭ�� 4���� �ٷ� ������

					// ���ǿ� ���� ��Ģ��� ���ĵ� ����Ʈ
					list.add(new Point(r, c, result[1], result[0]));
				}
			}
			Point now = list.poll();
			map[now.r][now.c] = student[0];
		}

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				int studentNum = map[r][c];
				int[] student = students[numberMap.get(studentNum)];
				int[] result = countNear(r, c, student);
				if (result[1] == 0)
					continue;
				else if (result[1] == 1)
					answer += 1;
				else if (result[1] == 2)
					answer += 10;
				else if (result[1] == 3)
					answer += 100;
				else if (result[1] == 4)
					answer += 1000;
			}
		}
		System.out.println(answer);

	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static public int[] countNear(int r, int c, int[] student) {
		// [0] : �� ĭ, [1] : �ֺ��� �����ϴ� ģ��
		int[] result = new int[2];

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isIn(nr, nc)) {
				// ��ĭ Ȯ��
				if (map[nr][nc] == 0)
					result[0]++;
				else {
					// �����ϴ� ģ�� Ȯ��
					for (int like = 1; like <= 4; like++) {
						if (map[nr][nc] == student[like])
							result[1]++;
					}
				}
			}

		}
		return result;
	}

	static boolean isIn(int r, int c) {
		if (r >= 1 && r <= N && c >= 1 && c <= N)
			return true;
		return false;
	}

}
