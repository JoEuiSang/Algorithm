package algorithm.백준.벨만포드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Bus {
	int from, to, time;

	public Bus(int from, int to, int time) {
		super();
		this.from = from;
		this.to = to;
		this.time = time;
	}
}

public class Boj11657타임머신 {
	static int N; // 도시 수
	static int M; // 버스 노선 수
	static Bus[] busList; // 노선 리스트 수
	static long[] dist;
	static int answer;

	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		busList = new Bus[M + 1];

		// 간선 입력
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			busList[i] = new Bus(from, to, time);
		}

		// 최단거리 초기화
		dist = new long[N + 1];
		for (int i = 2; i <= N; i++) {
			dist[i] = INF;
		}

		// 밸만 포드
		if (!bellmanFord()) {
			System.out.println(-1);
			return;
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 2; i <= N; i++) {
				if (dist[i] == INF)
					sb.append("-1\n");
				else
					sb.append(dist[i] + "\n");
			}
			System.out.println(sb.toString());
		}

	}

	static boolean bellmanFord() {
		// 1. N - 1번 동안 간선 M을 모두 확인하기
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= M; j++) {
				Bus now = busList[j];

				// 1-1. 출발지가 현재 무한대이면 continue
				if (dist[now.from] == INF)
					continue;
				// 1-2. 최솟값으로 값 갱신 가능하면 갱신
				dist[now.to] = Math.min(dist[now.to], dist[now.from] + now.time);
			}
		}

		// 2. 마지막으로 간선 M을 모두 확인해서 갱신이 발생하면 무한루프
		for (int j = 1; j <= M; j++) {
			Bus now = busList[j];

			if (dist[now.from] == INF)
				continue;

			// 갱신이 발생한다면 무한루프에 빠질 수 있음
			if (dist[now.from] + now.time < dist[now.to]) {
				return false;
			}
		}
		return true;
	}
}
