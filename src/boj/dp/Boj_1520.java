package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
내리막길 - 골3
소요 시간: 38분
1) 메모리 초과
원인: 방문 배열을 매개변수로 받아 사용한 것이 문제로 추측됨
>> 백트래킹으로 인해 필요가 없어짐. 그래서 정적 배열 선언으로 변환

소요 시간: + 5분
2) 시간 초과
원인: 좌표 기준 상하좌우 분석 + 재귀 함수의 문제로 추측됨
>> dfs가 아니라 DP로 접근하는게 맞다고 생각

소요 시간: 1시간 초과
중복되는 경우를 동적 계획법으로의 규칙을 구상하지 못함
>> 각 좌표에는 갈 수 있는 곳의 경우의 수가 존재(중복 경우)
- 블로그 참조 >> https://steady-coding.tistory.com/142
 */
public class Boj_1520 {
    static int M, N, result;
    static int[][] map, dp;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            Arrays.fill(dp[i], -1);
        }

        result = dfs(0, 0);
        System.out.println(result);
    }

    //dfs 풀이
    private static int dfs(int x, int y) {
        if (x == M - 1 && y == N - 1) {
            return 1;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        // 현재 위치에서 끝점까지 도달하는 경로의 개수를 0으로 초기화
        dp[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                // (x,y)높이 > (dx,dy)높이 >> map[dx][dy]에서 끝점까지 도달하는 경로의 개수 더하기
                if (map[x][y] > map[nx][ny]) {
                    dp[x][y] += dfs(nx, ny);
                }
            }
        }

        return dp[x][y];
    }
}
