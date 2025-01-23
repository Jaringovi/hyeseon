package week23;

import java.io.*;
import java.util.*;

/** 1477. 휴게소 세우기
* 메모리 14320	kb
* 시간 104 ms
* 이분탐색
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
