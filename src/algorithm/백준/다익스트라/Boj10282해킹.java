package algorithm.백준.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Boj10282해킹 {
	static ArrayList<Computer>[] graph;
	static int TC;
	static int N, D, C, A, B, S;
	static int times[];

	static int count, maxTime;

	static class Computer implements Comparable<Computer> {
		int nextNum;
		int time;

		public Computer(int nextNum, int time) {
			this.nextNum = nextNum;
			this.time = time;
		}

		@Override
		public int compareTo(Computer o) {
			return this.time - o.time;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; tc++) {
			String[] input = br.readLine().split(" ");

			N = Integer.parseInt(input[0]);
			D = Integer.parseInt(input[1]);
			C = Integer.parseInt(input[2]);

			graph = new ArrayList[N + 1];
			times = new int[N + 1];
			Arrays.fill(times, Integer.MAX_VALUE);
			times[C] = 0;

			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int d = 0; d < D; d++) {
				input = br.readLine().split(" ");
				A = Integer.parseInt(input[0]);
				B = Integer.parseInt(input[1]);
				S = Integer.parseInt(input[2]);

				graph[B].add(new Computer(A, S));
			}

			dijkstra(C);

			for (int i = 1; i <= N; i++) {
				if (times[i] != Integer.MAX_VALUE) {
					count++;
					maxTime = Math.max(maxTime, times[i]);
				}
			}

			System.out.println(count + " " + maxTime);
			count = 0;
			maxTime = 0;

		}

	}

	static void dijkstra(int start) {

		PriorityQueue<Computer> pq = new PriorityQueue<>();

		pq.add(new Computer(start, 0));

		while (!pq.isEmpty()) {
			Computer now = pq.poll();

			for (Computer computer : graph[now.nextNum]) {
				if (times[computer.nextNum] > times[now.nextNum] + computer.time) {
					times[computer.nextNum] = times[now.nextNum] + computer.time;
					pq.add(new Computer(computer.nextNum, times[computer.nextNum]));
				}
			}
		}
	}
}
