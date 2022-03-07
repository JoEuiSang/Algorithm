package algorithm.백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14500테트로미노 {
	static int N, M, map[][], ans;
	static boolean visit[][];

	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
//				System.out.print(map[r][c]);
			}
//			System.out.println();
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				per(r, c, 0, map[r][c]);
				oh(r, c);
			}
		}
		System.out.println(ans);

	}

	static void oh(int r, int c) {

		out: for (int dir = 0; dir < 4; dir++) {
			int sum = map[r][c];
			int near = 0;
			for (int d = 0; d < 4; d++) {
				if (d == dir)
					continue;
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (isIn(nr, nc))
					near += map[nr][nc];
				else
					continue out;
			}
			sum += near;
			ans = Math.max(sum, ans);
		}
	}

	static void per(int r, int c, int count, int sum) {
		// visit 처리해주기
		visit[r][c] = true;

		if (count == 3) {

			ans = Math.max(ans, sum);
			visit[r][c] = false;
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isIn(nr, nc) && !visit[nr][nc]) {
				per(nr, nc, count + 1, sum + map[nr][nc]);
			}
		}
		visit[r][c] = false;
	}

	static boolean isIn(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M)
			return true;
		return false;
	}

}
