package week4;

import java.io.*;
import java.util.*;

/*
2630. 색종이 만들기
재귀 문제
1. 종료 조건
  - 칸이 1개가 되면 그 색종이를 count++ 후 return
  - 모든 칸이 똑같은 색이면 그 색종이를 count++ 후 return
2. start point(0, 0), end point(n, n) 으로 정의
3. 모든 칸이 똑같은 색이 아니거나 칸이 1개가 아니면 좌표를 4분할하여 재귀 돌림
4. count 출력
 */
public class BOJ_2630 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static int n;
    static Map<Integer, Integer> count = new HashMap<>();

    public static void main(String[] args) throws IOException {
        init();
        solve(0, 0, n, n);
        output();
    }

    private static void output() {
        for (int key : count.keySet()) {
            sb.append(count.get(key)).append("\n");
        }
        System.out.println(sb);
    }

    private static void solve(int sy, int sx, int ey, int ex) {
        if(isEqual(sy, sx, ey, ex)) {
            count.put(map[sy][sx], count.get(map[sy][sx]) + 1);
            return;
        }

        // 왼상
        solve(sy, sx, (sy + ey) / 2, (sx + ex) / 2);
        // 오상
        solve(sy, (sx + ex) / 2, (sy + ey) / 2, ex);
        // 오하
        solve((sy + ey) / 2, (sx + ex) / 2, ey, ex);
        // 왼하
        solve((sy + ey) / 2, sx, ey, (sx + ex) / 2);
    }

    private static boolean isEqual(int sy, int sx, int ey, int ex) {
        int before = map[sy][sx];
        for (int i = sy; i < ey; i++) {
            for (int j = sx; j < ex; j++) {
                if(before != map[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] inputs = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        count.put(0, 0); // white count
        count.put(1, 0); // blue count
    }
}
