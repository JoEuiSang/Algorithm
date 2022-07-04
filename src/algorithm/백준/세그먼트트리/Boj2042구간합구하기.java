package algorithm.백준.세그먼트트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2042구간합구하기 {
	static int N, M, K;
	static long arr[], interval_sum[];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		K = Integer.parseInt(line[2]);

		arr = new long[N + 1];
		interval_sum = new long[N * 4];

		for (int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		init(1, N, 1);

		for (int i = 0; i < M + K; i++) {
			line = br.readLine().split(" ");
			oper(line);
		}

		System.out.println(sb.toString());

	}

	static long init(int start, int end, int index) {
		if (start == end) {
			return interval_sum[index] = arr[start];
		}

		int mid = (start + end) / 2;
		return interval_sum[index] = init(start, mid, index * 2) + init(mid + 1, end, index * 2 + 1);
	}

	static void oper(String[] line) {
		int a = Integer.parseInt(line[0]);
		int b = Integer.parseInt(line[1]);
		long c = Long.parseLong(line[2]);

		if (a == 1) {
			long diff = c - arr[b];
			arr[b] = c;
			update(1, N, 1, b, diff);
		} else {
			sb.append((getSum(1, N, 1, b, (int) c)) + "\n");
		}
	}

	static void update(int start, int end, int index, int targetIdx, long diff) {
		if (targetIdx < start || targetIdx > end) {
			return;
		}

		interval_sum[index] += diff;

		if (start == end) {
			return;
		}

		int mid = (start + end) / 2;
		update(start, mid, index * 2, targetIdx, diff);
		update(mid + 1, end, index * 2 + 1, targetIdx, diff);
	}

	// 현재시작 인덱스, 현재끝 인덱스, 현재 구간합을 나타내는 인덱스, 현재 구간의 합을 가지고 있는 인덱스,
	// 구간합 구할 범위 시작, 구간합 구할 범위 끝
	static long getSum(int start, int end, int index, int left, int right) {
		if (left > end || right < start)
			return 0;

		if (left <= start && end <= right)
			return interval_sum[index];

		int mid = (start + end) / 2;
		return getSum(start, mid, index * 2, left, right) + getSum(mid + 1, end, index * 2 + 1, left, right);

	}

}
