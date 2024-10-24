package week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 1012. 유기농 배추
 * 접근
 * 1. 전체 배열을 이중for문으로 탐색한다.
 * 2. 방문하지 않은 위치라면 bfs로 탐색한다.
 * 3. bfs 탐색을 하면서 사방탐색도 같이 한다.
 * 4. bfs 탐색을 완료하고 나왔을 때 count++ 한다.
 */
public class BOJ_1012 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int t;
    static int m; // 가로길이
    static int n; // 세로길이
    static int k; // 배추가 심어진 위치의 개수
    static int[][] map;
    static boolean[][] visit;
    static int count;

    static int[][] dxy = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상우하좌

    public static void main(String[] args) throws Exception {
        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            input();
            solve();
        }
    }

    private static void solve() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(canGo(i, j)) {
                    bfs(i, j);
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    private static void bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        visit[y][x] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cy = cur[0];
            int cx = cur[1];

            for(int i=0; i<4; i++) {
                int ny = cy + dxy[i][0];
                int nx = cx + dxy[i][1];

                if(isRange(ny, nx) && !visit[ny][nx] && map[ny][nx] == 1){
                    queue.add(new int[]{ny, nx});
                    visit[ny][nx] = true;
                }
            }
        }
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && x >= 0 && y < n && x < m;
    }

    private static boolean canGo(int y, int x) {
        return !visit[y][x] && map[y][x] == 1;
    }

    private static void input() throws IOException {

        String[] inputs = br.readLine().split(" ");
        m = Integer.parseInt(inputs[0]);
        n = Integer.parseInt(inputs[1]);
        k = Integer.parseInt(inputs[2]);

        map = new int[n][m];
        visit = new boolean[n][m];
        count = 0;

        for (int i = 0; i < k; i++) {
            inputs = br.readLine().split(" ");
            map[Integer.parseInt(inputs[1])][Integer.parseInt(inputs[0])] = 1;
        }
    }
}