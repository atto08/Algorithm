package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
DP 점화식이 익숙치않아 블로그 참고
이해하는데 1시간 걸림.. 아직 갈길이 너무 멀다.
1) 문제를 작게 나눠서 보자
2) 점화식 찾기
 */
public class Boj_2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dp = new int[K + 1];

        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            int coin = Integer.parseInt(br.readLine());
            for (int j = coin; j <= K; j++) {
                dp[j] = dp[j] + dp[j - coin];
            }
        }

        System.out.println(dp[K]);
        br.close();
    }
}
