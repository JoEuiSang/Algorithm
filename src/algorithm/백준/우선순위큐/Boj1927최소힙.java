package algorithm.백준.우선순위큐;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Boj1927최소힙 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num == 0) {
				if (pq.isEmpty()) {
					sb.append("0\n");
				} else {
					sb.append(pq.poll() + "\n");
				}
			} else {
				pq.add(num);
			}
		}
		System.out.println(sb.toString());
	}

}
