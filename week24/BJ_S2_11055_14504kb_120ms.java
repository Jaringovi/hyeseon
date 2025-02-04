import java.io.*;
import java.util.*;

/** 11055. 가장 큰 증가하는 부분수열
 * 메모리 14504 kb	
 * 시간 120 ms
 */
public class BJ_S2_11055_14504kb_120ms {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] numbers;
    static int[] dp;
    
    public static void main(String[] args) throws Exception {
        init();
        solve();
        output();
    }
    
    public static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        numbers = new int[N+1];
        dp = new int[N+1];
        for(int i=1; i<=N; i++) {
            numbers[i] = Integer.parseInt(inputs[i-1]);
        } 
    }
    
    public static void solve() {
        for(int i=1; i<=N; i++) {
            // 부분수열은 자기 자신을 포함한다.
            dp[i] = numbers[i];
            
            for(int j=1; j<i; j++) {
                if(numbers[i] > numbers[j]) {
                    dp[i] = Math.max(dp[j] + numbers[i], dp[i]);
                }
            }
        }
    }
    
    public static void output() {
        int max = Integer.MIN_VALUE;
        for(int value : dp) {
            max = Math.max(value, max);
        }
        System.out.println(max);
    }
}
