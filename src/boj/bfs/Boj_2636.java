package boj.bfs;

import java.io.*;
import java.util.*;

/*
치즈 - 골4
- 안쪽 치즈를 방문하지 않는 방법을 생각하지 못함
- 블로그 참조
 */
public class Boj_2636 {
    static int N, M, sec, cheese, min;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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
            min = cheese;
            sec++;
            visited = new boolean[N][M];
            bfs();
        }
        bw.write(sec + "\n" + min);
        bw.flush();
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = xy[0] + dx[i];
                int nextY = xy[1] + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }

                // 새로운 방문 체크
                if (!visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    if (map[nextX][nextY] == 0) {
                        queue.offer(new int[]{nextX, nextY});
                    }
                    // 치즈에 방문하면 치즈의 갯수를 차감 & 치즈 제거
                    // 이 행동으로 인해 치즈로 둘러싸여 있는 안속 치즈는 방문할 일이 발생하지 X
                    else {
                        cheese--;
                        map[nextX][nextY] = 0;
                    }
                }
            }
        }
    }
}
