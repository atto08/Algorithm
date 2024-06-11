package boj.dp;

import java.util.Scanner;
/*
이친수 - 실3
소요 시간: 25분
원인: 규칙은 빨리 찾았으나 대입시킬 점화식을 구현하는데 시간이 걸림

1) 통과
 */
public class Boj_2193 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        long[][] dp = new long[N + 1][2];
        if (N < 3) {
            System.out.println(1);
        } else {
            dp[2][0] = 1;

            for (int i = 3; i <= N; i++) {
                dp[i][0] += dp[i - 1][0] + dp[i - 1][1];
                dp[i][1] += dp[i - 1][0];
            }
            System.out.println(dp[N][0] + dp[N][1]);
        }
    }
}
