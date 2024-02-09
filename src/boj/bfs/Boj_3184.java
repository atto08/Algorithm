package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_3184 {
    static int N, M, s, w, o, v;
    static boolean[][] visited;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        s = 0;
        w = 0;

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'o') {
                    s++;
                } else if (map[i][j] == 'v') {
                    w++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && (map[i][j] == 'o' || map[i][j] == 'v')) {
                    o = 0;
                    v = 0;
                    bfs(i, j);
                    if (o > v) {
                        w -= v;
                    } else {
                        s -= o;
                    }
                }
            }
        }
        System.out.println(s + " " + w);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        if (map[x][y] == 'o') {
            o++;
        } else {
            v++;
        }

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            int x1 = xy[0];
            int y1 = xy[1];

            for (int i = 0; i < 4; i++) {
                int nextX = x1 + dx[i];
                int nextY = y1 + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }

                if (!visited[nextX][nextY] && map[nextX][nextY] != '#') {
                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                    if (map[nextX][nextY] == 'o') {
                        o++;
                    } else if (map[nextX][nextY] == 'v') {
                        v++;
                    }
                }
            }
        }
    }
}
