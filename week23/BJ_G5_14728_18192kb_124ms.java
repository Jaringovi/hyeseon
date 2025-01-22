package week23;

import java.io.*;
import java.util.*;

/** 14728. 벼락치기
 * 메모리 18192 kb
 * 시간 124 ms
 * dp
 */
public class BJ_G5_14728_18192kb_124ms {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, T;
	static int[][] problems;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		init();
		solve();
		output();
	}

	public static void init() throws Exception {
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		T = Integer.parseInt(inputs[1]);

		problems = new int[N+1][2];
		dp = new int[N+1][T+1];
		for(int i=1; i<=N; i++) {
			inputs = br.readLine().split(" ");
			problems[i][0] = Integer.parseInt(inputs[0]);
			problems[i][1] = Integer.parseInt(inputs[1]);
		}
	}

	public static void solve() {
		Arrays.fill(dp[0], 0);

		for(int i=1; i<=N; i++) {
			for(int j=1; j<=T; j++) {
				if(j < problems[i][0]){
					dp[i][j] = dp[i-1][j];
					continue;
				}
				dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-problems[i][0]]+problems[i][1]);
			}
		}
	}

	public static void output() {
		System.out.println(dp[N][T]);
	}
}
