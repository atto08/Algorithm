package boj.dp;

import java.util.Scanner;

public class Boj_2839 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] dp = new int[N / 3][N / 3 + 2];
        dp[0][0] = 3;
        dp[0][1] = 5;

        for (int i = 1; i < N / 3; i++) {
            for (int j = 0; j < i + 2; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + 3;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 5;
                }
            }
        }

        int result = -1;
        boolean find = false;
        for (int i = 0; i < N / 3; i++) {
            if(find){
                break;
            } else{
                for (int j = 0; j < N / 3 + 2; j++) {
                    if (dp[i][j] == N){
                        result = i+1;
                        find = true;
                        break;
                    }
                }
            }
        }

        System.out.println(result);
        sc.close();
    }
}
