package algorithm.백준.DP;

import java.util.Scanner;

public class Boj1003피보나치함수 {
	static int zero[], one[], T, N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			N = sc.nextInt();
			zero = new int[41];
			one = new int[41];

			if (N <= 1) {
				if (N == 0) {
					sb.append("1 0\n");
				} else if (N == 1) {
					sb.append("0 1\n");
				}
				continue;
			}

			zero[0] = 1;
			one[1] = 1;
			for (int i = 2; i <= N; i++) {
				zero[i] = zero[i - 1] + zero[i - 2];
				one[i] = one[i - 1] + one[i - 2];
			}
			sb.append(zero[N] + " " + one[N] + "\n");
		}
		System.out.println(sb.toString());
	}

}
