package week11;

import java.io.*;
import java.util.*;

/** 2109. 순회강연
 * 메모리 21132 kb
 * 시간 260 ms
 *
 * 그리디, 우선순위
 * pay로 내림차순 정렬, pay 같으면 day로 내림차순 정렬
 * day 방문 배열 생성
 * 해당 pair가 지금부터 앞선 날짜까지 강의일이 비어있으면 누적 및 방문처리
 */
public class BJ_G3_2109_순회강연_21132kb_260ms {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static PriorityQueue<Pair> pairs;
    static int max;
    static int result;

    public static void main(String[] args) throws Exception {
        init();
        solve();
        output();
    }

    private static void output() {
        System.out.println(result);
    }

    public static void solve() {

        if(N == 0) {
            result = 0;
            return;
        }

        boolean[] visit = new boolean[max + 1];

        while (!pairs.isEmpty()) {
            Pair cur = pairs.poll();

            for (int i = cur.day; i > 0; i--) {
                if(visit[i]) {
                    continue;
                }

                visit[i] = true;
                result += cur.pay;
                break;
            }
        }
    }

    public static void init() throws Exception{
        N = Integer.parseInt(br.readLine());
        max = Integer.MIN_VALUE;

        pairs = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            int p = Integer.parseInt(inputs[0]);
            int d = Integer.parseInt(inputs[1]);

            max = Math.max(max, d);
            pairs.add(new Pair(p, d));
        }
    }

    static class Pair implements Comparable<Pair> {
        int pay;
        int day;

        Pair(int p, int d) {
            this.pay = p;
            this.day = d;
        }

        @Override
        public int compareTo(Pair o) {
            if(this.pay == o.pay) {
                return Integer.compare(this.day, o.day) * -1;
            }
            return Integer.compare(this.pay, o.pay) * -1;
        }
    }
}