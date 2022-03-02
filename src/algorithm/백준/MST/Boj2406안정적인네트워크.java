package algorithm.백준.MST;

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
public class Boj2406안정적인네트워크 {
	static int n, m, edge, answer; // n:전체 컴퓨터 수, m 연결된 지사의 컴퓨터쌍의 수, edge: 연결된 간선의 수
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

		// 집합의 대빵을 나타낼 배열
		parent = new int[n + 1];
		makeSet(); // 자기자신으로 초기화

		for (int i = 0; i < m; i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);

			if (find(x) == find(y))
				continue;

			edge++;
			union(x, y);
		}

		// 간선의 비용을 저장할 우선순위 큐
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		ArrayList<Edge> useEdge = new ArrayList<>();

		// 비용 저장하기
		for (int start = 1; start <= n; start++) {
			input = br.readLine().split(" ");
			
			//1은 그냥 건너뛰기
			if (start == 1)
				continue;
			
			for (int end = start; end < n; end++) {
				pq.add(new Edge(start, end + 1, Integer.parseInt(input[end])));
			}
		}

		// 큐에 간선이 있고, 선택한 간선이 컴퓨터수 -2 만큼일때 반복
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

	// 부모 찾기
	public static int find(int node) {
		if (parent[node] == node)
			return node;

		return parent[node] = find(parent[node]);
	}

}
