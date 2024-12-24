package week20;

import java.io.*;
import java.util.*;

/** 11060. 점프 점프
 * 메모리 14392 kb
 * 시간 112 ms
 * dp
 *
 * [풀이]
 * 1. dp 배열 선언 후 충분히 큰 값으로 초기화 시킨다.
 * 2. numbers 값 범위만큼 dp를 갱신한다. 갱신된 이전 값이 있다면 더 작은 값을 갱신한다.
 * 3. dp[N] 이 초기값이라면 -1 출력하고 아니라면 dp[N]을 출력한다.
 */
public class BJ_S2_11060_14392kb_112ms {
	static final int MAX = 1_000_000;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] numbers;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		init();
		solve();
		output();
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());

		numbers = new int[N + 1];
		dp = new int[N + 1];
		String[] inputs = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			numbers[i + 1] = Integer.parseInt(inputs[i]);
		}
		Arrays.fill(dp, MAX);
	}

	private static void solve() {
		dp[1] = 0;

		for (int i = 1; i <= N; i++) {
			int range = numbers[i];
			for (int j = i; j <= i + range; j++) {
				if (j > N) {
					break;
				}
				dp[j] = Math.min(dp[j], dp[i] + 1);
			}
		}
	}

	private static void output() {
		if (dp[N] == MAX) {
			System.out.println(-1);
		} else {
			System.out.println(dp[N]);
		}
	}
}
