package week20;

import java.io.*;
import java.util.*;

/** 16472. 고냥이
 *  메모리 18508	kb
 *  시간 200 ms
 *  투포인터
 *
 *  [풀이]
 *  1. start, end 인덱스는 0부터 시작한다.
 *  2. end 인덱스에 해당하는 문자를 map 에 넣는다.(이미 있으면 +1을 한다)
 *     length++ 한다.
 *  3. 만약 map의 size가 N보다 커지면 map 에서 end의 값을 -1 한다.(1이라면 삭제한다)
 *     length--, start++ 한다.
 *  4. length 값 중 최대 값을 구한다.
 */
public class BJ_G4_16472_18508kb_200ms {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int maxLength;
	static int N;
	static String word;
	static Map<Character, Integer> map;

	public static void main(String[] args) throws IOException {
		init();
		solve();
		output();
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		word = br.readLine();
		maxLength = Integer.MIN_VALUE;
		map = new HashMap<>();
	}

	private static void solve() {
		int start = 0;
		int end = 0;
		int length = 0;

		while (start < word.length() && end < word.length()) {
			char endLetter = word.charAt(end);
			map.put(endLetter, map.getOrDefault(endLetter, 0) + 1);
			length++;

			if (map.size() > N) {
				char startLetter = word.charAt(start);
				if (map.get(startLetter) > 1) {
					map.put(startLetter, map.get(startLetter) - 1);
				} else {
					map.remove(startLetter);
				}
				start++;
				length--;
			}

			maxLength = Math.max(length, maxLength);
			end++;
		}
	}

	private static void output() {
		System.out.println(maxLength);
	}
}
