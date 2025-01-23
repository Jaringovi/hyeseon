package week23;

import java.io.*;
import java.util.*;

/** 1477. 휴게소 세우기
* 메모리 14320	kb
* 시간 104 ms
* 이분탐색
* 
* [풀이]
* 이분탐색으로 구해야 하는 값: target(추가 휴게소 수)이 가능한 최대 휴게소 거리의 최소값
* key point : 0 기존 휴게소 위치 L에서 (이후 값 - 이전 값 - 1) / mid 를 count에 누적한 값 == 그 mid 거리로 세울 수 있는 휴게소의 수 
* - `-1`을 해야 하는 이유: mid가 70이고 이전 값이 70, 이후 값이 140이라고 했을 때 휴게소를 세울 수 없다. 
* countHouse(mid) > target <- mid의 값이 작다. start = mid + 1
* countHouse(mid) < target <- mid의 값이 크다. end = mid - 1;
* count 값이 target을 만족하면서 mid 값이 가장 작은 경우를 구해야 하기 때문에 countHouse(mid) <= target 을 해야 한다.
* -> lower bound : 주어진 조건을 만족하는 가장 작은 값을 찾는 문제
*/
public class BJ_G4_1477_14320kb_104ms {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, target, L;
    static int[] restHouse;
    static int result;
    
    public static void main(String[] args) throws Exception {
        init();
        solve();
        output();
    }
    
    public static void init() throws Exception {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        target = Integer.parseInt(inputs[1]);
        L = Integer.parseInt(inputs[2]);
        
        restHouse = new int[N + 2];
        restHouse[0] = 0;
        restHouse[restHouse.length - 1] = L;
        
        inputs = br.readLine().split(" ");
        for(int i = 1; i <= N; i++) {
            restHouse[i] = Integer.parseInt(inputs[i - 1]);
        }
        
        result = Integer.MAX_VALUE;
    }
    
    public static void solve() {
        Arrays.sort(restHouse);
        
        int start = 1;
        int end = L - 1;
        
        while(start <= end) {
            int mid = (start + end) / 2;
            
            if(countHouse(mid) <= target) {
                end = mid - 1;
                result = Math.min(mid, result);
            }else {
                start = mid + 1;
            }
        } 
    }
    
    public static int countHouse(int mid) {
        int count = 0;
        for(int i = 0; i < restHouse.length - 1; i++) {
            count += (restHouse[i + 1] - restHouse[i] - 1) / mid;
        }
        
        return count;
    }
    
    public static void output() {
        System.out.println(result);
    }
}
