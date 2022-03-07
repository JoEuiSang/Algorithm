package algorithm.백준.수학;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj13458시험감독 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int testRoom[] = new int[N];

		long ans = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			testRoom[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());

		int mainSupervisor = Integer.parseInt(st.nextToken());
		int subSupervisor = Integer.parseInt(st.nextToken());

		for (Integer student : testRoom) {

			// 메인 감독 한명 무조건 투입
			student -= mainSupervisor;
			ans++;

			// 감독해야할 학생이 남아있으면 부감독 투입
			if (student > 0) {
				// 부감독 한명이 감독할수있는 학생수이면
				if (student <= subSupervisor) {
					ans++;
					continue;
				} else { // 부감독 여러명이 있어야하면
					int remain = student % subSupervisor;

					if (remain == 0)
						ans += (student / subSupervisor);
					else
						ans += ((student / subSupervisor) + 1);
				}
			}
		}

		System.out.println(ans);

	}

}
