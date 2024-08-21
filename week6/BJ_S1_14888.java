package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/** 14888 연산자 끼워넣기
 * 메모리 18076 kb
 * 시간 672 ms
 * 연산자를 리스트로 입력받아 순열을 만들고 계산한 sum값으로 max와 min값 갱신
 */
public class BJ_S1_14888 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int[] numbers;
	static List<Character> signs;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception  {
		init();
		makePermutation(0, new boolean[signs.size()], new char[signs.size()]);
		output();
	}

	private static void output() {
		sb.append(max).append("\n").append(min);
		System.out.println(sb);
	}

	private static void makePermutation(int cnt, boolean[] visit, char[] choose) {
		if(cnt == signs.size()) {
			int sum = calculate(choose);
			min = Math.min(sum, min);
			max = Math.max(sum, max);
		}

		for(int i=0; i<signs.size(); i++) {
			if(visit[i]) {
				continue;
			}
			visit[i] = true;
			choose[cnt] = signs.get(i);
			makePermutation(cnt + 1, visit, choose);
			visit[i] = false;
		}
	}

	private static int calculate(char[] choose) {
		int sum = numbers[0];
		for (int i = 0; i < choose.length; i++) {
			switch(choose[i]) {
				case '+' :
					sum += numbers[i + 1];
					break;
				case '-' :
					sum -= numbers[i + 1];
					break;
				case '*' :
					sum *= numbers[i + 1];
					break;
				case '/' :
					sum /= numbers[i + 1];
					break;
			}
		}
		return sum;
	}

	private static void init() throws Exception {
		n = Integer.parseInt(br.readLine());
		numbers = new int[n];
		String[] inputs = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(inputs[i]);
		}

		signs = new ArrayList<>();
		inputs = br.readLine().split(" ");

		for (int j = 0; j < Integer.parseInt(inputs[0]); j++) {
			signs.add('+');
		}
		for (int j = 0; j < Integer.parseInt(inputs[1]); j++) {
			signs.add('-');
		}
		for (int j = 0; j < Integer.parseInt(inputs[2]); j++) {
			signs.add('*');
		}
		for (int j = 0; j < Integer.parseInt(inputs[3]); j++) {
			signs.add('/');
		}
	}
}
