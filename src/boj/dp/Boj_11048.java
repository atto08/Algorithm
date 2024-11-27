package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
이동하기 - 실2
소요시간 - 1시간
원인 -
1. 오랜만에 푸는 DP문제
2. 규칙은 빨리찾고 BFS를 사용한 완탐으로 풀려했음
3. 규칙을 정리하다보니 점화식을 세울 수 있었음.
4. 3번에서 시작부터 하는것이 아니라 테두리를 먼저 채울 규칙을 찾으니 빨라짐.
 */
public class Boj_11048 {
    static int N, M;
    static int[][] map, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        result = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        result[1][1] = map[1][1];
        for (int c = 2; c <= M; c++) result[1][c] = result[1][c - 1] + map[1][c];
        for (int r = 2; r <= N; r++) result[r][1] = result[r - 1][1] + map[r][1];

        for (int r = 2; r <= N; r++) {
            for (int c = 2; c <= M; c++) {
                result[r][c] = Math.max(result[r - 1][c - 1], Math.max(result[r - 1][c], result[r][c - 1])) + map[r][c];
            }
        }

        System.out.println(result[N][M]);
    }
}
