package week9;

import java.io.*;
import java.util.*;

/** 13549. 숨바꼭질3
 * 메모리 23632 kb
 * 시간 204 ms
 *
 * 다익스트라
 */
public class BJ_G5_13549 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int K;
	static int[] dist;
	static int[][] move = {{1, 1}, {-1, 1}, {2, 0}};

	static int result;

	public static void main(String[] args) throws Exception {
		init();
		solve(N);
		output();
	}

	public static void output() {
		System.out.println(result);
	}

	public static void solve(int n) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(n, 0));
		dist[n] = 0;

		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int node = cur.node;
			int cost = cur.cost;

			int nextNode;
			int nextCost;
			for (int i = 0; i < move.length; i++) {
				if (i == 2) {
					nextNode = node * move[i][0];
				}else {
					nextNode = node + move[i][0];
				}
				nextCost = cost + move[i][1];

				if(isRange(nextNode) && dist[nextNode] > nextCost) {
					dist[nextNode] = nextCost;
					pq.add(new Node(nextNode, nextCost));
				}
			}
		}

		result = dist[K];
	}

	public static boolean isRange(int node) {
		return node >= 0 && node <= 100_000;
	}

	public static void init() throws Exception {
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		K = Integer.parseInt(inputs[1]);

		dist = new int[100_001];
		Arrays.fill(dist, Integer.MAX_VALUE);

		result = 0;
	}

	static class Node implements Comparable<Node>{
		int node;
		int cost;

		Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}
