package week20;

import java.io.*;
import java.util.*;

/** 13140. Hello World!
 * 메모리 77792 kb
 * 시간 256 ms
 * 브루트포스
 *
 * [풀이]
 * 1. 앞자리 수가 0이면 안되기 때문에 10_000부터 반복문을 시작한다.
 *    N-i 하여 second를 구한다. second의 자릿수가 다섯자리가 안되면 넘어간다.
 * 2. first의 3, 4번째 수와 second의 4번째 수가 같은지 확인하다. 다르면 넘어간다.
 * 3. first의 5번째 수와 second의 2번째 수가 같은지 확인한다. 다르면 넘어간다.
 * 4. 나머지 숫자들 간에 중복이 있는지 확인한다.
 * 5. 중복이 없으면 break 하고 해당 i를 first, N-i를 second로 출력한다.
 * 6. 반복문이 끝나도 first와 second가 갱신되지 않았다면 No Answer를 출력한다.
 */
public class BJ_G5_13140_77792kb_256ms {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int first;
	static int second;

	public static void main(String[] args) throws IOException {
		init();
		solve();
		output();
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
	}

	private static void solve() {
		for (int i = 10_000; i <= N - 10_000; i++) {
			String strFirst = String.valueOf(i);
			String strSecond = String.valueOf(N - i);

			if (strSecond.length() != 5) {
				continue;
			}

			if (!(strFirst.charAt(2) == strFirst.charAt(3) && strFirst.charAt(3) == strSecond.charAt(3))) {
				continue;
			}

			if (strFirst.charAt(4) != strSecond.charAt(1)) {
				continue;
			}

			Set<Character> usedDigits = new HashSet<>();
			boolean isFail = false;
			for (int j = 0; j < 5; j++) {
				if (j == 3) {
					continue;
				} else if (j == 4) {
					if (!usedDigits.add(strSecond.charAt(j))) {
						isFail = true;
						break;
					}
				} else if (!usedDigits.add(strFirst.charAt(j)) || !usedDigits.add(strSecond.charAt(j))) {
					isFail = true;
					break;
				}
			}

			if (!isFail) {
				first = i;
				second = N - i;
				break;
			}
		}
	}

	private static void output() {
		if (first == 0 && second == 0) {
			sb.append("No Answer");
		} else {
			sb.append("  ").append(first).append("\n");
			sb.append("+ ").append(second).append("\n");
			sb.append("-------\n");
			if (String.valueOf(N).length() > 5) {
				sb.append(" ").append(N);
			} else {
				sb.append("  ").append(N);
			}
		}
		System.out.println(sb);
	}
}
