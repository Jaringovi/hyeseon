package week14;

import java.io.*;
import java.util.*;

/** 1034. 램프
 * 메모리 15216 kb
 * 시간 140 ms
 * 완전탐색
 *
 * [풀이]
 * 순열, dp로 접근 시도했으나 실패함
 * 해답 보고 이해 후 다시 풀었음
 *
 * 1. 행에 존재하는 0의 개수가 K보다 크면 그 행은 불이 들어오지 않는다.
 * 2. 행에 존재하는 0의 개수와 K가 홀수/짝수로 같고 그 행과 똑같이 생긴 행들은 불이 들어온다.
 * 3. 볼이 들어오는 행의 개수를 max로 갱신한다.
 */
public class BJ_G4_1034_15216kb_140ms {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, K;
    static int[][] map;
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

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        K = Integer.parseInt(br.readLine());
    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0) {
                    count++;
                }
            }

            int sameRowcnt = 0;
            if (count <= K && count % 2 == K % 2) {
                for (int j = 0; j < N; j++) {
                    if(Arrays.equals(map[i], map[j])) {
                        sameRowcnt++;
                    }
                }
            }
            result = Math.max(sameRowcnt, result);
        }
    }

    private static void output() {
        System.out.println(result);
    }
}
