package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
아기 상어 - 실2
소요 시간: 18분 

단순 너비 우선 탐색 문제
개념이 흔들린거 같아 풀어봄

문제에서 안전거리는 빈칸 기준 가장 가까운 상어와의 거리 중 가장 큰 값을 의미함
 */
public class Boj_17086 {
    static int N, M, safeDist;
    static int[] dx = {-1, 0, 1, 0, -1, 1, -1, 1};
    static int[] dy = {0, -1, 0, 1, -1, -1, 1, 1};
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> q = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                    map[i][j] = 0;
                }
            }
        }

        bfs();

        System.out.println(safeDist);
    }

    private static void bfs() {
        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (!visited[nx][ny] && map[nx][ny] == 0) {
                        q.offer(new int[]{nx, ny});
                        map[nx][ny] = map[now[0]][now[1]] + 1;
                        if (map[nx][ny] > safeDist) {
                            safeDist = map[nx][ny];
                        }
                    }
                }
            }
        }
    }
}
