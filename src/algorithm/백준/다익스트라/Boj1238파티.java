package algorithm.백준.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Boj1238파티 {
	static int N, M, X, graph[][];
	static int times[];
	static int answerTime[]; // 왕복 타임
	static int answer; // 가장 오래걸린 시간

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		X = Integer.parseInt(input[2]);

		graph = new int[N + 1][N + 1];
		times = new int[N + 1];
		answerTime = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(graph[i], 99999999);
		}

		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");

			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			int time = Integer.parseInt(input[2]);
			graph[from][to] = time;
		}

		// X에서 타 도시로 가는거 먼저 구하기
		Arrays.fill(times, 99999999);
		dijk(X);
		for (int city = 1; city <= N; city++)
			answerTime[city] = times[city];

		// 각 도시에서 타 도시로 가는거 더해주기
		for (int start = 1; start <= N; start++) {
			Arrays.fill(times, 99999999);
			dijk(start);
			answerTime[start] += times[X];
		}

		for (int time : answerTime) {
			answer = Math.max(time, answer);
		}
		System.out.println(answer);

	}

	static class City implements Comparable<City> {
		int num, time;

		public City(int num, int time) {
			this.num = num;
			this.time = time;
		}

		@Override
		public int compareTo(City o) {
			return this.time - o.time;
		}
	}

	static void dijk(int start) {
		PriorityQueue<City> pq = new PriorityQueue<>();
		boolean[] visit = new boolean[N + 1];

		pq.add(new City(start, 0));
		times[start] = 0;
		visit[start] = true;

		while (!pq.isEmpty()) {
			City now = pq.poll();

			int nextCity = 0;
			int nextTime = Integer.MAX_VALUE;
			for (int city = 1; city <= N; city++) {
				if (graph[now.num][city] == 0)
					continue;
				if (now.num == city)
					continue;

				// 최단거리 구하기
				if (times[city] > times[now.num] + graph[now.num][city]) {
					times[city] = times[now.num] + graph[now.num][city];

					if (times[city] < nextTime) {
						nextTime = times[city];
						nextCity = city;
					}
				}

			}

			if (nextCity != 0) {
				pq.add(new City(nextCity, nextTime));
				visit[now.num] = true;

			}
		}
	}

}
