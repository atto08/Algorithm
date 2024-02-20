package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
블로그 참고 - DP(LIS) 유형
 */
public class Boj_11053 {
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            int here = 0;
            for (int j = 1; j < i; ++j) {
                if (arr[i] > arr[j])
                    here = Math.max(here, dp[j]);
            }
            dp[i] = here + 1;
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);
    }
}
