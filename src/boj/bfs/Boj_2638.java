package boj.bfs;

import java.io.*;
import java.util.*;

/*
치즈 - 골3
치즈(골4) 문제와 유사 - 참고
하지만 치즈의 4면 중 최소 2면 이상이 외부 공기와 접촉해야 1시간뒤 사라짐
소요 시간: 68분
 */
public class Boj_2638 {
    static int N, M, time, cheese;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    cheese++;
                }
            }
        }

        while (cheese != 0) {
            visited = new boolean[N][M];
            bfs();
            time++;
        }

        System.out.println(time);
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        Queue<int[]> cheeseQ = new LinkedList<>();
        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if (map[nx][ny] == 0) {
                        queue.offer(new int[]{nx, ny});
                    }

                    // 외부와 접촉한 치즈 전부 체크
                    else {
                        cheeseQ.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        // 외부와 접촉한 치즈 전부 넣기
        while (!cheeseQ.isEmpty()) {
            int[] xy = cheeseQ.poll();

            int ca = 0;
            int nx = xy[0], ny = xy[1];
            for (int j = 0; j < 4; j++) {
                int cx = nx + dx[j];
                int cy = ny + dy[j];

                if (cx < 0 || cy < 0 || cx >= N || cy >= M) {
                    continue;
                }
                // 공기인지 & 내부 공기인지
                if (map[cx][cy] == 0 && visited[cx][cy]) {
                    ca++;
                }
            }
            // 외부와 접촉한 면이 2이상 일 때
            if (ca > 1) {
                queue.offer(new int[]{nx, ny});
            }
        }
        // 외부와 접촉한 면이 2개 이상인 치즈 제거
        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            map[xy[0]][xy[1]] = 0;
            cheese--;
        }
    }
}
