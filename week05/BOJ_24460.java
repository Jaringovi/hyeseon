package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 24406. 특별상이라도 받고 싶어
 */
public class BOJ_24460 {

	static BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int[][] map;
	static int result;

	public static void main(String[] args) throws IOException {
		init();
		solve();
		output();
	}

	private static void output() {
		System.out.println(result);
	}

	private static void solve() {
		result = recursion(n, 0, 0);
	}

	private static int recursion(int n, int y, int x) {
		if(n == 0) {
			return map[y][x];
		}

		int[] temp = new int[4];
		int half = n / 2;

		// 1사분면 ~ 4사분면 탐색
		temp[0] = recursion(half, y, x);
		temp[1] = recursion(half, y + half, x);
		temp[2] = recursion(half, y, x + half);
		temp[3] = recursion(half, y + half, x + half);

		Arrays.sort(temp);
		return temp[1];
	}

	public static void init() throws IOException {
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			String[] inputs = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
			}
		}
	}
}
