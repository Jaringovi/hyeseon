package week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
2606. 바이러스
 */
public class BOJ_2606 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int e;
    static boolean[] visit;
    static List<List<Integer>> graph = new ArrayList<>();
    static int ans = 0;


    public static void main(String[] args) throws Exception  {
        input();
        dfs(1);
        System.out.println(ans);
        br.close();
    }

    private static void dfs(int start) {
        visit[start] = true;

        for (int v : graph.get(start)) {
            if (visit[v]) {
                continue;
            }
            ans++;
            dfs(v);
        }
    }

    private static void input() throws Exception {
        n = Integer.parseInt(br.readLine());
        e = Integer.parseInt(br.readLine());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            String[] temp = br.readLine().split(" ");
            int u = Integer.parseInt(temp[0]);
            int v = Integer.parseInt(temp[1]);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        visit = new boolean[n + 1];
    }
}