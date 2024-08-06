package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
24060. 알고리즘 수업 - 병합 정렬 1
 */
public class BOJ_24060 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int k;
    static int[] tmp;
    static int count = 0;
    static int ans;

    public static void main(String[] args) throws IOException {
        int[] arr = input();
        int start = 0;
        int end = arr.length - 1;

        mergeSort(arr, start, end);

        if(count < k) {
            ans = -1;
        }
        System.out.println(ans);
    }

    private static void mergeSort(int[] arr, int start, int end) {

        if(start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            } else {
                tmp[t++] = arr[j++];
            }
        }

        while(i <= mid) {
            tmp[t++] = arr[i++];
        }
        while(j <= end) {
            tmp[t++] = arr[j++];
        }
        i = start;
        t = 0;
        while(i <= end) {
            count++;
            arr[i++] = tmp[t++];
            if(count == k) {
                ans = arr[i-1];
            }
        }
    }

    private static int[] input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        tmp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        return arr;
    }
}