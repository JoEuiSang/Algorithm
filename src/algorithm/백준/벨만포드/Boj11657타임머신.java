package algorithm.����.��������;

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

public class Boj11657Ÿ�Ӹӽ� {
	static int N; // ���� ��
	static int M; // ���� �뼱 ��
	static Bus[] busList; // �뼱 ����Ʈ ��
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

		// ���� �Է�
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			busList[i] = new Bus(from, to, time);
		}

		// �ִܰŸ� �ʱ�ȭ
		dist = new long[N + 1];
		for (int i = 2; i <= N; i++) {
			dist[i] = INF;
		}

		// �븸 ����
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
		// 1. N - 1�� ���� ���� M�� ��� Ȯ���ϱ�
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= M; j++) {
				Bus now = busList[j];

				// 1-1. ������� ���� ���Ѵ��̸� continue
				if (dist[now.from] == INF)
					continue;
				// 1-2. �ּڰ����� �� ���� �����ϸ� ����
				dist[now.to] = Math.min(dist[now.to], dist[now.from] + now.time);
			}
		}

		// 2. ���������� ���� M�� ��� Ȯ���ؼ� ������ �߻��ϸ� ���ѷ���
		for (int j = 1; j <= M; j++) {
			Bus now = busList[j];

			if (dist[now.from] == INF)
				continue;

			// ������ �߻��Ѵٸ� ���ѷ����� ���� �� ����
			if (dist[now.from] + now.time < dist[now.to]) {
				return false;
			}
		}
		return true;
	}
}
