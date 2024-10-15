package week13;

import java.io.*;

/** 12919. A와 B 2
 *  메모리: 14292 kb
 *  시간: 100ms
 *  재귀
 *
 *  [풀이]
 *  정방향 탐색할 경우 12%에서 시간초과 난다.
 *  정방향 탐색은 first 문자열에서 target 문자열로 가는 과정에서 모든 가능한 경우를 시도한다.
 *  따라서 문자열 길이가 1씩 늘어날 때마다 경우의 수가 2배씩 증가한다.
 *  시간 복잡도는 O(2^n) 이며 n은 49까지 가능하다.
 *
 *  역방향 탐색을 하면 다음 단계로 넘어가는 경우가 제한적이기 때문에
 *  매번 선택지를 모두 탐색하지 않고 한 가지 선택만으로 길이를 줄여가면서 탐색한다.
 *  시간 복잡도는 문자열의 길이에 비례하므로 O(n) 이다.
 */
public class BJ_G5_12919_14292kb_100ms {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String first;
    static String target;

    static int result;

    public static void main(String[] args) throws IOException {
        init();
        solve(target.length(), target);
        output();
    }

    private static void output() {
        System.out.println(result);
    }

    private static void solve(int length, String word) {
        if (length == first.length()) {
            if (first.contentEquals(word)) {
                result = 1;
                return;
            }
            return;
        }

        if (word.charAt(word.length() - 1) == 'A') {
            solve(length - 1, word.substring(0, word.length() - 1));
        }

        if (word.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder();
            word = word.substring(1);
            for (int i = word.length() - 1; i >= 0; i--) {
                sb.append(word.charAt(i));
            }

            solve(length - 1, sb.toString());
        }
    }

    private static void init() throws IOException {
        first = br.readLine();
        target = br.readLine();
    }
}
