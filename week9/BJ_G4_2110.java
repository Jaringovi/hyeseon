package week9;

import java.io.*;
import java.util.*;

/** 2110. 공유기 설치
 * 메모리 28936 kb
 * 시간 260 ms
 *
 * 이분탐색
 * 기타레슨과 비슷한 문제인걸 알았는데
 * 등호/부등호를 사용하는 경우와 +1을 사용하는 경우를 잘 모르겠어서
 * gpt 도움으로 어떤 경우에 사용하는지 이해함
 * 다시 혼자 힘으로 풀었음
 *
 * <메모>
 * 1. start <= end
 * 최종 답을 명확히 찾아야 할 때 사용, 마지막으로 start == end가 되는 경우까지 확인해야 하는 경우에 사용
 * 되도록 이것을 사용하는 것이 정확하다.
 *
 * 2. start = mid + 1, end = mid - 1;
 * 정확한 값을 찾는 경우, 즉 mid값이 답이 아닐 때 사용
 * mid 값을 제외하고 탐색 범위를 좁혀야 할 때 사용
 *
 * 3. start = mid, end = mid;
 * mid 값이 답일 가능성이 있는 경우 사용
 * mid를 포함한 범위에서 더 나은 답을 찾기 위해 탐색을 계속해야 할 때 사용
 * - lower bound
 *   target 값보다 크거나 같은 숫자가 처음 나오는 곳 찾기
 *   start = mid
 * - upper bound
 *   target 값보다 큰 값이 처음 나오는 위치를 구하기
 *   end = mid
 * - 두 경우 다 결과값은 start에 있다.
 *   이분탐색에서 탐색 범위를 좁혀가는 과정에서 start가 답에 가까워지는 방향으로 이동하기 때문에
 *   start가 답을 가리키게 된다.
 */
public class BJ_G4_2110 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int C;
    static int[] homes;

    static int result;

    public static void main(String[] args) throws Exception {
        init();
        solve();
        output();
    }

    public static void output() {
        System.out.println(result);
    }

    public static void solve() {
        int start = 1; // 최소 범위
        int end = homes[homes.length - 1] - homes[0]; // 최대 범위

        while(start <= end) { // <= 을 사용하는 이유: start와 end가 같을 때까지, 그 값도 확인하기 위함이다.
            int mid = (start + end) / 2;

            // mid로 공유기 설치가 가능하거나 더 넓은 범위로도 설치가 가능한 경우
            if(getCount(mid) >= C) {
                result = mid; // 두 공유기 사이의 거리가 최대여야 하므로 계속 갱신
                start = mid + 1; // 더 넓은 범위에서 설치가 가능한지 확인
            }else {
                end = mid - 1; // 더 작은 범위에서 설치가 가능한지 확인
            }
        }
    }

    public static int getCount(int mid) {
        int count = 1; // 첫번째 집에 무조건 공유기 설치
        int before = homes[0];

        for (int i = 1; i < N; i++) {
            // 인덱스가 3일 때 집의 좌표는 8이다. 8-1 >= 3 이므로 공유기를 하나 더 설치해야 한다.
            if(homes[i] - before >= mid) {
                count++;
                before = homes[i];
            }
        }
        return count;
    }

    public static void init() throws Exception {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        C = Integer.parseInt(inputs[1]);

        homes = new int[N];
        for (int i = 0; i < N; i++) {
            homes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(homes);
    }
}
