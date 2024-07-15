package boj.dp;

import java.util.Arrays;
import java.util.Scanner;

/*
LCS - 골5
소요 시간: 30분

DP LCS(최장 공통 부분 수열)문제 학습.
>> 완벽히 이해하지 못함
 */
public class Boj_9251 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str1 = sc.next();
        String str2 = sc.next();

        System.out.println(LCS(str1, str2));
    }

    private static int LCS(String str1, String str2) {

        int N = str1.length(), M = str2.length();
        int[][] dp = new int[N + 1][M + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[N][M];
    }
}
