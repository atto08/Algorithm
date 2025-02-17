package programmers.lv3;

/*
정수 삼각형 - lv3(개념정리 및 재풀이)
소요시간 - 20분
분류 - DP

문제 설명:
- 삼각형의 꼭대기에서 바닥까지 이어지는 경로 중 거쳐간 숫자의 합이 가장 큰 경우 구하기
- 배열은 행이 증가할수록 +1개의 숫자를 가짐
- 제일 큰 경우의 수부터 시작하여 꼭대기(0행)에 도달할 때 까지 이전 행에 있는 두 수 중 더 큰 수를 골라서 저장하도록 구현(DP)
*/
public class Program_43105 {

    public int solution(int[][] triangle) {
        int N = triangle.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) dp[N - 1][i] = triangle[N - 1][i];

        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle[i][j] + Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        Program_43105 pg = new Program_43105();
        System.out.println(pg.solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}));
    }
}
