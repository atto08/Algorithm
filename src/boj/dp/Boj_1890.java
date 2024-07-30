package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
점프 - 실1
소요 시간: 50분(학습)
[학습 - 풀이X]
2차원 배열에서 DP를 푸는 방식을 모른다고 판단되어 방법을 찾아봄
2차원 배열에서 dp 풀이 방식을 검색

점화식을 잘 세우는 것이 중요한 건 알고 있었지만, 왜 그렇게 세워야 조건이 어떤 이유로 적용되는 것인지를 이해하지 못했었음
그래서 이해되지 않던 부분에 주석을 달아놓음. >> 이해 완료.

>> 점화식을 많이 세워봐야하고 dp 문제를 많이 풀어보는 것이 정답을 찾아낼 지름길.

- 2차원 배열 dp 경로 문제
 */
public class Boj_1890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1; // 시작 경로는 무조건 1개임 (0,0)에서 시작하기 때문

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // dp[i][j]가 0보다 크고, map[i][j]가 0보다 크다면, 현재 칸에서 이동할 수 있음을 의미
                if (dp[i][j] > 0 && map[i][j] > 0) {
                    int jump = map[i][j];

                    if (i + jump < N) dp[i + jump][j] += dp[i][j];

                    if (j + jump < N) dp[i][j + jump] += dp[i][j];
                }
            }
        }
        System.out.println(dp[N - 1][N - 1]);
    }
}
