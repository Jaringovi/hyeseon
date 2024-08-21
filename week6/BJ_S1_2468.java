package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/** 2468, 안전영역
 * 메모리: 36468 kb
 * 속도: 260 ms
 * 69%에서 틀렸습니다.
 * -> 이유: "아무 지역도 물에 잠기지 않을 수도 있다." 즉, 비의 양이 0인 경우도 생각해야 했다.
 */
public class BJ_S1_2468 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n;
	static int[][] map;
	static boolean[][] visit;
	static int result = Integer.MIN_VALUE;
	static int count;
	static int[][] dxy = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상우하좌

	public static void main(String[] args) throws Exception {
		init();
		solve();
		output();
	}

	private static void output() {
		System.out.println(result);
	}

	private static void solve() {
		for (int limit = 0; limit <= 100; limit++) {
			visit = new boolean[n][n];
			count = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!canGo(i, j, limit)) {
						continue;
					}
					bfs(i, j, limit);
					count++;
				}
			}

			result = Math.max(result, count);
		}

		if(result == Integer.MIN_VALUE) {
			result = 0;
		}
	}

	public static void bfs(int y, int x, int limit) {
		Queue<MyPoint> queue = new ArrayDeque<>();
		queue.add(new MyPoint(y, x));
		visit[y][x] = true;

		while(!queue.isEmpty()) {
			MyPoint cur = queue.poll();
			int curY = cur.y;
			int curX = cur.x;

			for (int i = 0; i < 4; i++) {
				int ny = dxy[i][0] + curY;
				int nx = dxy[i][1] + curX;

				if (!canGo(ny, nx, limit)) {
					continue;
				}

				visit[ny][nx] = true;
				queue.add(new MyPoint(ny, nx));
			}
		}
	}

	public static boolean canGo(int y, int x, int limit) {
		return isRange(y, x) && map[y][x] > limit && !visit[y][x];
	}

	public static boolean isRange(int y, int x) {
		return y >= 0 && x >= 0 && y < n && x < n;
	}

	public static void init() throws Exception {
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visit = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			String[] inputs = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
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
