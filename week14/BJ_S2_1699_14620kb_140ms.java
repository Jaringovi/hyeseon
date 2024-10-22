package week14;

import java.io.*;

/** 1699. 제곱수의 합
 * 메모리 14620 kb
 * 시간 140 ms
 * dp
 *
 * [풀이]
 * dp[i] : 숫자 i를 표현하기 위한 최소 제곱수의 개수를 저장하는 배열
 * dp[i] = 1 (i=j^2)
 * dp[i] = min(dp[i-j^2] + 1) (j는 1부터 루트i 까지)
 */
public class BJ_S2_1699_14620kb_140ms {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        output();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
    }

    private static void solve() {
        dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            int sqrt = (int) Math.sqrt(i);
            if(Math.pow(sqrt, 2) == i) {
                dp[i] = 1;
                continue;
            }

            dp[i] = dp[i - 1] + 1;
            for (int j = 2; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
    }

    private static void output() {
        System.out.println(dp[N]);
    }
}
