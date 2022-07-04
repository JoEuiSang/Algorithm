package algorithm.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj9935문자열폭발 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] arr = br.readLine().toCharArray();
		char[] target = br.readLine().toCharArray();

		Stack<Character> stack = new Stack<>();

		out: for (int i = 0; i < arr.length; i++) {
			stack.push(arr[i]);

			if (stack.size() >= target.length) {
				Stack<Character> tmp = new Stack<>();
				boolean check = true;
				for (int idx = target.length - 1; idx >= 0; idx--) {
					if (stack.peek() == target[idx]) {
						tmp.push(stack.pop());

					} else {
						check = false;
						while (tmp.size() > 0) {
							stack.push(tmp.pop());
						}
						continue out;
					}
				}

			}
		}

		if (stack.size() == 0)
			System.out.println("FRULA");
		else {
			StringBuilder sb = new StringBuilder();
			stack.forEach(e -> sb.append(e));
			System.out.println(sb.toString());
		}
	}

}
