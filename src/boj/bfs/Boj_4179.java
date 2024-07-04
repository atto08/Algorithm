package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
불! - 골3
소요 시간: 55분

1) 메모리 초과(2%)
원인: 큐에 무수히 많은 경우의 수가 들어감
해결: visited 이차원 배열을 3차원 배열로 바꿔 - 지훈이가 방문하는 경우[0]와 불이 번진 경우[1]로 수정 후 체크

소요 시간: +12분
2) 틀렸습니다(52%)
원인: 지훈이 움직이기 전에 불에 닿으면 이동이 불가 해야됨.
해결: 지훈 경로 탐색 후 불 탐색 ->> 불 탐색 후 지훈이 경로 탐색 순서로 뒤바꿈

3) 통과
 */
public class Boj_4179 {
    static int R, C;
    static char[][] maze;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static boolean[][][] visited;
    static Queue<Fire> q = new LinkedList<>();

    static class Fire {
        int x, y, cnt;
        char kind;

        private Fire(int x, int y, char kind) {
            this.x = x;
            this.y = y;
            this.kind = kind;
        }

        private Fire(int x, int y, int cnt, char kind) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.kind = kind;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maze = new char[R][C];
        visited = new boolean[R][C][2];

        int x = 0, y = 0;
        for (int i = 0; i < R; i++) {
            maze[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (maze[i][j] == 'F') {
                    visited[i][j][1] = true;
                    q.offer(new Fire(i, j, maze[i][j]));
                } else if (maze[i][j] == 'J') {
                    x = i;
                    y = j;
                }
            }
        }
        bfs(x, y);
    }

    private static void bfs(int stX, int stY) {

        q.offer(new Fire(stX, stY, 1, maze[stX][stY]));
        visited[stX][stY][0] = true;

        boolean canEscape = false;
        while (!q.isEmpty()) {
            Fire now = q.poll();

            if (now.kind == 'J' & (now.x == 0 || now.x == R - 1 || now.y == 0 || now.y == C - 1)) {
                canEscape = true;
                System.out.println(now.cnt);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

                if (now.kind == 'J') {
                    if (maze[nx][ny] == '.' && !visited[nx][ny][1] && !visited[nx][ny][0]) {
                        visited[nx][ny][0] = true;
                        q.offer(new Fire(nx, ny, now.cnt + 1, now.kind));
                    }
                } else {
                    if (!visited[nx][ny][1] && maze[nx][ny] != '#') {
                        visited[nx][ny][1] = true;
                        maze[nx][ny] = 'F';
                        q.offer(new Fire(nx, ny, now.kind));
                    }
                }
            }
        }
        if (!canEscape) {
            System.out.println("IMPOSSIBLE");
        }
    }
}
