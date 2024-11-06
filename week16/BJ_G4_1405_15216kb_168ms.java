package week16;

import java.io.*;

/** 1405. 미친 로봇
 * 메모리 15216 kb
 * 시간 168 ms
 * 백트래킹
 *
 * [풀이]
 * 경로가 단순하다 => 같은 곳을 방문하지 않는다. => 방문체크 배열 필요
 * 이동 경로가 단순할 확률 = 각 경로의 모든 확률 누적곱을 누적한 것
 * 방문체크 범위: 0 <= N <= 14 이므로 30 x 30 범위의 공간에서 (15, 15)에서 시작하면 음수가 나오지 않는다.
 */
public class BJ_G4_1405_15216kb_168ms {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static double[] percents;
    static boolean[][] visit;
    static int[][] dxy = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // 동서남북
    static double result;

    public static void main(String[] args) throws IOException {
        init();
        solve(0, 1, 15, 15);
        output();
    }

    private static void init() throws IOException {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);

        visit = new boolean[30][30];
        percents = new double[4];
        for (int i = 1; i < inputs.length; i++) {
            percents[i - 1] = Integer.parseInt(inputs[i]) * 0.01;
        }
    }

    private static void solve(int cnt, double sum, int y, int x) {
        if(cnt == N) {
            result += sum;
            return;
        }

        visit[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = dxy[i][0] + y;
            int nx = dxy[i][1] + x;

            if (!isRange(ny, nx)) continue;

            if (!visit[ny][nx]) {
                visit[ny][nx] = true;
                solve(cnt + 1, sum * percents[i], ny, nx);
                visit[ny][nx] = false;
            }
        }
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && x >= 0 && y < 30 && x < 30;
    }

    private static void output() {
        System.out.println(result);
    }
}
