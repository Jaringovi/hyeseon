package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
7562. 나이트의 이동
16%에서 틀렸습니다.
-> min 값을 비교하지 않고 이전값 + 1만 하니까 통과함 
 */
public class BOJ_7562 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int t;
    static int n;
    static int[][] map;
    static boolean[][] visit;
    static int[] start = new int[2];
    static int[] target = new int[2];
    static int[][] dxy = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}}; // 1시 ~ 11시 방향

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            init();
            if (start[0] == target[0] && start[1] == target[1]) {
                System.out.println(0);
                continue;
            }
            bfs(start[0], start[1]);
            System.out.println(map[target[0]][target[1]]);
        }
    }

    private static void bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        visit[y][x] = true;
        queue.add(new int[]{y, x});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cy = cur[0];
            int cx = cur[1];

            for (int i = 0; i < dxy.length; i++) {
                int ny = cy + dxy[i][0];
                int nx = cx + dxy[i][1];

                if(!isRange(ny, nx) || visit[ny][nx]) {
                    continue;
                }

                visit[ny][nx] = true;
                queue.add(new int[]{ny, nx});
                map[ny][nx] = map[cy][cx] + 1;
            }
        }
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        start[0] = Integer.parseInt(inputs[0]);
        start[1] = Integer.parseInt(inputs[1]);

        inputs = br.readLine().split(" ");
        target[0] = Integer.parseInt(inputs[0]);
        target[1] = Integer.parseInt(inputs[1]);

        map = new int[n][n];
        visit = new boolean[n][n];
    }
}