package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
블로그 참고 - 거꾸로 접근해보자
 */
public class Boj_14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] Ti = new int[N + 1];
        int[] Pi = new int[N + 1];

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            Ti[i] = Integer.parseInt(st.nextToken());
            Pi[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 2];
        for (int i = N; i > 0; i--) {
            int next = i + Ti[i];
            if (next > N + 1) {
                dp[i] = dp[i + 1];
            } else {
                dp[i] = Math.max(dp[i + 1], Pi[i] + dp[next]);
            }
        }

        System.out.println(dp[1]);
    }
}
