package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
불 - 골4
소요 시간: 58분

불!(4179) 문제와 같은 BFS 문제라고 생각함.

1) 통과
 */
public class Boj_5427 {

    static class Fire {
        int x, y, s, t;

        private Fire(int x, int y, int s) {
            this.x = x;
            this.y = y;
            this.s = s;
        }

        private Fire(int x, int y, int s, int t) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.t = t;
        }
    }

    static int W, H;
    static int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            char[][] map = new char[H][W];
            for (int i = 0; i < H; i++) map[i] = br.readLine().toCharArray();

            bw.write(escapeBuilding(map) + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static String escapeBuilding(char[][] map) {
        boolean[][] visited = new boolean[H][W];
        Queue<Fire> q = new LinkedList<>();
        int sx = 0, sy = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == '*') {
                    q.offer(new Fire(i, j, -1));
                } else if (map[i][j] == '@') {
                    visited[i][j] = true;
                    sx = i;
                    sy = j;
                }
            }
        }
        q.offer(new Fire(sx, sy, 1, 0));

        String minTime = "IMPOSSIBLE";
        while (!q.isEmpty()) {
            Fire current = q.poll();

            if (current.s == 1 && (current.x == 0 || current.y == 0 || current.x == H - 1 || current.y == W - 1)) {
                minTime = String.valueOf(current.t + 1);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;

                if (current.s < 0) { // 불
                    if (map[nx][ny] == '.' || map[nx][ny] == '@') {
                        map[nx][ny] = '*';
                        q.offer(new Fire(nx, ny, -1));
                    }
                } else { // 상근
                    if (!visited[nx][ny] && map[nx][ny] == '.') {
                        visited[nx][ny] = true;
                        q.offer(new Fire(nx, ny, 1, current.t + 1));
                    }
                }
            }
        }

        return minTime;
    }
}
