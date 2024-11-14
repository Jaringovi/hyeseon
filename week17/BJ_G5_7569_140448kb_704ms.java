package week17;

import java.io.*;
import java.util.*;

/** 7569. 토마토
 * 메모리 140448	kb
 * 시간 704 ms
 * bfs
 *
 * [풀이]
 * 1. 3차원 배열로 저장된 토마토 상태를 읽고, 이미 익은 토마토를 큐에 넣는다.
 * 2. 큐에서 토마토를 하나씩 꺼내며 6방향으로 인접한 익지 않은 토마토를 방문하여 익히고 날짜 +1을 한다.
 * 3. bfs 종료 후, 모든 토마토가 익었으면 최대 날짜를 출력하고 하나라도 익지 않은 토마토가 있으면 -1을 출력한다.
 * + 3차원 배열을 큐에 저장할 수 없기 때문에 클래스를 따로 만들어야 한다.
 */
public class BJ_G5_7569_140448kb_704ms {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int result;

    static int H, M, N;
    static int[][][] map;
    static Queue<PointZYX> queue;
    static int[][] dzyx = {{-1, 0, 0}, {1, 0, 0}, {0, 0, -1}, {0, 0, 1}, {0, -1, 0}, {0, 1, 0}}; // 위, 아래, 왼, 오, 앞, 뒤

    static class PointZYX {
        int z;
        int y;
        int x;

        PointZYX(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        solve();
        output();
    }

    private static void init() throws Exception {
        String[] inputs = br.readLine().split(" ");
        M = Integer.parseInt(inputs[0]);
        N = Integer.parseInt(inputs[1]);
        H = Integer.parseInt(inputs[2]);

        map = new int[H][N][M];
        queue = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                inputs = br.readLine().split(" ");
                for (int k = 0; k < M; k++) {
                    map[i][j][k] = Integer.parseInt(inputs[k]);
                    if(map[i][j][k] == 1) {
                        queue.add(new PointZYX(i, j, k));
                    }
                }
            }
        }

        result = Integer.MIN_VALUE;
    }

    private static void solve() {
        bfs();
        findMaxDay();
    }

    private static void bfs() {
        while(!queue.isEmpty()) {
            PointZYX cur = queue.poll();
            int cz = cur.z;
            int cy = cur.y;
            int cx = cur.x;

            for (int i = 0; i < dzyx.length; i++) {
                int nz = cz + dzyx[i][0];
                int ny = cy + dzyx[i][1];
                int nx = cx + dzyx[i][2];

                if (!canGo(nz, ny, nx)) {
                    continue;
                }

                queue.add(new PointZYX(nz, ny, nx));
                map[nz][ny][nx] = map[cz][cy][cx] + 1;
            }
        }
    }

    private static boolean canGo(int z, int y, int x) {
        // 범위 안에 들어온다면
        if(z < 0 || y < 0 || x < 0 || z >= H || y >= N || x >= M) {
            return false;
        }
        // 익은 토마토라면 true
        return map[z][y][x] == 0;
    }

    private static void findMaxDay() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if(map[i][j][k] == 0) {
                        result = -1;
                        return;
                    }
                    result = Math.max(result, map[i][j][k]);
                }
            }
        }
    }

    private static void output() {
        // 토마토의 시작 숫자는 1, 최소 일수를 구하려면 -1을 해야한다.
        if(result == -1) {
            System.out.println(result);
        }else {
            System.out.println(result - 1);
        }
    }
}
