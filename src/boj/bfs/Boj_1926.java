package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
그림 - 실1
소요 시간: 12분

풀이:
1) map을 돌며 방문하지 않은 1을 찾기
2) 찾은 1에서 상하좌우를 확인하여 붙어있는 1을 확인
3) 붙은 1의 갯수를 세기 (그림 사이즈 재기)
4) 남은 방문하지 않은 1찾기
 */
public class Boj_1926 {
    static int N, M, picture, maxSize;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

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
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(picture + "\n" + maxSize);
    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new int[]{x, y});

        int pictureSize = 0;
        while (!q.isEmpty()) {
            int[] n = q.poll();
            pictureSize++;

            for (int i = 0; i < 4; i++) {
                int nx = n[0] + dx[i];
                int ny = n[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                if (!visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        picture++;
        if (pictureSize > maxSize) {
            maxSize = pictureSize;
        }
    }
}
