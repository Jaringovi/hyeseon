package week12;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BJ_S1_1062 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int k;
    static boolean[] alphabet;
    static List<Character> letters;
    static List<String> words;

    static int result;

    public static void main(String[] args) throws Exception {
        init();
        solve();
        output();
    }

    private static void output() {
        System.out.println(result);
    }

    private static void solve() {
        if(k < 5) {
            result = 0;
            return;
        }

        backtracking(0, 0);
    }

    private static void backtracking(int cnt, int start) {
        if (cnt == k - 5) {
            int studyWords = findStudyWords();
            result = Math.max(result, studyWords);
            return;
        }

        for (int i = start; i < letters.size(); i++) {
            alphabet[letters.get(i) - 'a'] = true;
            backtracking(cnt + 1, i + 1);
            alphabet[letters.get(i) - 'a'] = false;
        }
    }

    private static int findStudyWords() {
        int count = 0;
        for (String word : words) {
            boolean isRemainLetter = false;
            for (char letter : word.toCharArray()) {
                if(!alphabet[letter-'a']) {
                    isRemainLetter = true;
                    break;
                }
            }
            if(!isRemainLetter) {
                count++;
            }
        }
        return count;
    }

    public static void init() throws Exception {
        String[] inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        k = Integer.parseInt(inputs[1]);

        alphabet = new boolean[26];
        alphabet[0] = true; // a
        alphabet[2] = true; // c
        alphabet[8] = true; // i
        alphabet[13] = true; // n
        alphabet[19] = true; // t

        words = new ArrayList<>();
        letters = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String word = br.readLine().replace("anta", "").replace("tica", "");

            String nextWord = "";
            for(char c : word.toCharArray()) {
                if(alphabet[c - 'a'] || letters.contains(c)) {
                    continue;
                }
                letters.add(c);
                nextWord += String.valueOf(c);
            }

            words.add(nextWord);
        }

        result = Integer.MIN_VALUE;
    }
}
