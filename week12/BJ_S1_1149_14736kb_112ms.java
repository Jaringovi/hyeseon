package week12;

import java.io.*;

/**1149. RGB거리
 * 메모리 14736 kb
 * 시간 112 ms
 * dp
 *
 * [풀이]
 * 페인트를 칠할 수 있는 모든 경우의 수를 dp에 저장한다.
 * 이전에 0 또는 2를 칠했다면 지금은 1을 칠할 수 밖에 없는 규칙을 이용한다.
 * dp[i][페인트1] = Math.min(dp[i-1][페인트2], dp[i-1][페인트3]) + cost[i][페인트1]
 */
public class BJ_S1_1149_14736kb_112ms {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] dp;
    static int[][] costs;
    static int result;

    public static void main(String[] args) throws Exception {
        init();
        solve();
        output();
    }

    private static void output() {
        result = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            result = Math.min(dp[N][i], result);
        }
        System.out.println(result);
    }

    private static void solve() {
        dp[1][0] = costs[1][0];
        dp[1][1] = costs[1][1];
        dp[1][2] = costs[1][2];

        for (int i = 2; i <= N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }
    }

    public static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][3];
        costs = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            String[] inputs = br.readLine().split(" ");
            costs[i][0] = Integer.parseInt(inputs[0]);
            costs[i][1] = Integer.parseInt(inputs[1]);
            costs[i][2] = Integer.parseInt(inputs[2]);
        }
    }
}
