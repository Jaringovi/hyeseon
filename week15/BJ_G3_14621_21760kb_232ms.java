package week15;

import java.io.*;
import java.util.*;

/** 14621. 나만 안되는 연애
 * 메모리 21760 kb
 * 시간 232 ms
 * MST
 *
 * union 하기 전에 start, end 노드가 다른 성별 학교인지 검사한 후 union 한다.
 *
 * 33%에서 틀렸습니다 난 이유
 * count가 N-1이 되기 전에 간선 리스트를 다 돌 수 있으므로 flag 변수 필요
 */
public class BJ_G3_14621_21760kb_232ms {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int E;
    static int[] parents;
    static String[] universities;
    static List<Edge> edges;
    static boolean flag;
    static int result;

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solve();
        output();
    }

    private static void init() throws IOException {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        E = Integer.parseInt(inputs[1]);

        inputs = br.readLine().split(" ");
        universities = new String[N + 1];
        for (int i = 1; i <= N; i++) {
            universities[i] = inputs[i - 1];
        }

        edges = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            inputs = br.readLine().split(" ");
            int start = Integer.parseInt(inputs[0]);
            int end = Integer.parseInt(inputs[1]);
            int weight = Integer.parseInt(inputs[2]);

            edges.add(new Edge(start, end, weight));
        }

        result = 0;
    }

    private static void solve() {
        make();

        Collections.sort(edges);

        int count = 0;
        for(Edge e : edges) {
            if (!universities[e.start].equals(universities[e.end]) && union(e.start, e.end)) {
                result += e.weight;
                if (++count == N - 1) {
                    flag = true;
                    break;
                }
            }
        }
    }

    private static void make() {
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if(aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = aRoot;
        return true;
    }

    private static int findSet(int x) {
        if(parents[x] == x) {
            return x;
        }

        return parents[x] = findSet(parents[x]);
    }

    private static void output() {
        if(result == 0 || !flag) {
            System.out.println(-1);
        }else {
            System.out.println(result);
        }
    }
}
