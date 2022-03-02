package algorithm.prgm;

import java.util.LinkedList;
import java.util.Queue;

public class �ٸ�����������Ʈ�� {

	public static void main(String[] args) {
		int bridge_length = 2, weight = 10, truck_weights[] = { 7, 4, 5, 6 };
		solution(bridge_length, weight, truck_weights);

	}

	static public int solution(int bridge_length, int weight, int[] truck_weights) {
		int time = 0;

		Queue<Integer> wait = new LinkedList();
		Queue<Integer> bridge = new LinkedList();

		for (int i = 0; i < truck_weights.length; i++) {
			wait.offer(truck_weights[i]);
		}

		for (int i = 0; i < bridge_length; i++) {
			bridge.offer(0);
		}

		int bridge_weight = 0; // ���� �ٸ��� �ִ� Ʈ������ ���� ��
		int bridge_count = 0;

		while (!bridge.isEmpty()) {
			time++;

			int truck = bridge.poll();
			if (truck != 0) {
				bridge_weight -= truck;
				bridge_count--;
			}

			if (!wait.isEmpty()) {
				if (bridge_count < bridge_length // ���� ����
						&& bridge_weight + wait.peek() <= weight) {// ���� ����
					bridge_weight += wait.peek();
					bridge.offer(wait.poll());
					bridge_count++;
				} else {

					bridge.offer(0);
				}
			}
		}
		System.out.println(time);
		return time;
	}

}
