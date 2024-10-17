package week13;

import java.io.*;
import java.util.*;

/** 2294. 동전 2
 * 메모리 14276 kb
 * 시간 112 ms
 * dp
 *
 * [풀이]
 * dp: 현재 금액을 만들기 위한 최소 동전 개수
 * dp[i] = Math.min(dp[i], dp[i-coin] + 1)
 * 이전 동전을 사용한 경우의 결과인 dp[i-coin]에서 동전 1개를 더한 값과
 * 현재 dp[i] 값 중 더 작은 값을 선택하는 방식
 */
public class BJ_G5_2294_14276kb_112ms {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int Target;
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        output();
    }

    private static void init() throws IOException {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        Target = Integer.parseInt(inputs[1]);

        coins = new int[N];
        dp = new int[Target + 1];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void solve() {
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= Target; i++) {
                if (dp[i - coin] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
    }

    private static void output() {
        if (dp[Target] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(dp[Target]);
    }
}
