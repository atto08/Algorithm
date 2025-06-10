package boj.implementation;

import java.io.*;
import java.util.StringTokenizer;

/*
 수열 - 실3
 소요시간 - 30분

 풀이설명:
 - 슬라이딩 윈도우 알고리즘으로 풀이
 */
public class Boj_2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int sum = 0;
        for (int i = 0; i < K; i++) sum += arr[i];

        int max = sum;
        for (int i = 1; i <= N - K; i++) {
            sum = sum - arr[i - 1] + arr[i - 1 + K];
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}
