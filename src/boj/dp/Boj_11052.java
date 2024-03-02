package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

/*
블로그 참조
경우의 수를 따지다보니 완전탐색하는 방향으로 코드를 작성
점화식, 메모이제이션 활용!
 */
public class Boj_11052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] P = new int[N + 1];
        int[] dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
        dp[1] = P[1];

        for (int i = 2; i <= N; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int num = dp[j] + dp[i - j];
                if (max < num) {
                    max = num;
                }
            }
            dp[i] = Math.max(max, P[i]);
        }

        System.out.println(dp[N]);
    }
}
