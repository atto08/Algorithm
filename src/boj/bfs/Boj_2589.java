package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
보물섬 - 골5
소요 시간: 15분

1) 통과
전형적인 bfs 문제 - 모든 육지에서 방문하지 않은 육지까지 가장 오래걸리는 육지까지의 시간이 정답
 */
public class Boj_2589 {
    static int N, M, result;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 육지끼리 최장거리! 구하면됨
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') { // 육지일 때 만
                    visited = new boolean[N][M];
                    bfs(i, j);
                }
            }
        }

        System.out.println(result);
    }

    private static void bfs(int x, int y) {
        Queue<Island> q = new LinkedList<>();
        q.offer(new Island(x, y, 0));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Island now = q.poll();

            if (result < now.dist) {
                result = now.dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (!visited[nx][ny] && map[nx][ny] == 'L') {
                    visited[nx][ny] = true;
                    q.offer(new Island(nx, ny, now.dist + 1));
                }
            }
        }
    }

    static class Island {
        int x, y, dist;

        private Island(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
