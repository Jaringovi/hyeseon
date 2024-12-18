package week19;

import java.io.*;
import java.util.*;

/** 2660. 회장뽑기
 * 메모리 14268 kb
 * 시간 104 ms
 * 플로이드-워셜
 *
 * [풀이]
 * 1. distance[][] 인접행렬 초기화
 * 2. 촐발, 도착 노드 가중치 표시
 * 3. 3중 for문으로 distance 배열 갱신
 * 4. 각 노드에서 큰 값 구하고 그 값에 해당하는 노드를 리스트 형해로 만들어 map에 저장
 * 5. 큰 값 중 제일 작은 값을 구하고 그 값에 해당하는 value를 오름차순 정렬
 * 6. 제일 작은 값, value의 사이즈, 오름차순 정렬된 value를 출력
 */
public class BJ_G5_2660_14268kb_104ms {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[][] distance;

	static Map<Integer, List<Integer>> map;
	static int min;

	public static void main(String[] args) throws IOException {
		init();
		solve();
		output();
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		distance = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					distance[i][j] = 0;
				}else {
					distance[i][j] = 100_001;
				}
			}
		}

		while (true) {
			String[] inputs = br.readLine().split(" ");
			int a = Integer.parseInt(inputs[0]);
			int b = Integer.parseInt(inputs[1]);

			if (a == -1 && b == -1) {
				break;
			}

			distance[a][b] = 1;
			distance[b][a] = 1;
		}
	}

	private static void solve() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}

		map = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			Arrays.sort(distance[i]);
			int max = distance[i][distance[i].length - 1];
			List<Integer> list = map.getOrDefault(max, new ArrayList<>());
			list.add(i);
			map.put(max, list);
		}

		min = Integer.MAX_VALUE;
		for (int key : map.keySet()) {
			min = Math.min(key, min);
		}

		List<Integer> results = map.get(min);
		Collections.sort(results);
	}

	private static void output() {
		sb.append(min).append(" ").append(map.get(min).size()).append("\n");
		for (int v : map.get(min)) {
			sb.append(v).append(" ");
		}
		System.out.println(sb);
	}
}
