package week10;

import java.io.*;
import java.util.*;

/**14891. 톱니바퀴
 * 메모리 14440 kb
 * 시간 104 ms
 *
 * 시뮬레이션
 * 1. 명령어에 따라 지금 회전시킨 톱니바퀴의 왼쪽 오른쪽의 톱니바퀴들의 뱡향을 지정하는 배열을 만든다.
 * 2. 방향 배열에 따라 모든 톱니바퀴를 회전시킨다.
 * 3. k번 반복 후 나오는 12시 방향 값을 2의 지수승으로 누적한다.
 */
public class BJ_G5_14891 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<Wheel> wheels = new ArrayList<>();
	static int k;
	static List<Command> commands = new ArrayList<>();
	static int result;

	public static void main(String[] args) throws Exception {
		init();
		solve();
		output();
	}

	public static void output() {
		System.out.println(result);
	}

	public static void solve() {
		for(Command command : commands) {
			// 각 명령에 대해 회전 방향을 저장할 배열
			int[] rotateDir = new int[5]; // 1번부터 4번까지 사용
			rotateDir[command.number] = command.dir;

			// 왼쪽 톱니바퀴 확인
			int current = command.number;
			while (current > 1) {
				if (wheels.get(current).values[6] != wheels.get(current - 1).values[2]) {
					rotateDir[current - 1] = -rotateDir[current];
				} else {
					break;
				}
				current--;
			}

			// 오른쪽 톱니바퀴 확인
			current = command.number;
			while (current < 4) {
				if (wheels.get(current).values[2] != wheels.get(current + 1).values[6]) {
					rotateDir[current + 1] = -rotateDir[current];
				} else {
					break;
				}
				current++;
			}

			// 모든 톱니바퀴 회전
			for (int i = 1; i <= 4; i++) {
				if (rotateDir[i] != 0) {
					wheels.get(i).rotate(rotateDir[i]);
				}
			}
		}

		result = calculate();
	}

	public static int calculate() {
		int sum = 0;
		for (int i = 1; i < wheels.size(); i++) {
			if(wheels.get(i).is12South()) {
				sum += (int)Math.pow(2, i - 1);
			}
		}
		return sum;
	}

	public static void init() throws Exception {
		for (int i = 0; i < 5; i++) {
			wheels.add(new Wheel(i));
		}

		for (int i = 1; i <= 4; i++) {
			char[] inputs = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				wheels.get(i).values[j] = inputs[j] - '0';
			}
		}

		k = Integer.parseInt(br.readLine());

		for (int i = 0; i < k; i++) {
			String[] inputs = br.readLine().split(" ");
			int num = Integer.parseInt(inputs[0]);
			int dir = Integer.parseInt(inputs[1]);

			commands.add(new Command(num, dir));
		}
	}

	static class Wheel {
		int number;
		int[] values;

		Wheel(int number) {
			this.number = number;
			this.values = new int[8];
		}

		public void rotate(int r) {
			// 시계방향 회전
			if (r == 1) {
				int temp = this.values[7];
				for (int i = 7; i >= 1; i--) {
					this.values[i] = this.values[i - 1];
				}
				this.values[0] = temp;
			}
			// 반시계방향 회전
			else {
				int temp = this.values[0];
				for (int i = 1; i < 8; i++) {
					this.values[i - 1] = this.values[i];
				}
				this.values[7] = temp;
			}
		}

		public boolean is12South() {
			return this.values[0] == 1;
		}
	}

	static class Command {
		int number;
		int dir;

		Command(int number, int dir) {
			this.number = number;
			this.dir = dir;
		}
	}
}
