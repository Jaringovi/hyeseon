package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**10844. 쉬운 계단 수
 * 메모리 14300 kb
 * 시간 104 ms
 * dp
 *
 * [풀이]
 * 1의 자리수가 0~9일 때 +1, -1 일 때의 경우의 수를 더한다.
 * 단, N이 1일 경우 0으로 시작하는 숫자는 없으므로 dp[1][0] = 0 으로 초기화한다.
 *
 * result += dp[N][i] % 1_000_000_000;
 * 이렇게 누적하면 dp[N][i] 값이 이미 1_000_000_000으로 나눈 나머지 값으로 계산되기 때문에 result가 점점 커지면
 * overflow가 발생할 수 있다.
 * 따라서 매번 덧셈 연산 후 모듈러 연산을 해서 1_000_000_000 미만의 값으로 유지해주어야 result가
 * int 범위 안에서 결과가 나온다.
 */
public class BJ_S1_10844_14300kb_104ms {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] dp;
    static int result;

    public static void main(String[] args) throws Exception {
        init();
        solve();
        output();
    }

    private static void output() {
        result = 0;
        for (int i = 0; i < dp[0].length; i++) {
            result = (result + dp[N][i]) % 1_000_000_000;
        }
        System.out.println(result);
    }

    private static void solve() {
        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][1] % 1_000_000_000;
            for (int j = 1; j <= 8; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1_000_000_000;
            }
            dp[i][9] = dp[i - 1][8] % 1_000_000_000;
        }
    }

    public static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][10];
    }
}
