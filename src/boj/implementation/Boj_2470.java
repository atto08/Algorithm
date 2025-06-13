package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
두 용액 - 골5

용액(2467)과 똑같은 문제.
대신 용액배열이 오름차순이 아님.
그래서 정렬한번 해주면 정답임.
 */
public class Boj_2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        int start = 0, end = N - 1, w1 = 0, w2 = 0, dis = Integer.MAX_VALUE;
        while (start != end) {

            int sum = arr[start] + arr[end];
            int abs = Math.abs(sum);

            if (abs <= dis) {
                dis = abs;
                w1 = arr[start];
                w2 = arr[end];
            }

            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }

        System.out.println(w1 + " " + w2);
    }
}