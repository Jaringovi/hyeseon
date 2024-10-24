package week08;

import java.io.*;
import java.util.*;

/** 1197. 최소 스패닝 트리
 * 메모리 60276 kb
 * 시간 728 ms
 *
 * 크루스칼 알고리즘
 */
public class BJ_G4_1197 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int V;
    static int E;
    static Edge[] edges;
    static int[] parents;
    static long result;

    public static void main(String[] args) throws Exception {
        init();
        solve();
        output();
    }

    public static void output() {
        System.out.println(result);
    }

    public static void solve() {
        make();

        Arrays.sort(edges);

        int count = 0;
        for (Edge e : edges) {
            if(union(e.start, e.end)) {
                result += e.weight;
                if (++count == V - 1) {
                    break;
                }
            }
        }
    }

    public static void make() {
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }

    public static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if(aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = aRoot;
        return true;
    }

    public static int findSet(int x) {
        if(parents[x] == x) {
            return x;
        }

        return parents[x] = findSet(parents[x]);
    }

    public static void init() throws Exception {
        String[] inputs = br.readLine().split(" ");
        V = Integer.parseInt(inputs[0]);
        E = Integer.parseInt(inputs[1]);

        edges = new Edge[E];
        parents = new int[V + 1];

        for (int i = 0; i < E; i++) {
            inputs = br.readLine().split(" ");
            int s = Integer.parseInt(inputs[0]);
            int e = Integer.parseInt(inputs[1]);
            long w = Long.parseLong(inputs[2]);

            edges[i] = new Edge(s, e, w);
        }
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        long weight;

        Edge(int start, int end, long weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }
}
