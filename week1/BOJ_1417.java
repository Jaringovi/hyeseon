package week1;

import java.io.*;
import java.util.*;

/* 1417. 국회의원 선거
 * 1. 1번 숫자는 target 숫자로 지정
 * 2. 2 ~ n 번 숫자는 내림차순으로 pq에 저장
 * 3. n == 1이면 0 출력 후 return
 * 4. target < pq.peek() 이면 target++, pq.add(pq.poll()-1) <- 이럴 때마다 count++
 * 5. count 출력
 */

public class BOJ_1417 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        if(n == 1) {
            System.out.println(0);
            return;
        }

        for (int i = 1; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int count = 0;
        while (target <= pq.peek()) {
            count++;
            target += 1;
            pq.add(pq.poll() - 1);
        }
        System.out.println(count);
        br.close();
    }
}