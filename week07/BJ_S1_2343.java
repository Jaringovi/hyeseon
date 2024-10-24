package week07;

import java.io.*;

public class BJ_S1_2343 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int vedioN;
    static int blurayN;
    static int[] sumVedios;

    public static void main(String[] args) throws Exception  {
        init();
        solve();
    }

    public static void solve() {
        int left = 0;
        int right = sumVedios.length - 1;

        while(left <= right) {
            int mid = (left + right) / 2;

        }
    }

    public static void init() throws Exception {
        String[] inputs = br.readLine().split(" ");
        vedioN = Integer.parseInt(inputs[0]);
        blurayN = Integer.parseInt(inputs[1]);
        sumVedios = new int[vedioN];

        inputs = br.readLine().split(" ");
        sumVedios[0] = Integer.parseInt(inputs[0]);
        for (int i = 1; i < vedioN; i++) {
            sumVedios[i] = Integer.parseInt(inputs[i]) + sumVedios[i - 1];
        }
    }
}
