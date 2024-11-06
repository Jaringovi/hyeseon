package week16;

import java.io.*;

/** 6236. 용돈 관리
 * 메모리 22244 kb
 * 시간 244 ms
 * 이분 탐색
 *
 * [풀이]
 * Lower Bound를 찾는 이분 탐색 방식 : 특정 조건을 만족하는 가장 작은 값
 * start : 가장 큰 지출액 <- 한 번의 인출로 해당일의 지출을 만족해야 하기 때문
 * end : 총 지출액
 * 
 * 검사(calculate) 방식
 * 남은 금액이 해당일의 지출액보다 작으면 mid원을 인출하고 count++
 * 남은 금액에서 해당일의 지출액을 뺀다.
 * 구해진 count가 M과 같거나 작은 경우 범위를 줄여가며 탐색한다.
 */
public class BJ_S1_6236_22244kb_244ms {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static int maxExpenses;
    static int totalExpenses;
    static int[] prices;
    static int result;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        output();
    }

    private static void init() throws IOException {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        maxExpenses = Integer.MIN_VALUE;
        totalExpenses = 0;
        prices = new int[N];
        for (int i = 0; i < N; i++) {
            prices[i] = Integer.parseInt(br.readLine());
            totalExpenses += prices[i];
            maxExpenses = Math.max(maxExpenses, prices[i]);
        }
    }

    private static void solve() {
        int start = maxExpenses;
        int end = totalExpenses;

        while (start <= end) {
            int mid = (start + end) / 2;

            int count = calculate(mid);
            if(count <= M) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
    }

    private static int calculate(int mid) {
        int temp = mid;
        int count = 1;
        for (int price : prices) {
            if (temp < price) {
                temp = mid;
                count++;
            }
            temp -= price;
        }
        return count;
    }

    private static void output() {
        System.out.println(result);
    }
}
