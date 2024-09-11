package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**14889. 스타트와 링크
 * 메모리 40908 kb
 * 시간 272 ms
 *
 * 조합
 */
public class BJ_S1_14889 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int teamN;
	static int[][] map;

	static int result;

	public static void main(String[] args) throws Exception {
		init();
		solve(0, 0, new boolean[N]);
		output();
	}

	public static void output() {
		System.out.println(result);
	}

	public static void solve(int cnt, int index, boolean[] selected) {
		if(cnt == teamN) {
			int start = calculate(selected, true);

			int link = calculate(selected, false);

			result = Math.min(result, Math.abs(start - link));
			return;
		}

		for (int i = index; i < N; i++) {
			selected[i] = true;
			solve(cnt + 1, i + 1, selected);
			selected[i] = false;
		}
	}

	private static int calculate(boolean[] selected, boolean status) {
		int[] teams = new int[teamN];
		int index = 0;
		for (int i = 0; i < N; i++) {
			if(selected[i] == status) {
				teams[index++] = i;
			}
		}

		int sum = 0;
		for (int i = 0; i < teamN; i++) {
			for (int j = 0; j < teamN; j++) {
				sum += map[teams[i]][teams[j]];
			}
		}
		return sum;
	}

	public static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
		teamN = N / 2;

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] inputs = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
			}
		}

		result = Integer.MAX_VALUE;
	}
}
