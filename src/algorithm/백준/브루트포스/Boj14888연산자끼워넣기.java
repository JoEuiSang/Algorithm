package algorithm.백준.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14888연산자끼워넣기 {


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
	        if (d == n) { // depth가 n이 되면 최대값 판별 후 return
	            max = Math.max(max, value);
	            min = Math.min(min, value);
	            return;
	        }
	        for (int i = 0; i < 4; i++) { // operator 배열 탐색

	            if (operator[i] > 0) { // 해당 연산자가 1개 이상 있는 경우에
	                operator[i]--; // 연산자를 사용할 것이기에 1을 빼준다.

	                switch (i) {
	                    case 0: // 연산자가 +면
	                        backTracking(d + 1, value + arr[d]); // depth를 1 증가시키고 value값 계산 후 dfs 호출
	                        break;
	                    case 1: // 연산자가 -면
	                        backTracking(d + 1, value - arr[d]); // depth를 1 증가시키고 value값 계산 후 dfs 호출
	                        break;
	                    case 2: // 연산자가 *면
	                        backTracking(d + 1, value * arr[d]); // depth를 1 증가시키고 value값 계산 후 dfs 호출
	                        break;
	                    case 3: // 연산자가 /면
	                        backTracking(d + 1, value / arr[d]); // depth를 1 증가시키고 value값 계산 후 dfs 호출
	                        break;
	                }
	                operator[i]++; // 사용한 연산자를 다음 dfs에서 사용할 수 있도록 다시 되돌려준다.
	            }
	        }
	    }
	}