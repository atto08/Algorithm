package boj.dp;

import java.io.*;

public class Boj_9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        long[] dp = new long[101];

        for (int test_case = 0; test_case < T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            dp[1] = dp[2] = dp[3] = 1;
            dp[4] = dp[5] = dp[1] + dp[3];

            if (N > 5) {
                for (int i = 6; i <= N; i++) {
                    dp[i] = dp[i - 1] + dp[i - 5];
                }
            }
            bw.write(dp[N] + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
