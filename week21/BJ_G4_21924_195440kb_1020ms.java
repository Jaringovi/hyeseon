package week21;

import java.io.*;
import java.util.*;

/** 21924. 도시 건설
 * 메모리 195440	kb
 * 시간 1020 ms
 * MST(크루스칼)
 */
public class BJ_G4_21924_195440kb_1020ms {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static long total;
	static int[] parents;
	static PriorityQueue<Edge> pq;
	static long result;

	public static void main(String[] args) throws IOException {
		init();
		solve();
		output();
	}

	static class Edge implements Comparable<Edge> {
		int left;
		int right;
		int weight;

		public Edge(int left, int right, int weight) {
			this.left = left;
			this.right = right;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	private static void init() throws IOException {
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		total = 0;
		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}

		pq = new PriorityQueue<>();
		for (int i = 1; i <= M; i++) {
			inputs = br.readLine().split(" ");
			int a = Integer.parseInt(inputs[0]);
			int b = Integer.parseInt(inputs[1]);
			int w = Integer.parseInt(inputs[2]);

			total += w;
			pq.add(new Edge(a, b, w));
		}
	}

	private static void solve() {
		int count = 0;
		long sum = 0;
		while (!pq.isEmpty()) {
			Edge poll = pq.poll();

			int pl = poll.left;
			int pr = poll.right;

			if (merged(pl, pr)) {
				sum += poll.weight;
				count++;
				if (count == N - 1) {
					result = total - sum;
					return;
				}
			}
		}
	}

	private static boolean merged(int pl, int pr) {
		int rootPl = findParent(pl);
		int rootPr = findParent(pr);
		if (rootPl == rootPr) {
			return false;
		} else {
			parents[rootPl] = rootPr;
			return true;
		}
	}

	private static int findParent(int child) {
		if (parents[child] == child) {
			return child;
		} else {
			return parents[child] = findParent(parents[child]);
		}
	}

	private static void output() {
		if (result > 0) {
			System.out.println(result);
		}else {
			System.out.println(-1);
		}
	}
}
