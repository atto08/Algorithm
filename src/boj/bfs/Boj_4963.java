package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_4963 {
    static StringTokenizer st;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};
    static int[][] map;
    static boolean[][] visited;
    static int w, h, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            result = 0;
            map = new int[h][w];
            visited = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        bfs(i, j);
                        result++;
                    }
                }
            }
            bw.write(result + "\n");

        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            int x1 = xy[0];
            int y1 = xy[1];
            for (int i = 0; i < 8; i++) {
                int nextX = x1 + dx[i];
                int nextY = y1 + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= h || nextY >= w) {
                    continue;
                }

                if (!visited[nextX][nextY] && map[nextX][nextY] == 1) {
                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }
    }
}
