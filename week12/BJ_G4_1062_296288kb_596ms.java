package week12;

import java.io.*;
import java.util.*;

/** 1062. 가르침
 * 메모리 296288 kb
 * 시간 596 ms
 *
 * 백트래킹
 * 조합
 * 알파벳 배열
 *
 * [틀린 이유]
 * 입력 단어에서 acint 를 제외한 글자만 검사를 했는데 그럴 경우 antantica 같은 acint가 여러 번 있는 단어가
 * 없는 것처럼 취급되었음
 * 입력 단어를 그대로 검사하여 해결했습니다.
 */
public class BJ_G4_1062_296288kb_596ms {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int k;
    static boolean[] alphabet;
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
        } else if (k == 26) {
            result = n;
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

        for (int i = start; i < 26; i++) {
            if (!alphabet[i]) {
                alphabet[i] = true;
                backtracking(cnt + 1, i);
                alphabet[i] = false;
            }
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
        alphabet['a' - 'a'] = true; // a
        alphabet['c' - 'a'] = true; // c
        alphabet['i' - 'a'] = true; // i
        alphabet['n' - 'a'] = true; // n
        alphabet['t' - 'a'] = true; // t

        words = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String word = br.readLine().replace("anta", "").replace("tica", "");
            words.add(word);
        }
        result = Integer.MIN_VALUE;
    }
}
