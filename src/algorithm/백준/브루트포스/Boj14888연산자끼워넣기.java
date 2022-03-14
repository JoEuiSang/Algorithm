package algorithm.����.���Ʈ����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14888�����ڳ����ֱ� {


	    static int n;
	    static int[] arr;
	    static int[] operator = new int[4]; // 0 + , 1 -, 2 *, 3 /
	    static int max = Integer.MIN_VALUE;
	    static int min = Integer.MAX_VALUE;

	    public static void main(String[] args) throws IOException {
	        // input
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String s = br.readLine();
	        n = Integer.parseInt(s);
	        arr = new int[n];
	        int[] result = new int[n];
	        s = br.readLine();
	        StringTokenizer st = new StringTokenizer(s, " ");

	        for (int i = 0; i < n; i++) {
	            arr[i] = Integer.parseInt(st.nextToken());
	        }

	        s = br.readLine();
	        st = new StringTokenizer(s, " ");

	        for (int i = 0; i < 4; i++) {
	            operator[i] = Integer.parseInt(st.nextToken());
	        }
	        //input end

	        backTracking(1, arr[0]);
	        System.out.println(max);
	        System.out.println(min);
	    }


	    static void backTracking(int d, int value) {
	        if (d == n) { // depth�� n�� �Ǹ� �ִ밪 �Ǻ� �� return
	            max = Math.max(max, value);
	            min = Math.min(min, value);
	            return;
	        }
	        for (int i = 0; i < 4; i++) { // operator �迭 Ž��

	            if (operator[i] > 0) { // �ش� �����ڰ� 1�� �̻� �ִ� ��쿡
	                operator[i]--; // �����ڸ� ����� ���̱⿡ 1�� ���ش�.

	                switch (i) {
	                    case 0: // �����ڰ� +��
	                        backTracking(d + 1, value + arr[d]); // depth�� 1 ������Ű�� value�� ��� �� dfs ȣ��
	                        break;
	                    case 1: // �����ڰ� -��
	                        backTracking(d + 1, value - arr[d]); // depth�� 1 ������Ű�� value�� ��� �� dfs ȣ��
	                        break;
	                    case 2: // �����ڰ� *��
	                        backTracking(d + 1, value * arr[d]); // depth�� 1 ������Ű�� value�� ��� �� dfs ȣ��
	                        break;
	                    case 3: // �����ڰ� /��
	                        backTracking(d + 1, value / arr[d]); // depth�� 1 ������Ű�� value�� ��� �� dfs ȣ��
	                        break;
	                }
	                operator[i]++; // ����� �����ڸ� ���� dfs���� ����� �� �ֵ��� �ٽ� �ǵ����ش�.
	            }
	        }
	    }
	}