package boj.bfs;

/*
상범 빌딩 - 골5
소요시간: 55분
층이 존재하는 토마토 문제 유형 bfs
원인:
1) df의 길이가 5였음. ','가 하나 빠져있었음
2) L의 범위가 넘어갈때 중 L > 인 경우만 체크하고 있었음
해결:
1) df 수정
2) L >= 로 바꿔줌
 */

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_6593 {
    static int L, R, C, sX, sY, sF, eX, eY, eF;
    static char[][][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 0, 1, 0, 0, 0}, dy = {0, -1, 0, 1, 0, 0}, df = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            map = new char[L][R][C];
            visited = new boolean[L][R][C];

            for (int l = 0; l < L; l++) { // 층
                for (int i = 0; i < R; i++) {
                    map[l][i] = br.readLine().toCharArray();
                    for (int j = 0; j < C; j++) {
                        if (map[l][i][j] == 'S') {
                            sF = l;
                            sX = i;
                            sY = j;
                        } else if (map[l][i][j] == 'E') {
                            eF = l;
                            eX = i;
                            eY = j;
                        }
                    }
                }
                String igore = br.readLine();
            }

            bfs();
        }
    }

    private static void bfs() {
        Queue<SB> q = new LinkedList<>();
        q.offer(new SB(sX, sY, sF, 0));
        visited[sF][sX][sY] = true;

        String result = "Trapped!";
        while (!q.isEmpty()) {
            SB now = q.poll();

            if (map[now.f][now.x][now.y] == 'E') {
                StringBuilder sb = new StringBuilder();
                sb.append("Escaped in ").append(now.cnt).append(" minute(s).");
                result = sb.toString();
                break;
            }

            for (int i = 0; i < 6; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nf = now.f + df[i];

                if (nx < 0 || ny < 0 || nf < 0 || nx >= R || ny >= C || nf >= L) {
                    continue;
                }

                if (!visited[nf][nx][ny] && map[nf][nx][ny] != '#') {
                    visited[nf][nx][ny] = true;
                    q.offer(new SB(nx, ny, nf, now.cnt + 1));
                }
            }
        }

        System.out.println(result);
    }

    static class SB {
        int x, y, f, cnt;

        private SB(int x, int y, int f, int cnt) {
            this.x = x;
            this.y = y;
            this.f = f;
            this.cnt = cnt;
        }
    }
}
