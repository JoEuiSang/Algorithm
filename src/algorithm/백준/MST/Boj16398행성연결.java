package algorithm.백준.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj16398행성연결 {
	static int N, parent[], flow[][];
	static long ans;
	static ArrayList<Edge> edgeList = new ArrayList<>();

	static class Edge implements Comparable<Edge> {
		int from, to;
		long weight;

		public Edge(int from, int to, long weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return (int) (this.weight - o.weight);
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		flow = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				flow[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				edgeList.add(new Edge(i, j, flow[i][j]));
			}
		}

		Collections.sort(edgeList);

		parent = new int[N + 1];
		set();

		for (Edge e : edgeList) {
			if (find(e.from) != find(e.to)) {
				ans += e.weight;
				union(e.from, e.to);
			}
		}
		System.out.println(ans);

	}

	static public void set() {
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

	static public int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	static public void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			parent[y] = x;
		}
	}

}
