package programmers;

/*
멀리 뛰기 - level2
소요 시간: 9분

규칙을 빨리 찾아서 dp로 쉽게 풀 수 있었음.
 */
public class Program_12914 {
    public long solution(int n) {
        long[] dp = new long[n + 1];

        if (n < 3) return n;

        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
        }

        return dp[n];
    }
}
