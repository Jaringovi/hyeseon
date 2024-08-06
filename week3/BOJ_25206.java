package week3;

import java.io.*;
import java.util.*;

/*
25206. 너의 평점은
 */
public class BOJ_25206 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Map<String, Double> map = new HashMap<>();
    static int n;

    public static void main(String[] args) throws Exception{
        init();

        double total = 0;
        double totalNum = 0;
        for (int i = 0; i < n; i++) {
            String[] inputs = br.readLine().split(" ");
            String subject = inputs[0];
            double num = Double.parseDouble(inputs[1]);
            String grade = inputs[2];

            if(grade.equals("P")) {
                continue;
            }

            totalNum += num;
            total += num * map.get(grade);
        }

        System.out.println(total/totalNum);
    }

    private static void init() {
        n = 20;
        map.put("A+", 4.5);
        map.put("A0", 4.0);
        map.put("B+", 3.5);
        map.put("B0", 3.0);
        map.put("C+", 2.5);
        map.put("C0", 2.0);
        map.put("D+", 1.5);
        map.put("D0", 1.0);
        map.put("F", 0.0);
    }
}