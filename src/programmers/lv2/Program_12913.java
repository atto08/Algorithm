package programmers.lv2;

/*
땅따먹기 - lv2
소요시간 - 30분

풀이설명:
- 행은 최대 10만, 최소 1개
- dp 문제 -> 탑다운으로 풀기

p.s
- dp문제 오랜만이라 기억안나서 큐로 모든 경우를 보려했음.
    -> N(행의 갯수)은 최대 100,000인데 그렇게 되면 시간초과가 무조건 발생할 수 밖에없다.
    즉, 이건 아니고 무조건 DP로 해결해야하는 문제.

솔직히 탑다운 & 바텀업 방식은 기억했지만 구분할 방법이 기억 안났음.
-> 질문하기에서 Math 키워드 보고 바로 기억남.

실제 코테에서도 이럴 수 없으니 오늘 액땜한걸로. dp 오랜만이였다.
그래도 Math 키워드 하나만 보고 구현방식 기억하고 빨리 구현했다.~
*/

import java.util.Arrays;

public class Program_12913 {
    public static int solution(int[][] land) {

        int L = land.length;
        int[][] dp = new int[L][4];

        for (int i = 0; i < 4; i++) dp[0][i] = land[0][i];

        for (int i = 1; i < L; i++) {
            for (int j = 0; j < 4; j++) {
                dp[i][j] = maxValGround(i, j, dp, land);
            }
        }

        Arrays.sort(dp[L - 1]);
        return dp[L - 1][3];
    }

    private static int maxValGround(int i, int j, int[][] dp, int[][] land) {
        if (j == 0) {
            return land[i][j] + Math.max(dp[i - 1][1], Math.max(dp[i - 1][2], dp[i - 1][3]));
        } else if (j == 1) {
            return land[i][j] + Math.max(dp[i - 1][0], Math.max(dp[i - 1][2], dp[i - 1][3]));
        } else if (j == 2) {
            return land[i][j] + Math.max(dp[i - 1][1], Math.max(dp[i - 1][0], dp[i - 1][3]));
        }
        return land[i][j] + Math.max(dp[i - 1][1], Math.max(dp[i - 1][2], dp[i - 1][0]));
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}}));
    }
}