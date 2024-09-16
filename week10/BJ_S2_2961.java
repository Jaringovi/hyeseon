package week10;

import java.io.*;

/** 2961. 도영이가 만든 맛있는 음식
 * 메모리 14292 kb
 * 시간 104 ms
 *
 * 비트마스킹
 * 부분집합
 */
public class BJ_S2_2961 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] S;
	static int[] B;
	static int result;

	public static void main(String[] args) throws Exception {
		init();
		solve(0, 0, 1, 0);
		output();
	}

	public static void output() {
		System.out.println(result);
	}

	public static void solve(int cnt, int flag, int sValue, int bValue) {
		if (cnt == N) {
			// 공집합이 아닌 경우
			if (flag != 0) {
				result = Math.min(result, Math.abs(sValue - bValue));
			}
			return;
		}

		// 재료를 선택하는 경우
		solve(cnt + 1, flag | (1 << cnt), sValue * S[cnt], bValue + B[cnt]);

		// 재료를 선택하지 않는 경우
		solve(cnt + 1, flag, sValue, bValue);
	}

	public static void init() throws Exception {
		N = Integer.parseInt(br.readLine());

		S = new int[N];
		B = new int[N];
		for (int i = 0; i < N; i++) {
			String[] inputs = br.readLine().split(" ");
			S[i] = Integer.parseInt(inputs[0]);
			B[i] = Integer.parseInt(inputs[1]);
		}

		result = Integer.MAX_VALUE;
	}
}
