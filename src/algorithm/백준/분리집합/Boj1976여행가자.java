package algorithm.백준.분리집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1976여행가자 {
	static int N, M, parent[];
	static int[][] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		// 그룹 초기화
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		// 연결정보 초기화
//		graph = new int[N + 1][N + 1];

		// 연결정보 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {

				// 두 도시가 연결되어있다면
				if (st.nextToken().equals("1")) {
					// 같은 그룹으로 묶어주기
					if (find(i) != find(j)) {
						union(i, j);
					}
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int group = parent[start];

		while (st.hasMoreTokens()) {
			int next = Integer.parseInt(st.nextToken());
			if (group != find(next)) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

	static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y)
			parent[y] = x;

	}

}
