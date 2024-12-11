package week18;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/** 2615. 오목
 * 메모리 14232 kb
 * 시간 104 ms
 * 구현
 *
 * [풀이]
 * 1. 이중 for문 탐색
 * 2. 0이 아니라면 그 위치부터
 *  - 북동, 동, 남동, 동 탐색
 *  - 앞 뒤로 같은 색의 돌이 없으면(6개가 연속) return true
 * 3. 이긴 돌이 있으면 그 위치도 출력
 *
 * + 메소드 재사용 가능한 버전
 */
public class BJ_S1_2615_advanced {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[][] board;
	static int winner;
	static int[] yx;

	public static void main(String[] args) throws Exception {
		init();
		solve();
		output();
	}

	public static void init() throws Exception {
		board = new int[20][20];

		for (int i = 1; i < 20; i++) {
			String[] inputs = br.readLine().split(" ");

			for (int j = 1; j < 20; j++) {
				board[i][j] = Integer.parseInt(inputs[j - 1]);
			}
		}
	}

	private static void solve() {
		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				if (board[i][j] == 0) {
					continue;
				}

				int color = board[i][j];

				if (isWin(color, i, j)) {
					winner = color;
					yx = new int[]{i, j};
					return;
				}
			}
		}
	}

	private static boolean isWin(int color, int y, int x) {
		return checkDirection(color, y, x, 0, 1)   // 동쪽
			|| checkDirection(color, y, x, 1, 1)   // 남동쪽
			|| checkDirection(color, y, x, 1, 0)   // 남쪽
			|| checkDirection(color, y, x, -1, 1); // 북동쪽
	}

	private static boolean checkDirection(int color, int y, int x, int dy, int dx) {
		for (int i = 1; i < 5; i++) {
			y += dy;
			x += dx;

			if (!isRange(y, x) || board[y][x] != color) {
				return false;
			}
		}

		// 6개 이상인지 확인 (앞뒤로 추가 돌이 있는지 체크)
		return (!isRange(y + dy, x + dx) || board[y + dy][x + dx] != color) &&
			(!isRange(y - 5 * dy, x - 5 * dx) || board[y - 5 * dy][x - 5 * dx] != color);
	}

	private static boolean isRange(int y, int x) {
		return y >= 1 && x >= 1 && y < 20 && x < 20;
	}

	private static void output() {
		sb.append(winner).append("\n");
		if (winner != 0) {
			sb.append(yx[0]).append(" ").append(yx[1]).append("\n");
		}
		System.out.println(sb);
	}
}
