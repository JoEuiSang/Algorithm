package algorithm.백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14503로봇청소기 {
	static int N, M, ans, map[][]; // 왼쪽상단부터 0,0

	// 북 동 남 서
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int d;
	static int rotateCnt = 0;

	static final int FRONT = 0, LEFT = 1, SOUTH = 2, WEST = 3;

	static Robot robot;

	static class Robot {
		int r, c, d;

		public Robot(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);

		// 0 빈칸, 1 벽, 2 청소완료
		map = new int[N][M];

		String[] robo = br.readLine().split(" ");

		robot = new Robot(Integer.parseInt(robo[0]), Integer.parseInt(robo[1]), Integer.parseInt(robo[2]));

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
//				sb.append(map[r][c]);
			}
//			sb.append("\n");
		}

		while (true) {
			// 1. 청소작업.
			clean();

			// 2.a 왼쪽방향이 청소 안했는지 확인
			if (checkLeft() == 0) {
				rotateLeft();
				goStraight();
				continue;
			} else {
				rotateLeft();
			}

			if (rotateCnt == 4) {
				if (checkBack() == 1)
					break;
				else
					goBack();
			}

		}
		System.out.println(ans);

	}

	// 현재 위치를 청소한다 2는 청소상태
	static void clean() {
		if (map[robot.r][robot.c] == 0) {
			map[robot.r][robot.c] = 2;
			ans++;
		}
	}

	// 네방향 전부 청소했거나 벽일때
	static boolean checkAll() {
		if (checkLeft() == 0 && checkBack() == 0 && checkFront() == 0 && checkFront() == 0)
			return true;
		return false;
	}

	static int checkDir(int dir) {
		int left = (robot.d + 3) % 4;
		int nr = robot.r + dr[left];
		int nc = robot.c + dc[left];

		if (map[nr][nc] == 0)
			return 0;
		if (map[nr][nc] == 1)
			return 1;
		return 2;
	}

	static int checkLeft() {
		int left = (robot.d + 3) % 4;
		int nr = robot.r + dr[left];
		int nc = robot.c + dc[left];

		if (map[nr][nc] == 0)
			return 0;
		if (map[nr][nc] == 1)
			return 1;
		return 2;

	}

	static int checkRight() {
		int right = (robot.d + 1) % 4;
		int nr = robot.r + dr[right];
		int nc = robot.c + dc[right];

		if (map[nr][nc] == 0)
			return 0;
		if (map[nr][nc] == 1)
			return 1;
		return 2;
	}

	static int checkFront() {
		int nr = robot.r + dr[robot.d];
		int nc = robot.c + dc[robot.d];

		if (map[nr][nc] == 0)
			return 0;
		if (map[nr][nc] == 1)
			return 1;
		return 2;

	}

	static int checkBack() {
		int nr = robot.r - dr[robot.d];
		int nc = robot.c - dc[robot.d];

		if (map[nr][nc] == 0)
			return 0;
		if (map[nr][nc] == 1)
			return 1;
		return 2;
	}

	static void rotateLeft() {
		robot.d = (robot.d + 3) % 4;
		rotateCnt++;
	}

	static void goStraight() {
		robot.r = robot.r + dr[robot.d];
		robot.c = robot.c + dc[robot.d];
		rotateCnt = 0;
	}

	static void goBack() {
		robot.r = robot.r - dr[robot.d];
		robot.c = robot.c - dc[robot.d];
		rotateCnt = 0;
	}

}