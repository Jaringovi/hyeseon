package week13;

import java.io.*;
import java.util.*;

/** 21610.마법사 상어와 비바라기
 * 메모리 25836 kb
 * 시간 496 ms
 *
 * 시뮬레이션
 *
 * [풀이]
 * 명령어 배열 초기화 후 다름 프로세스를 반복한다.
 * 1. 구름 이동
 * 2. 비 내림
 * 3. 물 복사
 * 4. 구름 만들기
 * 마지막에 만들어진 물 바구니의 값을 누적해서 출력한다.
 *
 * [key]
 * map을 벗어나면 반대쪽에서 이어지게 만들어야 한다.
 * ny = (ny % N + N) % N; 이렇게 계산해야 범위를 벗어나도 0이상 5미만의 수가 나온다.
 */
public class BJ_G5_21610_25836kb_496ms {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static int[][] map;
    static int[][] commands;
    static List<int[]> clouds;
    static int[][] dxy = {{}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static int[][] diagonal = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    static int result;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        output();
    }

    private static void init() throws IOException {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        commands = new int[M][2];
        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            commands[i][0] = Integer.parseInt(inputs[0]);
            commands[i][1] = Integer.parseInt(inputs[1]);
        }

        clouds = new ArrayList<>();
        clouds.add(new int[]{N - 1, 0});
        clouds.add(new int[]{N - 1, 1});
        clouds.add(new int[]{N - 2, 0});
        clouds.add(new int[]{N - 2, 1});
    }

    private static void solve() {
        for (int[] command : commands) {
            move(command);
            rain();
            copy();
            make();
        }
        calculate();
    }

    private static void move(int[] command) {
        List<int[]> movedCloud = new ArrayList<>();
        int d = command[0];
        int num = command[1];

        for(int[] cloud : clouds) {
            int ny = cloud[0] + dxy[d][0] * num;
            int nx = cloud[1] + dxy[d][1] * num;

            ny = (ny % N + N) % N;
            nx = (nx % N + N) % N;

            movedCloud.add(new int[]{ny, nx});
        }
        clouds = movedCloud;
    }

    private static void rain() {
        for (int[] cloud : clouds) {
            map[cloud[0]][cloud[1]] += 1;
        }
    }

    private static void copy() {
        for(int[] cloud : clouds) {
            int count = 0;
            for (int i = 0; i < 4; i++) {
                int ny = cloud[0] + diagonal[i][0];
                int nx = cloud[1] + diagonal[i][1];

                if (!isRange(ny, nx)) {
                    continue;
                }

                if(map[ny][nx] > 0) {
                    count++;
                }
            }
            map[cloud[0]][cloud[1]] += count;
        }
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    private static void make() {
        List<int[]> makedCloud = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] >= 2 && !isCurrentCloud(i, j)) {
                    map[i][j] -= 2;
                    makedCloud.add(new int[]{i, j});
                }
            }
        }
        clouds = makedCloud;
    }

    private static boolean isCurrentCloud(int y, int x) {
        for (int[] cloud : clouds) {
            if (cloud[0] == y && cloud[1] == x) {
                return true;
            }
        }
        return false;
    }

    private static void calculate() {
        result = 0;
        for (int[] m : map) {
            for (int v : m) {
                result += v;
            }
        }
    }

    private static void output() {
        System.out.println(result);
    }
}
