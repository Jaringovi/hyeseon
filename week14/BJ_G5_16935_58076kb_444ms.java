package week14;

import java.io.*;

/** 16935. 배열 돌리기 3
 * 메모리 58076 kb
 * 시간 444 ms
 * 구현, 시뮬레이션
 */
public class BJ_G5_16935_58076kb_444ms {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static int R;
    static int[] commands;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        output();
    }

    private static void init() throws IOException {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        R = Integer.parseInt(inputs[2]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        commands = new int[R];
        inputs = br.readLine().split(" ");
        for (int i = 0; i < R; i++) {
            commands[i] = Integer.parseInt(inputs[i]);
        }
    }

    private static void solve() {
        for (int command : commands) {
            switch(command) {
                case 1:
                    switchUpAndDown();
                    break;
                case 2:
                    rotateLeft();
                    switchUpAndDown();
                    rotateRight();
                    break;
                case 3:
                    rotateRight();
                    break;
                case 4:
                    rotateLeft();
                    break;
                case 5:
                    rotateArrRight();
                    break;
                case 6:
                    rotateArrLeft();
                    break;
            }
        }
    }

    private static void switchUpAndDown() {
        for (int j = 0; j < map[0].length; j++) {
            for (int i = 0; i < map.length / 2; i++) {
                int temp = map[i][j];
                map[i][j] = map[map.length - 1 - i][j];
                map[map.length - 1 - i][j] = temp;
            }
        }
    }

    private static void rotateRight() {
        int[][] temp = new int[map[0].length][map.length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                temp[j][map.length - 1 - i] = map[i][j];
            }
        }
        map = temp;
    }

    private static void rotateLeft() {
        int[][] temp = new int[map[0].length][map.length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                temp[map[0].length - 1 - j][i] = map[i][j];
            }
        }
        map = temp;
    }

    private static void rotateArrRight() {
        int[][] temp = new int[map.length / 2][map[0].length / 2];
        // 4번 부분배열 temp 담기
        for (int i = map.length / 2; i < map.length; i++) {
            for (int j = 0; j < map[0].length / 2; j++) {
                temp[i - map.length / 2][j] = map[i][j];
            }
        }

        // 4번에 3번 삽입
        for (int i = map.length / 2; i < map.length; i++) {
            for (int j = map[0].length / 2; j < map[0].length; j++) {
                map[i][j - map[0].length / 2] = map[i][j];
            }
        }

        // 3번에 2번 삽입
        for (int i = 0; i < map.length / 2; i++) {
            for (int j = map[0].length / 2; j < map[0].length; j++) {
                map[i + map.length / 2][j] = map[i][j];
            }
        }

        // 2번에 1번 삽입
        for (int i = 0; i < map.length / 2; i++) {
            for (int j = 0; j < map[0].length / 2; j++) {
                map[i][j + map[0].length / 2] = map[i][j];
            }
        }

        // 1번에 temp 담기
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    private static void rotateArrLeft() {
        int[][] temp = new int[map.length / 2][map[0].length / 2];
        // 1번 부분배열 temp 담기
        for (int i = 0; i < map.length / 2; i++) {
            for (int j = 0; j < map[0].length / 2; j++) {
                temp[i][j] = map[i][j];
            }
        }

        // 1번에 2번 삽입
        for (int i = 0; i < map.length / 2; i++) {
            for (int j = map[0].length / 2; j < map[0].length; j++) {
                map[i][j - map[0].length / 2] = map[i][j];
            }
        }

        // 2번에 3번 삽입하기
        for (int i = map.length / 2; i < map.length; i++) {
            for (int j = map[0].length / 2; j < map[0].length; j++) {
                map[i - map.length / 2][j] = map[i][j];
            }
        }

        // 3번에 4번 삽입하기
        for (int i = map.length / 2; i < map.length; i++) {
            for (int j = 0; j < map[0].length / 2; j++) {
                map[i][j + map[0].length / 2] = map[i][j];
            }
        }

        // 4번에 temp 삽입하기
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                map[i + map.length / 2][j] = temp[i][j];
            }
        }
    }

    private static void output() {
        StringBuilder sb = new StringBuilder();
        for(int[] row : map) {
            for(int v : row) {
                sb.append(v).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
