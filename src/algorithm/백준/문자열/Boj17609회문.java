package algorithm.백준.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj17609회문 {
	static int input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		input = Integer.parseInt(br.readLine());

		out: for (int repeat = 0; repeat < input; repeat++) {
			String word = br.readLine();

			int mid = word.length() % 2 == 0 ? word.length() / 2 - 1 : word.length() / 2;

			for (int start = 0, end = word.length() - 1; start < word.length(); start++, end--) {
				if (word.charAt(start) == word.charAt(end)) {
					if (end - start == 0 || end - start == 1) {
						sb.append("0\n");
						continue out;
					}
				} else {
					for (int start2 = start + 1, end2 = end; start2 < word.length(); start2++, end2--) {
						if (word.charAt(start2) == word.charAt(end2)) {
							if (end2 - start2 == 0 || end2 - start2 == 1) {
								sb.append("1\n");
								continue out;
							}
						} else {
							break;
						}
					}

					for (int start2 = start, end2 = end - 1; start2 < word.length(); start2++, end2--) {
						if (word.charAt(start2) == word.charAt(end2)) {
							if (end2 - start2 == 0 || end2 - start2 == 1) {
								sb.append("1\n");
								continue out;
							}
						} else {
							sb.append("2\n");
							continue out;
						}
					}
				}
			}
		}

		System.out.println(sb.toString());

	}

}
