package algorithm.����.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

/*
	L1 : ��ǻ�ͼ� N
	L2 : ���� ������ ���� �� M  1<= M <= 100000
	L3 ~ M+2 : ��ǻ�� ���� ��� 	
*/

public class Boj1922��Ʈ��ũ���� {
	static int N, M, ans, parent[];
	static ArrayList<Edge> edgeList = new ArrayList<>();

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		// ���� ��� �Է�
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edgeList.add(new Edge(from, to, weight));
		}

		Collections.sort(edgeList);

		// �θ� �ʱ�ȭ
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
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}

	static public int find(int x) {
		if (x == parent[x])
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
