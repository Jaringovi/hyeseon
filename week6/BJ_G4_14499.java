package week6;

import java.io.*;
import java.util.*;

/** 14499 주사위 굴리기
 * 메모리 14508 kb
 * 시간 108 ms
 * 구현
 *
 * 방향 명령어마다
 * isRange 체크
 * 주사위 굴리기
 * 현재위치 값과 주사위의 값의 비교해서 복사
 * 상단값 출력
 */
public class BJ_G4_14499 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int R;
    static int C;
    static int startY;
    static int startX;
    static int commandN;
    static int[][] map;
    static int[] commands;
    static int[][] dice;

    public static void main(String[] args) throws Exception {
        init();
        solve();
        System.out.println(sb);
    }

    public static void output(int top) {
        sb.append(top).append("\n");
    }

    public static void solve() {
        int moveY = startY;
        int moveX = startX;

        for(int command : commands) {
            switch(command) {
                case 1 : // 동쪽
                    moveX++;
                    if(!isRange(moveY, moveX)) {
                        moveX--;
                        continue;
                    }

                    rollDiceToE();
                    copy(moveY, moveX);
                    output(dice[1][1]);
                    break;
                case 2 : // 서쪽
                    moveX--;
                    if(!isRange(moveY, moveX)) {
                        moveX++;
                        continue;
                    }

                    rollDiceToW();
                    copy(moveY, moveX);
                    output(dice[1][1]);
                    break;
                case 3 : // 북쪽
                    moveY--;
                    if(!isRange(moveY, moveX)) {
                        moveY++;
                        continue;
                    }

                    rollDiceToN();
                    copy(moveY, moveX);
                    output(dice[1][1]);
                    break;
                case 4 : // 남쪽
                    moveY++;
                    if(!isRange(moveY, moveX)) {
                        moveY--;
                        continue;
                    }

                    rollDiceToS();
                    copy(moveY, moveX);
                    output(dice[1][1]);
                    break;
            }
        }
    }

    public static void copy(int moveY, int moveX) {
        if(map[moveY][moveX] == 0) {
            map[moveY][moveX] = dice[3][1];
        }else {
            dice[3][1] = map[moveY][moveX];
            map[moveY][moveX] = 0;
        }
    }

    public static void rollDiceToE() {
        int temp = dice[3][1];
        dice[3][1] = dice[1][0];
        dice[1][0] = dice[1][1];
        dice[1][1] = dice[1][2];
        dice[1][2] = temp;
    }

    public static void rollDiceToW() {
        int temp = dice[1][2];
        dice[1][2] = dice[1][1];
        dice[1][1] = dice[1][0];
        dice[1][0] = dice[3][1];
        dice[3][1] = temp;
    }

    public static void rollDiceToN() {
        int temp = dice[0][1];
        for (int i = 1; i < 4; i++) {
            dice[i - 1][1] = dice[i][1];
        }
        dice[3][1] = temp;
    }

    public static void rollDiceToS() {
        int temp = dice[3][1];
        for (int i = 3; i > 0; i--) {
            dice[i][1] = dice[i - 1][1];
        }
        dice[0][1] = temp;
    }

    public static boolean isRange(int y, int x) {
        return y >= 0 && x >= 0 && y < R && x < C;
    }

    public static void init() throws Exception {
        String[] inputs = br.readLine().split(" ");
        R = Integer.parseInt(inputs[0]);
        C = Integer.parseInt(inputs[1]);
        startY = Integer.parseInt(inputs[2]);
        startX = Integer.parseInt(inputs[3]);
        commandN = Integer.parseInt(inputs[4]);

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        commands = new int[commandN];
        inputs = br.readLine().split(" ");
        for (int i = 0; i < commandN; i++) {
            commands[i] = Integer.parseInt(inputs[i]);
        }

        makeDice();
    }

    public static void makeDice() {
        dice = new int[4][3];
        dice[0][1] = 0;
        dice[1][0] = 0;
        dice[1][1] = 0;
        dice[1][2] = 0;
        dice[2][1] = 0;
        dice[3][1] = 0;
    }
}
