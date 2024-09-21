package week10;

import java.io.*;
import java.util.Arrays;

/** 3017. 입국심사
 * 메모리 27668 kb
 * 시간 412 ms
 *
 * 이분탐색
 * getCount 할 때 count가 personN을 넘으면 끊어줘야 한다. -> 안 하면 32%에서 틀림
 * personN이 1억까지기 나올 수 있기 때문에 end값이 int 범위를 넘을 수 있다.
 */
public class BJ_G5_3079 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int personN;
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
		personN = Integer.parseInt(inputs[1]);

		Times = new long[(int)N];
		for (int i = 0; i < N; i++) {
			Times[i] = Integer.parseInt(br.readLine());
		}

		result = Long.MAX_VALUE;
	}
}
