package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_7562 {
    static StringTokenizer st;
    static int[] dx = {-2, -2, 2, 2, -1, 1, -1, 1};
    static int[] dy = {-1, 1, -1, 1, -2, -2, 2, 2};
    static int[][] map;
    static boolean[][] visited;
    static int T, l, sx, sy, ex, ey;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {

            l = Integer.parseInt(br.readLine());
            map = new int[l][l];
            visited = new boolean[l][l];

            st = new StringTokenizer(br.readLine());
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            ex = Integer.parseInt(st.nextToken());
            ey = Integer.parseInt(st.nextToken());

            bfs(sx, sy);

            bw.write(map[ex][ey] + "\n");

        }

        bw.flush();
        bw.close();
        br.close();

    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, 0});
        visited[x][y] = true;
        map[x][y] = 0;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            int x1 = xy[0];
            int y1 = xy[1];
            int count = ++xy[2];
            if (x1 == ex && y1 == ey) {
                break;
            }

            for (int i = 0; i < 8; i++) {
                int nextX = x1 + dx[i];
                int nextY = y1 + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= l || nextY >= l) {
                    continue;
                }

                if (!visited[nextX][nextY]) {
                    map[nextX][nextY] = count;
                    visited[nextX][nextY] = true;
                    queue.offer(new int[]{nextX, nextY, count});
                }
            }
        }
    }
}
