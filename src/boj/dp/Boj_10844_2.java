package boj.dp;

import java.util.Scanner;

/*
[다시 풀어보기]
규칙 잘 찾았는데 아쉬워서 다시 품
쉬운 계단 수 - 실1
소요 시간: 16분
 */
public class Boj_10844_2 {
    public static void main(String[] args) {
        int mod = 1000000000;
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        long[][] dp = new long[N + 1][10];
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        if (N > 1) {
            for (int i = 2; i <= N; i++) {
                for (int j = 0; j < 10; j++) {
                    if (j == 0) {
                        dp[i][j] = dp[i - 1][j + 1] % mod;
                    } else if (j == 9) {
                        dp[i][j] = dp[i - 1][j - 1] % mod;
                    } else {
                        dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
                    }
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += dp[N][i];
        }

        System.out.println(sum % mod);
    }
}
