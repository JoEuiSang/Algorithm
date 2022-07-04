package algorithm.백준.세그먼트트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2357최솟값과최댓값 {
	static int[] arr, minTree, maxTree;
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		minTree = new int[N * 4];
		maxTree = new int[N * 4];

		arr = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		minInit(1, N, 1);
		maxInit(1, N, 1);

		for (int i = 1; i <= M; i++) {
			line = br.readLine().split(" ");
			int min = getMin(1, N, 1, Integer.parseInt(line[0]), Integer.parseInt(line[1]));
			int max = getMax(1, N, 1, Integer.parseInt(line[0]), Integer.parseInt(line[1]));

			sb.append(min + " " + max + "\n");
		}

		System.out.println(sb.toString());
	}

	static int getMin(int nowStart, int nowEnd, int nowIdx, int targetStart, int targetEnd) {
		if (targetEnd < nowStart || nowEnd < targetStart)
			return Integer.MAX_VALUE;

		if (targetStart <= nowStart && nowEnd <= targetEnd)
			return minTree[nowIdx];

		int mid = (nowStart + nowEnd) / 2;
		return Math.min(getMin(nowStart, mid, nowIdx * 2, targetStart, targetEnd),
				getMin(mid + 1, nowEnd, nowIdx * 2 + 1, targetStart, targetEnd));
	}

	static int getMax(int nowStart, int nowEnd, int nowIdx, int targetStart, int targetEnd) {
		if (targetEnd < nowStart || nowEnd < targetStart)
			return Integer.MIN_VALUE;

		if (targetStart <= nowStart && nowEnd <= targetEnd)
			return maxTree[nowIdx];

		int mid = (nowStart + nowEnd) / 2;
		return Math.max(getMax(nowStart, mid, nowIdx * 2, targetStart, targetEnd),
				getMax(mid + 1, nowEnd, nowIdx * 2 + 1, targetStart, targetEnd));
	}

	static int minInit(int start, int end, int index) {
		if (start == end)
			return minTree[index] = arr[start];

		int mid = (start + end) / 2;
		return minTree[index] = Math.min(minInit(start, mid, index * 2), minInit(mid + 1, end, index * 2 + 1));
	}

	static int maxInit(int start, int end, int index) {
		if (start == end)
			return maxTree[index] = arr[start];

		int mid = (start + end) / 2;
		return maxTree[index] = Math.max(maxInit(start, mid, index * 2), maxInit(mid + 1, end, index * 2 + 1));
	}

}
