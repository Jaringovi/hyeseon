package week2;

import java.io.*;
import java.util.*;

/*
16918. 봄버맨
 */
public class BOJ_16918 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int r;
    static int c;
    static int n;
    static String[][] map;
    static int time = 1;
    static boolean[][] visit;
    static List<MyPoint> bombs = new ArrayList<>();
    static List<MyPoint> bombRange = new ArrayList<>();


    public static void main(String[] args) throws Exception {
        input();

        while(time <= n) {
            if (time % 2 != 0) {
                bomb();
                findBomb();
                installBomb();
            } else {
                fillBombAll();
            }
            time++;
        }

        output();
    }

    private static void output() {
        StringBuilder sb = new StringBuilder();
        for(String[] s : map) {
            for(String ss : s) {
                sb.append(ss);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void installBomb() {
        int[] dy = {-1, 0, 1, 0}; //상우하좌
        int[] dx = {0, 1, 0, -1};

        for(MyPoint mp : bombs) {
            bombRange.add(new MyPoint(mp.y, mp.x));

            for (int i = 0; i < 4; i++) {
                int ny = mp.y + dy[i];
                int nx = mp.x + dx[i];

                if(!isRange(ny, nx)) {
                    continue;
                }

                bombRange.add(new MyPoint(ny, nx));
            }
        }
    }

    private static boolean isRange(int y, int x) {
        return y >= 0 && y < r && x >= 0 && x < c;
    }

    private static void findBomb() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(map[i][j].equals("O")) {
                    bombs.add(new MyPoint(i, j));
                }
            }
        }
    }

    private static void bomb() {
        for(MyPoint mp : bombRange) {
            if(map[mp.y][mp.x].equals("O")) {
                map[mp.y][mp.x] = ".";
            }
        }
        bombs.clear();
        bombRange.clear();
    }

    private static void fillBombAll() {
        for(String[] s : map) {
            Arrays.fill(s, "O");
        }
    }

    private static void input() throws Exception {
        String[] temp = br.readLine().split(" ");
        r = Integer.parseInt(temp[0]);
        c = Integer.parseInt(temp[1]);
        n = Integer.parseInt(temp[2]);

        map = new String[r][c];
        visit = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < c; j++) {
                map[i][j] = temp[j];
            }
        }
    }
}

class MyPoint {
    int y;
    int x;

    MyPoint(int y, int x) {
        this.y = y;
        this.x = x;
    }
}