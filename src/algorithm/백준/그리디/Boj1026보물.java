package algorithm.백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj1026보물 {
	static int N, A[], B[], useA[];
	static boolean use[];
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		B = new int[N];
		useA = new int[N];
		use = new boolean[N];

		String[] line = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(line[i]);
		}

		line = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(line[i]);
		}

		Arrays.sort(A);
		Arrays.sort(B);

		int sum = 0;
		for (int a = 0, b = N - 1; a < N; a++, b--) {
			sum += (A[a] * B[b]);
		}

		System.out.println(sum);

//		per(0);
//
//		System.out.println(answer);

	}

	static void per(int count) {
		if (count == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				sum += (useA[i] * B[i]);
			}
			answer = Math.min(answer, sum);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!use[i]) {
				use[i] = true;
				useA[count] = A[i];
				per(count + 1);
				use[i] = false;
			}
		}
	}

}
