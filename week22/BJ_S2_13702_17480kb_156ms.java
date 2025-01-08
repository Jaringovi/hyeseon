package week22;

import java.io.*;

/** 13702. 이상한 술집
 * 메모리 17480 kb
 * 시간 156 ms
 * 이분탐색
 */
public class BJ_S2_13702_17480kb_156ms {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K;
	static int[] alcohol;
	static int max;
	static long result;

	public static void main(String[] args) throws IOException {
		init();
		solve();
		output();
	}

	private static void init() throws IOException {
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		K = Integer.parseInt(inputs[1]);

		max = Integer.MIN_VALUE;
		alcohol = new int[N];
		for (int i = 0; i < N; i++) {
			alcohol[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, alcohol[i]);
		}
	}

	private static void solve() {
		long start = 1;
		long end = max;

		while (start <= end) {
			long mid = (start + end) / 2;

			if (calculate(mid) >= K) {
				result = mid;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
	}

	private static int calculate(long mid) {
		int count = 0;
		for (int i = 0; i < alcohol.length; i++) {
			count += alcohol[i] / mid;
		}
		return count;
	}

	private static void output() {
		System.out.println(result);
	}
}
