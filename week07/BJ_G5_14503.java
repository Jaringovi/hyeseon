package week07;

import java.io.*;

/**
 * 14503. 로봇청소기
 * 메모리 14392 kb
 * 시간 104 ms
 *
 * 구현, 시뮬레이션
 */
public class BJ_G5_14503 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static int[][] map;
    static Robot robot;
    static int startY;
    static int startX;
    static boolean[][] cleaning;
    static int result;
    static int[][] dxy = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};


    public static void main(String[] args) throws Exception {
        init();
        solve();
        output();
    }
    
    public static void output() {
        System.out.println(result);
    }

    public static void solve() {

        while(true) {
            // 현재 칸이 청소되지 않은 경우 현재 칸을 청소한다.
            cleanThisPoint();

            // 현재 칸의 주변 4칸의 청소 상태 확인
            boolean isDirty = findCleaningStatus();


            // 주변에 청소되지 않은 칸이 없는 경우
            if(!isDirty) {

                int ny = robot.y + dxy[robot.d][0] * -1;
                int nx = robot.x + dxy[robot.d][1] * -1;

                // 바라보는 방향의 뒤쪽 칸이 범위를 넘었거나 벽이라면 작동을 멈춘다.
                if(!isRange(ny, nx) || map[ny][nx] == 1) {
                    break;
                }

                // 바라보는 방향을 유지한 채로 후진한다.
                robot.y = ny;
                robot.x = nx;
            }
            // 주변에 청소된 칸이 있는 경우
            else {
                // 반시계 방향으로 90% 회전한다.
                for (int i = 0; i < 4; i++) {
                    robot.d = (robot.d + 3) % 4;

                    int ny = robot.y + dxy[robot.d][0];
                    int nx = robot.x + dxy[robot.d][1];

                    if (!isRange(ny, nx) || map[ny][nx] == 1 || cleaning[ny][nx]) {
                        continue;
                    }

                    // 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
                    robot.y += dxy[robot.d][0];
                    robot.x += dxy[robot.d][1];
                    break;
                }
            }
        }

        findCleaningCount();
    }

    private static void findCleaningCount() {
        result = 0;
        for(boolean[] v : cleaning) {
            for(boolean vv : v) {
                if(vv) {
                    result++;
                }
            }
        }
    }

    private static boolean findCleaningStatus() {
        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            int ny = robot.y + dxy[i][0];
            int nx = robot.x + dxy[i][1];

            if (!isRange(ny, nx) || cleaning[ny][nx]) {
                continue;
            }

            // 청소되지 않는 빈 칸이 있는 경우
            if (map[ny][nx] == 0) {
                flag = true;
            }
        }
        return flag;
    }

    private static void cleanThisPoint() {
        if(map[robot.y][robot.x] == 0) {
            cleaning[robot.y][robot.x] = true;
        }
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 & y < N && x >= 0 & x < M;
    }

    public static void init() throws Exception {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        inputs = br.readLine().split(" ");
        startY = Integer.parseInt(inputs[0]);
        startX = Integer.parseInt(inputs[1]);
        int d = Integer.parseInt(inputs[2]);

        robot = new Robot(startY, startX, d);

        map = new int[N][M];
        cleaning = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(inputs[j]);
            }
        }
    }
}

class Robot {
    int y;
    int x;
    int d;

    Robot(int y, int x, int d) {
        this.y = y;
        this.x = x;
        this.d = d;
    }
}
