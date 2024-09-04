package week8;

import java.io.*;
import java.util.*;

/** 2252. 줄 세우기
 * 메모리 56144 kb
 * 시간 512 ms
 *
 * 위상정렬
 */
public class BJ_G3_2252 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M;
	static List<List<Integer>> graph;
	static int[] indegree;
	static List<Integer> results;

	public static void main(String[] args) throws Exception {
		init();
		solve();
		output();
	}

	public static void output() throws Exception {
		for(int v : results) {
			sb.append(v).append(" ");
		}
		System.out.println(sb);
		br.close();
	}

	public static void solve() {
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				queue.add(i);
			}
		}

		while(!queue.isEmpty()) {
			int cur = queue.poll();
			results.add(cur);

			for(int next : graph.get(cur)) {
				indegree[next] -= 1;
				if(indegree[next] == 0) {
					queue.add(next);
				}
			}
		}
	}

	public static void init() throws Exception {
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		indegree = new int[N + 1];
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for(int i = 0; i < M; i++) {
			inputs = br.readLine().split(" ");
			int a = Integer.parseInt(inputs[0]);
			int b = Integer.parseInt(inputs[1]);

			graph.get(a).add(b);

			indegree[b] += 1;
		}

		results = new ArrayList<>();
	}
}
