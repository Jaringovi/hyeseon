package week17;

import java.io.*;

/** 2571. 색종이
 * 메모리 14316 kb
 * 시간 112 ms
 * 구현
 *
 * [풀이]
 * 1. 입력받은 색종이 영역을 도화지에 1로 표시한다.
 * 2. 각 열에서 세로로 색종이 영역이 이어지면 높이를 누적하여 직사각형 높이를 기록한다.
 * 3. 각 행을 기준으로 가로로 확장하며 직사각형 넓이를 계산하고 최대값을 갱신한다.
 * + 히스토그램에서 최대 직사각형 구하는 알고리즘을 응용하면 된다.
 */
public class BJ_G3_2571_14316kb_112ms {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int result;

    static int N;
    static int[][] paper;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        output();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        paper = new int[101][101];

        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            int y = Integer.parseInt(inputs[0]);
            int x = Integer.parseInt(inputs[1]);

            // 색종이 영역 1로 표시
            for (int j = y; j < y + 10; j++) {
                for (int k = x; k < x + 10; k++) {
                    paper[j][k] = 1;
                }
            }
        }

        // 직사각형 최대 넓이를 구하기 위한 변수
        result = Integer.MIN_VALUE;
    }

    private static void solve() {
        countLength();
        calculateMaxRectangle();
    }

    // 세로 줄에 색종이가 이어지면 값을 누적한다.
    private static void countLength() {
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 100; j++) {
                if (paper[i][j] != 0 && paper[i + 1][j] != 0) {
                    paper[i + 1][j] = paper[i][j] + 1;
                }
            }
        }
    }

    // 현재 좌표 기준 가로로 직사각형을 확장해 나가면서 max값을 갱신한다.
    private static void calculateMaxRectangle() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int h = 100;
                for (int k = j; k < 100; k++) {
                    h = Math.min(h, paper[i][k]);
                    if(h == 0) break;
                    result = Math.max(result, h * (k - j + 1));
                }
            }
        }
    }

    private static void output() {
        System.out.println(result);
    }
}
