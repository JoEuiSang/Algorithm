package algorithm.백준.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int from, to;
	double weight;

	public Edge(int from, int to, double weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return (int) (this.weight - o.weight);
	}
}

class Point {
	double x, y;

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

}

public class Boj4386별자리만들기 {
	static int N, parent[];
	static double ans;
	static ArrayList<Edge> edgeList = new ArrayList<>();
	static Point[] starArr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		parent = new int[N];
		starArr = new Point[N];

		// 각 별 좌표 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			starArr[i] = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}

		// 별끼리의 거리 구하기
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) { // i+1 유의할 것, 시간에 영향을 끼칠 수 있다.
				if (i == j)
					continue;

				double diffX = Math.pow(starArr[i].x - starArr[j].x, 2);
				double diffY = Math.pow(starArr[i].y - starArr[j].y, 2);
				double distance = Math.sqrt(diffX + diffY);

				edgeList.add(new Edge(i, j, distance));
			}
		}

		Collections.sort(edgeList);
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

	static public int find(int a) {
		if (a == parent[a])
			return a;
		return parent[a] = find(parent[a]);
	}

	static public void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a != b)
			parent[b] = a;
	}

}
