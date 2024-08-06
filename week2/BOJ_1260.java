package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
1260. DFS와 BFS
 */
public class BOJ_1260 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int nodeN;
    static int edgeN;
    static int start;
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        input();

        dfs(start);
        sb.append("\n");
        visit = new boolean[nodeN + 1];

        bfs(start);

        System.out.println(sb);
        br.close();
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        sb.append(start).append(" ");
        visit[start] = true;

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                if (!visit[next]) {
                    visit[next] = true;
                    sb.append(next).append(" ");
                    queue.add(next);
                }
            }
        }
    }

    private static void dfs(int start) {
        visit[start] = true;
        sb.append(start).append(" ");

        for (Integer v : graph.get(start)) {
            if (!visit[v]) {
                visit[v] = true;
                dfs(v);
            }
        }
    }

    public static void input() throws Exception {
        String[] temp = br.readLine().split(" ");
        nodeN = Integer.parseInt(temp[0]);
        edgeN = Integer.parseInt(temp[1]);
        start = Integer.parseInt(temp[2]);
        visit = new boolean[nodeN + 1];

        for (int i = 0; i <= nodeN; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edgeN; i++) {
            String[] nodes = br.readLine().split(" ");
            int u = Integer.parseInt(nodes[0]);
            int v = Integer.parseInt(nodes[1]);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // 연결 된 노드가 다양할 경우 작은 노드부터 처리하기 위해 정렬
        for (int i = 1; i <= nodeN; i++) {
            Collections.sort(graph.get(i));
        }
    }
}