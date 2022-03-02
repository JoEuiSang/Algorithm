package algorithm.백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj1205등수구하기 {
	static int N, P, rank[], descRank[], answer;
	static boolean input;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line[] = br.readLine().split(" ");

		N = Integer.parseInt(line[0]);
		int score = Integer.parseInt(line[1]);
		P = Integer.parseInt(line[2]);

		if (N == 0) {
			System.out.println(1);
			return;
		}

		rank = new int[N];
		descRank = new int[N];

		line = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			rank[i] = Integer.parseInt(line[i]);
		}

		Arrays.sort(rank);

		for (int front = 0, back = N - 1; front < N; front++, back--) {
			descRank[back] = rank[front];
		}

		for (int i = 0; i < N; i++) {
			if (descRank[i] < score) {
				answer = i + 1;
				input = true;
				break;
			} else if (descRank[i] == score) {
				if (i + 1 == P) {
					answer = -1;
					input = true;
					break;
				} else {
					answer = i + 1;
					input = true;
					break;
				}
			}
		}

		if (!input && N <= P)
			answer = N + 1;

		System.out.println(answer);

	}

}
