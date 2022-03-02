package algorithm.백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
5 4
2 2
1 1 E
5 4 W
1 F 7
2 F 7*/
public class Boj2174로봇시뮬레이션 {
	static int R, C, robot, oper, map[][];
	static ArrayList<Robot> robotList = new ArrayList<>();
	static ArrayList<Oper> operList = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	static boolean gameOver;

	static class Robot {
		int r, c;
		char dir;

		public Robot(int r, int c, char dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	static class Oper {
		int robotNum, repeat;
		char oper;

		public Oper(int robotNum, char oper, int repeat) {
			super();
			this.robotNum = robotNum;
			this.repeat = repeat;
			this.oper = oper;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);

		map = new int[R + 1][C + 1];

		input = br.readLine().split(" ");
		robot = Integer.parseInt(input[0]);
		oper = Integer.parseInt(input[1]);

		for (int i = 0; i < robot; i++) {
			input = br.readLine().split(" ");
			int r = Integer.parseInt(input[0]);
			int c = Integer.parseInt(input[1]);
			char dir = input[2].charAt(0);
			robotList.add(new Robot(r, c, dir));
			map[r][c] = i + 1;
		}

		for (int i = 0; i < oper; i++) {
			input = br.readLine().split(" ");
			int robotNum = Integer.parseInt(input[0]);
			char oper = input[1].charAt(0);
			int repeat = Integer.parseInt(input[2]);
			operList.add(new Oper(robotNum, oper, repeat));
		}

		for (Oper o : operList) {
			Robot now = robotList.get(o.robotNum - 1);
			operate(now, o);
			if (gameOver)
				break;
		}

		if (!gameOver)
			System.out.println("OK");
	}

	static char dir[] = { 'N', 'E', 'S', 'W' };

	public static void operate(Robot robot, Oper oper) {
		for (int i = 0; i < oper.repeat; i++) {
			if (oper.oper == 'F') {
				move(robot, oper.robotNum);
			} else if (oper.oper == 'R') {
				rotate(robot, 'R');
			} else if (oper.oper == 'L') {
				rotate(robot, 'L');
			}
			if (gameOver)
				break;
		}
	}

	public static void rotate(Robot robot, char rotate) {
		int idx = 0;
		if (robot.dir == 'N')
			idx = 0;
		else if (robot.dir == 'E')
			idx = 1;
		else if (robot.dir == 'S')
			idx = 2;
		else if (robot.dir == 'W')
			idx = 3;

		if (rotate == 'R')
			idx = (idx + 1) % 4;
		else if (rotate == 'L')
			idx = (idx - 1 + 4) % 4;

		robot.dir = dir[idx];
	}

	public static void move(Robot robot, int robotNum) {
		int nr = robot.r, nc = robot.c;

		if (robot.dir == 'N') {
			nr = robot.r + 1;
		} else if (robot.dir == 'E') {
			nc = robot.c + 1;
		} else if (robot.dir == 'S') {
			nr = robot.r - 1;
		} else if (robot.dir == 'W') {
			nc = robot.c - 1;
		}

		// 다음좌표가 맵안에 있으면
		if (nr >= 1 && nr <= R && nc >= 1 && nc <= C) {
			// 갈 좌표에 로봇이 없으면
			if (map[nr][nc] == 0) {
				map[robot.r][robot.c] = 0;
				robot.r = nr;
				robot.c = nc;
				map[robot.r][robot.c] = robotNum;
			} else { // 로봇이 있으면
				System.out.println("Robot " + robotNum + " crashes into robot " + map[nr][nc]);
				gameOver = true;
			}
		} else {
			System.out.println("Robot " + robotNum + " crashes into the wall");
			gameOver = true;
		}
	}

}
