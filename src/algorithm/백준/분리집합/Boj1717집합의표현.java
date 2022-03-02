package algorithm.백준.분리집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1717집합의표현 {
	static int node, oper, parent[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] input = br.readLine().split(" ");

		node = Integer.parseInt(input[0]);
		oper = Integer.parseInt(input[1]);

		parent = new int[node + 1];
		makeSet();

		for (int i = 0; i < oper; i++) {
			input = br.readLine().split(" ");

			int type = Integer.parseInt(input[0]);
			int a = Integer.parseInt(input[1]);
			int b = Integer.parseInt(input[2]);
			if (type == 0) {
				union(a, b);
			} else if (type == 1) {
				if (find(a) == find(b))
					sb.append("YES\n");
				else
					sb.append("NO\n");
			}
		}
		System.out.println(sb.toString());

	}

	public static void makeSet() {
		for (int i = 0; i <= node; i++) {
			parent[i] = i;
		}
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	public static int find(int a) {
		if (parent[a] == a)
			return a;

		return parent[a] = find(parent[a]);
	}
}
