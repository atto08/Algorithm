package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2178 {
    static int N, M;
    static int[][] maze, depth;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new int[N][M];
        depth = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                maze[i][j] = arr[j];
            }
        }
        bfs();

        System.out.println(depth[N-1][M-1]);
        br.close();
    }

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        int x = 0;
        int y = 0;
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        depth[x][y] = 1;

        while (!q.isEmpty()) {
            int[] xy = q.poll();
            for (int i = 0; i < 4; i++) {
                x = xy[0] + dx[i];
                // '+' 가 '=' 으로 되어있었다. 오타 조심
                y = xy[1] + dy[i];

                if (x < 0 || y < 0 || x >= N || y >= M) {
                    continue;
                }

                if (!visited[x][y] && maze[x][y] == '1') {
                    q.offer(new int[]{x, y});
                    visited[x][y] = true;
                    depth[x][y] = depth[xy[0]][xy[1]] + 1;
                }
            }
        }
    }
}
