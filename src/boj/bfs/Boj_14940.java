package boj.bfs;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
블로그 참고: https://wiselog.tistory.com/163
 */
public class Boj_14940 {
    static StringTokenizer st;
    static int N, M, nr, mr;
    static boolean[][] visited;
    static int[][] map, depth;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        depth = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(depth[i], -1);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 2) {
                    nr = i;
                    mr = j;
                } else if (num == 0) {
                    depth[i][j] = 0;
                }
                map[i][j] = num;
            }
        }

        bfs();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j != M - 1) {
                    bw.write(depth[i][j] + " ");
                } else {
                    bw.write(depth[i][j] + "");
                }
            }
            bw.write("\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{nr, mr});
        visited[nr][mr] = true;
        depth[nr][mr] = 0;

        while (!q.isEmpty()) {
            int[] xy = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = xy[0] + dx[i];
                int y = xy[1] + dy[i];

                // index 나가는거 체크를 이렇게 하다니 대단하다 .
                if (x < 0 || y < 0 || x >= N || y >= M) {
                    continue;
                }
                if (!visited[x][y] && map[x][y] == 1) {
                    q.offer(new int[]{x, y});
                    visited[x][y] = true;
                    depth[x][y] = depth[xy[0]][xy[1]] + 1;
                }
            }
        }
    }
}
