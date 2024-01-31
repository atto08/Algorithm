package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DP 설명은 들었는데, 규칙은 내가 찾았음. - 의외로 빨리 이해하고 쉽게 풂
/*
DP 문제 - 생각할 부분
1) 어떻게 해야 뒤로 돌아가지 않을까?
2) 정보를 어떻게 누적해야 할까?
3) 같은 연산을 하지않기 위해 어떤 정보를 남겨야 할까?
 */
public class Boj_1932 {
    static StringTokenizer st;
    static int N, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int[][] dp = new int[N][N];

        // 집어넣기
        count = 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < count; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            count++;
        }

        // dp 만들기!
        count = 2;
        dp[0][0] = map[0][0];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + map[i][j];
                } else if (i == j) {
                    dp[i][j] = dp[i - 1][j - 1] + map[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + map[i][j], dp[i - 1][j] + map[i][j]);
                }
            }
            count++;
        }

        // 최댓값 넣기
        int max = dp[N - 1][0];
        for (int i = 1; i < N; i++) {
            if (max < dp[N - 1][i]) {
                max = dp[N - 1][i];
            }
        }

        System.out.println(max);
        br.close();
    }
}
