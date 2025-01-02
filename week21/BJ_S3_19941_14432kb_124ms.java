package week21;

import java.io.*;

/** 19941. 햄버거 분배
 * 메모리  14432 kb
 * 시간 124 ms
 * 그리디
 *
 * [풀이]
 * 현재 위치가 사람이라면 k범위 안에서 제일 왼쪽에 있는 햄버거를 고르는 것이 최적이다.
 */
public class BJ_S3_19941_14432kb_124ms {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K;
	static char[] hamburgerAndPerson;
	static int count;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		init();
		solve();
		output();
	}

	public static void init() throws Exception {
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		K = Integer.parseInt(inputs[1]);

		hamburgerAndPerson = br.readLine().toCharArray();
		count = 0;
		visit = new boolean[N];
	}

	private static void solve() {
		for (int i = 0; i < hamburgerAndPerson.length; i++) {
			if(hamburgerAndPerson[i] == 'P') {
				for (int j = Math.max(0, i - K); j < Math.min(i + K + 1, N); j++) {
					if (hamburgerAndPerson[j] == 'H' && !visit[j]) {
						visit[j] = true;
						count++;
						break;
					}
				}
			}
		}
	}

	private static void output() {
		System.out.println(count);
	}
}
