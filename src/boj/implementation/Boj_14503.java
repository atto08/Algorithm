package boj.implementation;

import java.io.*;
import java.util.*;

public class Boj_14503 {
    static int N, M, count;
    static int[][] room;
    static int[] dir90 = {3, 0, 1, 2};
    static int[] dir180 = {2, 3, 0, 1};
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        room = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(r, c, d);

        System.out.println(count);
    }

    private static void bfs(int r, int c, int d) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});

        boolean trigger = false;
        while (!trigger) {
            int[] xy = queue.poll();
            int x = xy[0];
            int y = xy[1];
            // 현재 칸이 청소하지 않았으면 청소
            if (room[x][y] == 0) {
                count++;
                room[x][y] = -1;
            }

            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (room[nextX][nextY] == 0) {
                    cnt++;
                }
            }

            // 4칸 모두 청소된 경우
            if (cnt == 0) {
                // 방향 유지 & 한칸 후진 시도
                int nextX = x + dx[dir180[d]];
                int nextY = y + dy[dir180[d]];
                // 벽이라 후진이 안되면 종료
                if (room[nextX][nextY] == 1) {
                    trigger = true;
                }
                // 벽이 아니라 후진이 가능하면 이동 후 1번
                else {
                    queue.offer(new int[]{nextX, nextY});
                }
            }
            // 4칸 중 청소되지 않은 칸이 있는 경우
            else {
                // 반시계 방향으로 회전
                d = dir90[d];
                int nextX = x + dx[d];
                int nextY = y + dy[d];
                // 청소되지 않는 경우 해당 칸으로 이동 후 1번
                if (room[nextX][nextY] == 0) {
                    queue.offer(new int[]{nextX, nextY});
                }
                // 청소된 곳이나 벽이면 해당 자리 유지 후 1번
                else {
                    queue.offer(new int[]{x, y});
                }
            }
        }
    }
}
