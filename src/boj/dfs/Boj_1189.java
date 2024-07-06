package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
컴백홈 - 실1
소요 시간: 30분

1) 통과

원인: 단순 백트래킹 깊이우선 탐색 문제로 생각함
해결: 거리 == K && 목적지 위치 일때만 결과값++.
 */
public class Boj_1189 {
    static int R, C, K, result;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();

        visited[R - 1][0] = true;
        dfs(R - 1, 0, 1);

        System.out.println(result);
    }

    private static void dfs(int x, int y, int depth) {

        if (depth == K && x == 0 && y == C - 1) {
            result++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

            if (!visited[nx][ny] && map[nx][ny] != 'T') {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1);
                visited[nx][ny] = false;
            }
        }
    }
}
