package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1,2차 메모리초과 
N <= 100,000
==> 2차원 배열은 약 10만 * 10만 * 4byte = 4TB 의 메모리를 사용하게 됨.
==> 1차원 배열 사용
이해가 되지않으면 손으로 노가다 해보자 - 이해하기 훨씬 쉬워진다.
쉽게 만들 수 있는 경우를 접하기 위해 많은 문제를 풀어보기!
 */
public class Boj_1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        dp[0] = Integer.parseInt(st.nextToken());
        int max = dp[0];
        for (int i = 1; i < N; i++) {
            dp[i] = Integer.parseInt(st.nextToken());
            dp[i] = Math.max(dp[i], dp[i] + dp[i - 1]);
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
