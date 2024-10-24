package week08;

import java.io.*;

/** 14500. 테트로미노
 * 메모리
 * 시간
 *
 * 구현, dfs, 완탐
 */
public class BJ_G4_14500 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visit;
    static int[][] dxy = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static int result;

    public static void main(String[] args) throws Exception {
        init();
        solve();
        output();
    }

    public static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = true;
                dfs(0, 0, i, j);
                visit[i][j] = false;
                findT(0, map[i][j], i, j, 0);
            }
        }
    }

    public static void findT(int depth, int sum, int y, int x, int start) {
        if(depth == 3) {
            result = Math.max(result, sum);
            return;
        }

        for (int i = start; i < 4; i++) {
            int ny = dxy[i][0] + y;
            int nx = dxy[i][1] + x;

            if (!isRange(ny, nx)) continue;

            findT(depth + 1, sum + map[ny][nx], y, x, i + 1);
        }
    }

    public static void dfs(int depth, int sum, int y, int x) {
        if(depth == 4) {
            result = Math.max(result, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = dxy[i][0] + y;
            int nx = dxy[i][1] + x;

            if(!isRange(ny, nx) || visit[ny][nx]) continue;

            visit[ny][nx] = true;
            dfs(depth + 1, sum + map[ny][nx], ny, nx);
            visit[ny][nx] = false;
        }
    }

    public static boolean isRange(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    public static void output() {
        System.out.println(result);
    }

    public static void init() throws Exception {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        map = new int[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        result = Integer.MIN_VALUE;
    }
}
