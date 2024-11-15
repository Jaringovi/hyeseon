package week07;

import java.io.*;

/** 2343. 기타 레슨
 * 메모리 25480 kb
 * 시간 276 ms
 * 이분탐색
 *
 * [풀이]
 * 1. 블루레이의 크기가 크면 target 값보다 작은 값이 나온다.
 * 2. 블루레이의 크기가 작으면 target 값보다 큰 값이 나온다.
 * 3. 위의 개념을 가지고 lower bound로 풀면 된다. (특정 조건을 만족하는 가장 작은 값)
 * + 한 강의의 크기가 mid 값보다 크면 넘겨야 한다. 여기서 50%에서 틀렸습니다가 나왔다.
 */
public class BJ_S1_2343_25480kb_276ms {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int result;

    static int lectureN, target;
    static int[] lectures;
    static int sumTime;
    static int minTime;

    public static void main(String[] args) throws Exception {
        init();
        solve();
        output();
    }

    public static void init() throws Exception {
        String[] inputs = br.readLine().split(" ");
        lectureN = Integer.parseInt(inputs[0]);
        target = Integer.parseInt(inputs[1]);

        sumTime = 0;
        minTime = Integer.MAX_VALUE;
        lectures = new int[lectureN];
        inputs = br.readLine().split(" ");
        for (int i = 0; i < lectureN; i++) {
            lectures[i] = Integer.parseInt(inputs[i]);
            sumTime += lectures[i];
            minTime = Math.min(minTime, lectures[i]);
        }

        result = Integer.MAX_VALUE;
    }

    private static void solve() {
        int start = minTime;
        int end = sumTime;

        while(start <= end) {
            int mid = (start + end) / 2;

            int count = makeBluray(mid);
            if(count <= target) {
                result = mid;
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }
    }

    private static int makeBluray(int size) {
        int sum = 0;
        int count = 1;
        for (int i = 0; i < lectureN; i++) {
            // 한 강의의 크기가 size보다 크면 불가능
            if(lectures[i] > size) {
                return Integer.MAX_VALUE;
            }

            sum += lectures[i];
            if(sum > size) {
                count++;
                sum = lectures[i];
            }
        }
        return count;
    }

    private static void output() {
        System.out.println(result);
    }
}
