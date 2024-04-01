package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
쉬운 계단 수 - 실1
1) 틀렸습니다.
소요 시간: 21분
원인: 세운 점화식을 잘못 세움 => N의 값이 5부터 잘못 나옴

블로그 참조.
2) 2차원 배열로 해결

진작에 각 자릿값을 저장할 걸
생각나는 대로 먼저 구상해보자 최적화는 이후에 해도 충분하다.

 */
public class Boj_10844 {
    final static long mod = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N + 1][10];
        // 첫 번째 자릿수는 오른쪽 맨 끝의 자릿수이므로 경우의 수가 1개밖에 없음
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }
        // 두 번째 자릿수부터 N까지 탐색
        for (int i = 2; i <= N; i++) {
            // i번째 자릿수의 자릿값들을 탐색 (0~9)
            for (int j = 0; j < 10; j++) {
                // j=0, 즉 자릿값이 0이라면 이전 자릿수의 첫번째 자릿수만 가능
                if (j == 0) {
                    dp[i][0] = dp[i - 1][1] % mod;
                }
                // j=9라면 이전 자릿수는 8만 가능
                else if (j == 9) {
                    dp[i][9] = dp[i - 1][8] % mod;
                }
                // 그 외의 경우 이전 자릿수의 자릿값 +1, -1 의 합이 됨
                else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
                }
            }
        }

        long result = 0;
        // 각 자릿값마다의 경우의 수를 모두 더해준다.
        for (int i = 0; i < 10; i++) {
            result += dp[N][i];
        }
        System.out.println(result % mod);
    }
}
