package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
벽부수고 이동하기 - 골3
[재풀이] 이전에 제대로 이해하지 못해서 다시 풂
소요 시간: 1시간 초과
point 벽을 부수고 이동한 경우와 그렇지 않은 경우 구분짓기

블로그 & 질문 게시판 참고
 */
public class Boj_2206 {
    static int N, M;
    static boolean[][][] visited;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String[] arr = br.readLine().split("");
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(arr[j - 1]);
            }
        }
        bfs();
    }

    private static void bfs() {
        Queue<Edge> queue = new LinkedList<>();
        queue.offer(new Edge(1, 1, 1, false));
        visited = new boolean[N + 1][M + 1][2];

        while (!queue.isEmpty()) {
            Edge e = queue.poll();
            int x = e.x, y = e.y;
            // 도착지에 도착하면
            if (x == N && y == M) {
                System.out.println(e.dist);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx > N || ny > M) {
                    continue;
                }
                // 벽이 아닐때
                if (map[nx][ny] == 0) {
                    // 벽 부순적 없으면서 방문한 적 없으면 이동
                    if (!e.crash && !visited[nx][ny][0]) {
                        queue.offer(new Edge(nx, ny, e.dist + 1, false));
                        visited[nx][ny][0] = true;
                    }
                    // 벽 부수고 방문한적 없으면 이동
                    else if (e.crash && !visited[nx][ny][1]) {
                        queue.offer(new Edge(nx, ny, e.dist + 1, true));
                        visited[nx][ny][1] = true;
                    }
                }
                // 벽일 때
                else if (map[nx][ny] == 1) {
                    // 벽을 부순적 없으면 부수고 이동
                    if (!e.crash) {
                        queue.offer(new Edge(nx, ny, e.dist + 1, true));
                        visited[nx][ny][1] = true;
                    }
                    //부순적 있다면 X
                }
            }
        }
        // 도착지에 도달할 수 없다면
        System.out.println(-1);
    }

    static class Edge {
        int x, y, dist;
        boolean crash;

        private Edge(int x, int y, int dist, boolean crash) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.crash = crash;
        }
    }
}
