package algorithm.prgm;

import java.util.PriorityQueue;

public class ´õ¸Ê°Ô {

	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(1);
		pq.add(3);
		pq.add(2);
		pq.add(0);
		pq.add(6);
		pq.add(8);
		pq.add(5);
		for(Integer i : pq) {
			System.out.println(i);
		}
		
		check(pq, 7);
	}
	
	static public boolean check(PriorityQueue pq, int K){
        return true;
    }

}
