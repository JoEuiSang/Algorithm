package algorithm.백준.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj21608상어초등학교 {
	static int N, map[][], students[][], answer;
	static HashMap<Integer, Integer> numberMap = new HashMap<>(); // 학생번호:인덱스번호

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

		// 학생의 번호와, 좋아하는 사람입력
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int like = 0; like < 5; like++) {
				students[i][like] = Integer.parseInt(st.nextToken());
				if (like == 0)
					numberMap.put(students[i][0], i);
			}
		}

		// 입력 순서대로 자리에 배치
		PriorityQueue<Point> list = new PriorityQueue();

		for (int[] student : students) {
			list.clear();

			// 자리 확인하기
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (map[r][c] != 0)
						continue;

					int[] result = countNear(r, c, student);

					// 조건
					// 1. 좋아하는 사람 최대, 리스트로 추가
					// 2. 좋아하는 사람들 중 빈칸이 많은 칸을 선택
					// 3. 2가 여러개면 위쪽우선 왼쪽우선 선택
					// 인접 빈칸이 4개면 바로 끝내기

					// 조건에 의해 규칙대로 정렬된 리스트
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
		// [0] : 빈 칸, [1] : 주변의 좋아하는 친구
		int[] result = new int[2];

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isIn(nr, nc)) {
				// 빈칸 확인
				if (map[nr][nc] == 0)
					result[0]++;
				else {
					// 좋아하는 친구 확인
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
