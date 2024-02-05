package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 점화식 이용하기. --> 점화식 찾아내기
 dp 문제를 정수 삼각형 문제(1932)로 시작해
 dp 스럽지 못한 풀이를 계속사용 (중복 계산) =>> 메모리/시간 초과 발생
 다이나믹 프로그래밍은 중복 계산을 하지 않도록 하여 시간과 메모리 초과를 피하는 방법.
 */
public class Boj_2225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int result = 0;
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[K + 1][N + 1];
        dp[0][0] = 1;

        for (int i = 1; i <=K; i++) {
            for (int j = 0; j <= N; j++) {
                for (int l = 0; l <= j; l++) {
                    dp[i][j] += dp[i - 1][j - l];
                    dp[i][j] %= 1000000000;
                }
            }
        }
        for (int i=0; i<=K; i++){
            System.out.println(Arrays.toString(dp[i]));
        }

        System.out.println(dp[K][N]);
        br.close();
    }
}
