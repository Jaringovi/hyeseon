package week05;

import java.io.*;

/** 14501. 퇴사
 * 메모리 14272 kb
 * 시간 104 ms
 * dp
 *
 * [풀이]
 * 현재 날짜에 시작한 작업이 끝나고 수익을 얻는 날짜 : i + times[i]
 * dp[i] : i일까지 얻을 수 있는 최대 수익
 * <점화식>
 * 현재 날짜에서 상담을 하지 않는 경우
 * - dp[i+1] = max(dp[i], dp[i+1])
 * 현재 날짜에서 상담을 하는 경우
 * - 상담이 주어진 날짜 범위를 초과하지 않는다면
 * - dp[i+times[i]] = max(dp[i+times[i]], dp[i] + prices[i])
 */
public class BJ_S3_14501_14272kb_104ms {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] dp;
    static int[] times;
    static int[] prices;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        output();
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 2];
        times = new int[N + 1];
        prices = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            String[] inputs = br.readLine().split(" ");
            times[i] = Integer.parseInt(inputs[0]);
            prices[i] = Integer.parseInt(inputs[1]);
        }
    }

    private static void solve() {
        for (int i = 1; i <= N; i++) {
            if (i + times[i] <= N + 1) {
                dp[i + times[i]] = Math.max(dp[i + times[i]], dp[i] + prices[i]);
            }
            dp[i + 1] = Math.max(dp[i], dp[i + 1]);
        }
    }

    private static void output() {
        System.out.println(dp[N + 1]);
    }
}
