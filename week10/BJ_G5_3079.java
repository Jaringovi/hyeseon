package week10;

import java.io.*;
import java.util.Arrays;

public class BJ_G5_3079 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static long personN;
	static long[] Times;

	static long result;

	public static void main(String[] args) throws Exception {
		init();
		solve();
		output();
	}

	private static void output() {
		System.out.println(result);
	}

	public static void solve() {
		Arrays.sort(Times);

		long start = 0;
		long end = Times[N - 1] * personN;

		while(start <= end) {
			long mid = (start + end) / 2;

			if (getCount(mid) >= personN) {
				end = mid - 1;
				result = mid;
			}else {
				start = mid + 1;
			}
		}
	}

	private static long getCount(long mid) {
		long count = 0;
		for (int i = 0; i < N; i++) {
			count += mid / Times[i];
			if(count > personN) {
				break;
			}
		}
		return count;
	}

	private static void init() throws Exception {
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		personN = Long.parseLong(inputs[1]);

		Times = new long[(int)N];
		for (int i = 0; i < N; i++) {
			Times[i] = Integer.parseInt(br.readLine());
		}

		result = Long.MAX_VALUE;
	}
}
