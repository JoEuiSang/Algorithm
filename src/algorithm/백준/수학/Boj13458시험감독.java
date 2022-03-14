package algorithm.����.����;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj13458���谨�� {

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

			// ���� ���� �Ѹ� ������ ����
			student -= mainSupervisor;
			ans++;

			// �����ؾ��� �л��� ���������� �ΰ��� ����
			if (student > 0) {
				// �ΰ��� �Ѹ��� �����Ҽ��ִ� �л����̸�
				if (student <= subSupervisor) {
					ans++;
					continue;
				} else { // �ΰ��� �������� �־���ϸ�
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
