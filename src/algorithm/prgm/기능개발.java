package algorithm.prgm;

import java.util.*;

public class 기능개발 {

	public static void main(String[] args) {
		int[] progresses = { 95, 90, 99, 99, 80, 99 };
		int[] speeds = { 1, 1, 1, 1, 1, 1 };
		System.out.println(solution(progresses, speeds));
	}

	static public int[] solution(int[] progresses, int[] speeds) {
		int size = progresses.length;
		int success = 0;
		ArrayList<Integer> answerList = new ArrayList<>();

		System.out.println(size);

		while (success < size) {
			int count = 0;
			for (int i = success; i < size; i++) {
				if (progresses[i] >= 100) {
					count++;
				} else
					break;
			}

			for (int i = success; i < size; i++) {
				progresses[i] += speeds[i];
			}

			if (count != 0) {
				success += count;
				answerList.add(count);
			}

		}

		int[] answer = new int[answerList.size()];
		for (int i = 0; i < answerList.size(); i++) {
			answer[i] = answerList.get(i);
			System.out.println(answer[i]);
		}

		String[] a = new String[1];
		
		return answer;
	}

}
