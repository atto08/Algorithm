package programmers;

/*
피보나치 수 - level2
소요 시간: 10분 이내 - 경험한 개념
dp 대표 문제
 */
public class Program_12945 {
    public int solution(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 2; i <= n; i++) dp[i] = (dp[i - 2] + dp[i - 1]) % 1234567;

        return dp[n];
    }
}