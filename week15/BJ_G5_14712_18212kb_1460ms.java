package week15;

import java.io.*;

/** 14712. 넴모넴모 (Easy)
 * 메모리 18212 kb
 * 시간 1460 ms
 * 백트래킹
 *
 * [풀이]
 * 넴모가 2x2 형태로 배치하는 것을 제외한 가능한 모든 배치를 세는 문제
 * 정답 코드를 보고 이해 후 다시 풀었음
 */
public class BJ_G5_14712_18212kb_1460ms {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static boolean[][] nemo;
    static int result;

    public static void main(String[] args) throws IOException {
        init();
        backtracking(0);
        output();
    }

    private static void init() throws IOException {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        nemo = new boolean[N][M];
        result = 0;
    }

    private static void backtracking(int depth) {
        // 모든 칸에 대해 넴모를 놓을지 말지를 결정했을 때 탐색 종료
        if (depth == N * M) {
            // 2x2 형태가 아닌 배치라면 count++
            if(!canBomb()) {
                result++;
            }
            return;
        }

        int y = depth / M;
        int x = depth % M;

        // 현재 위치를 선택한 경우
        nemo[y][x] = true;
        backtracking(depth + 1);

        // 현재 위치를 선택하지 않은 경우
        nemo[y][x] = false;
        backtracking(depth + 1);
    }

    // 2 x 2 사각형을 이루면 넴모를 없앤다.
    private static boolean canBomb() {
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                if (nemo[i][j] && nemo[i + 1][j] && nemo[i][j + 1] && nemo[i + 1][j + 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void output() {
        System.out.println(result);
    }
}
