package algorithm.����.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

/*
5 2
3 2
3 4
0 100 50 100 100
100 0 100 100 100
50 100 0 20 100
100 100 20 0 80
100 100 100 80 0
*/
public class Boj2406�������γ�Ʈ��ũ {
	static int n, m, edge, answer; // n:��ü ��ǻ�� ��, m ����� ������ ��ǻ�ͽ��� ��, edge: ����� ������ ��
	static int[] parent;

	public static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int cost;

		public Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);

		// ������ �뻧�� ��Ÿ�� �迭
		parent = new int[n + 1];
		makeSet(); // �ڱ��ڽ����� �ʱ�ȭ

		for (int i = 0; i < m; i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);

			if (find(x) == find(y))
				continue;

			edge++;
			union(x, y);
		}

		// ������ ����� ������ �켱���� ť
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		ArrayList<Edge> useEdge = new ArrayList<>();

		// ��� �����ϱ�
		for (int start = 1; start <= n; start++) {
			input = br.readLine().split(" ");
			
			//1�� �׳� �ǳʶٱ�
			if (start == 1)
				continue;
			
			for (int end = start; end < n; end++) {
				pq.add(new Edge(start, end + 1, Integer.parseInt(input[end])));
			}
		}

		// ť�� ������ �ְ�, ������ ������ ��ǻ�ͼ� -2 ��ŭ�϶� �ݺ�
		while (!pq.isEmpty() && edge < n - 2) {
			Edge now = pq.poll();

			int x = now.start;
			int y = now.end;

			if (find(x) == find(y))
				continue;

			union(x, y);
			answer += now.cost;
			useEdge.add(now);
			edge++;
		}

		System.out.println(answer + " " + useEdge.size());
		for (int i = 0; i < useEdge.size(); i++) {
			System.out.println(useEdge.get(i).start + " " + useEdge.get(i).end);
		}

	}

	public static void makeSet() {
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x < y) {
			parent[y] = x;
		} else {
			parent[x] = y;
		}
	}

	// �θ� ã��
	public static int find(int node) {
		if (parent[node] == node)
			return node;

		return parent[node] = find(parent[node]);
	}

}
