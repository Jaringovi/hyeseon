package week01;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
14620. 꽃길
 */
public class BOJ_14620 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[][] map;
    static boolean[][] visit;
    static int[][] dxy = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상우하좌
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        input();

        backtracking(0);

        System.out.println(min);
    }

    private static void backtracking(int depth) {
        if(depth == 3) {
            int sum = getPrice();
            min = Math.min(sum, min);
            return;
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!canPlant(i, j)) {
                    continue;
                }
                plant(i, j);

                backtracking(depth + 1);

                remove(i, j);
            }
        }
    }

    private static void remove(int y, int x) {
        visit[y][x] = false;
        for (int i = 0; i < 4; i++) {
            int ny = dxy[i][0] + y;
            int nx = dxy[i][1] + x;

            visit[ny][nx] = false;
        }
    }

    private static void plant(int y, int x) {
        visit[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = dxy[i][0] + y;
            int nx = dxy[i][1] + x;

            visit[ny][nx] = true;
        }
    }

    private static boolean canPlant(int y, int x) {
        if(visit[y][x]) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            int ny = dxy[i][0] + y;
            int nx = dxy[i][1] + x;

            if (visit[ny][nx]|| !isRange(ny, nx)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }

    private static int getPrice() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!visit[i][j])
                    continue;
                sum += map[i][j];
            }
        }
        return sum;
    }

    private static void input() throws Exception {
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

    }
}