package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
양치기 꿍 - 실1
소요 시간 - 33분

1) 시작점이 울타리인 경우 놓침
2) 시작점이 양과 늑대인 경우를 놓침
 */
public class Boj_3187 {
    static int R, C, K, V;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            char[] now = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                map[r][c] = now[c];
                if (now[c] == 'v') {
                    V++;
                } else if (now[c] == 'k') {
                    K++;
                }
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!visited[r][c] && map[r][c] != '#') {
                    bfs(r, c);
                }
            }
        }
        System.out.println(K + " " + V);
    }

    private static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        visited[r][c] = true;
        int k = 0;
        int v = 0;

        if (map[r][c] == 'k') {
            k++;
        } else if (map[r][c] == 'v') {
            v++;
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    continue;
                }

                if (!visited[nr][nc] && map[nr][nc] != '#') {
                    visited[nr][nc] = true;
                    if (map[nr][nc] == 'k') {
                        k++;
                    } else if (map[nr][nc] == 'v') {
                        v++;
                    }
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        if (k > v) {
            V -= v;
        } else {
            K -= k;
        }
    }
}
