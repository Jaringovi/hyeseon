package week5;

import java.io.*;
import java.util.*;

/*
2502. 떡 먹는 호랑이
[ 접근 방법 ]
1. 재귀를 사용하여 d[2] 와 d[1] 의 개수를 count 한다.
2. 완전탐색으로 첫째날은 100_000 까지, 둘째날은 첫째날 이상으로 시작해서 100_000까지 반복문을 돌린다.
3. day1의 count * 첫째날 떡 개수 + day2의 count * 둘째날 떡 개수가 total과 같으면 그 떡 개수들을 출력한다.
 */
public class BOJ_2502 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] dp;
    static int n;
    static int total;
    static int twoCount = 0;
    static int oneCount = 0;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        output();
    }

    private static void output() {
        System.out.println(sb);
    }

    private static void solve() {
        dp(n);

        findFirstAndSecond();
    }

    private static void findFirstAndSecond() {
        for (int i = 1; i < 100_000; i++) {
            for (int j = i; j < 100_000; j++) {
                if((i * oneCount + j * twoCount) == total) {
                    sb.append(i).append("\n");
                    sb.append(j).append("\n");
                    return;
                }
            }
        }
    }

    private static void dp(int n) {
        if(n == 2) {
            twoCount++;
            return;
        }
        if(n == 1) {
            oneCount++;
            return;
        }

        dp(n - 1);
        dp(n - 2);
    }

    private static void input() throws Exception {
        String[] inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        total = Integer.parseInt(inputs[1]);

        dp = new int[n + 1];
    }
}
