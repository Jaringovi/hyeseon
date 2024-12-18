package week19;

import java.io.*;
import java.util.*;

/** 1038. 감소하는 수
 * 메모리 14316 kb
 * 시간 108 ms
 * 백트래킹
 *
 * [풀이]
 * 1. 재귀적으로 감소하는 수를 만들어 리스트에 넣는다.
 * 2. 리스트를 오름차순 정렬한다.
 * 3. N번째에 해당하는 값이 있으면 그 값을 출력하고 없으면 -1을 출력한다.
 *
 * [key point]
 * 9876543210을 완전탐색하기엔 범위가 너무 크다.
 * num % 10 까지 for문을 돌며 일의자리를 만들어주면 현재 숫자보다 작은 수들을 차례로 만들 수 있다.
 */
public class BJ_G5_1038_14316kb_108ms {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static List<Long> decreaseNumbers;

	public static void main(String[] args) throws IOException {
		init();
		solve();
		output();
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		decreaseNumbers = new ArrayList<>();
	}

	private static void solve() {
		for (int i = 0; i < 10; i++) {
			makeDecreaseNumber(i, 1);
		}

		Collections.sort(decreaseNumbers);
	}

	// num: 현재 생성 중인 숫자, value: 현재 숫자의 자릿수
	private static void makeDecreaseNumber(long num, int value) {
		if (value > 10) {
			return;
		}

		decreaseNumbers.add(num);

		for (int i = 0; i < num % 10; i++) {
			makeDecreaseNumber((num * 10) + i, value + 1);
		}
	}

	private static void output() {
		if (N >= decreaseNumbers.size()) {
			System.out.println(-1);
		}else {
			System.out.println(decreaseNumbers.get(N));
		}
	}
}
