package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
'연속된 3잔 마시기 불가' 하다는 조건을 보고 계단오르기 와 같은 문제로 인식.
==> 1) 틀렸습니다.
의도하고 마시지 않는 경우를 고려하지 x

계단오르기는 마지막 계단을 꼭 밟아야한다는 조건이 있었음.
ex) 10 25 30 1 이 주어졌을때
계단오르기 경우 ==> 최대값은 10 + 30 + 1 = 41
포도주시식 경우 ==> 최대값은 25 + 30 = 55
즉 연속으로 3번 만나지 않으면 됨
결론 = max(안마실때, max(연속한잔째, 연속두잔째))

아직 생각하는 것을 구현해내는 것은 물론이고 문제의 의도를 파악하는데 어려움을 많이 겪고 있다.
문제를 많이 풀면서 익숙해질 수 있도록 하자!
 */
public class Boj_2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] wine = new int[N + 1];
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = wine[1];
        if (N > 1) {
            dp[2] = wine[1] + wine[2];
        }
        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2], dp[i - 3] + wine[i - 1]) + wine[i]);
        }

        System.out.println(dp[N]);
    }
}
