package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_1149 {
    static StringTokenizer st;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][3];
        int[][] dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0] = map[0];

        /*
        이전과 겹치지 않는 k의 인덱스를 q안에 넣어 j!=k인 이전 인덱스 값만
        현재 인덱스의 값과 더하여 더 작은 경우를 dp[i][j] 값으로 지정
         */
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j != k) {
                        q.offer(dp[i - 1][k]);
                    }
                }
                dp[i][j] = Math.min(map[i][j] + q.poll(), map[i][j] + q.poll());
            }
        }

        Arrays.sort(dp[N - 1]);
        System.out.println(dp[N - 1][0]);
        br.close();
    }
}
