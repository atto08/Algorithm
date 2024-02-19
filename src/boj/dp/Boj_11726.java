package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
쉽게 생각할 규칙을 너무 어렵게 생각해서 예상보다 더많은 시간을 소요
 */
public class Boj_11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        dp[1] = 1;
        if (N > 1) {
            dp[2] = 2;
        }
        if (N > 2) {
            for (int i = 3; i <= N; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
            }
        }
        System.out.println(dp[N]);
    }
}
