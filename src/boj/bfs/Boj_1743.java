package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
음식물 피하기 - 실1
소요 시간: 17분

각 칸마다 쓰레기인 칸인지 체크 후 쓰레기면 이동 후 사이즈 업 + 방문 체크
>> bfs로 가장 큰 크기의 음식물 쓰레기 찾는 문제
 */
public class Boj_1743 {
    static int N, M, K, max;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            map[x][y] = 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(max);
    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new int[]{x, y});

        int size = 0;
        while (!q.isEmpty()) {

            int[] now = q.poll();
            size++;

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                if (!visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        if (size > max) {
            max = size;
        }
    }
}
