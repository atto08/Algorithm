package boj.implementation;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
지구 온난화 - 실2
소요 시간 - 52분

면적이 작아지는 방법을 생각하는데 시간이 오래걸림
 */
public class Boj_5212 {
    static int R, C, sx, sy, ex, ey;
    static char[][] map, result;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        result = new char[R][C];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                result[i][j] = map[i][j];
                if (map[i][j] == 'X') q.offer(new int[]{i, j});
            }
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];

            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                    cnt++;
                    continue;
                }

                if (map[nx][ny] == '.') cnt++;
            }

            if (cnt > 2) result[x][y] = '.';
        }

        sx = R;
        sy = C;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (result[i][j] == 'X') {
                    if (sx > i) sx = i;
                    if (sy > j) sy = j;
                    if (ex < i) ex = i;
                    if (ey < j) ey = j;
                }
            }
        }

        for (int i = sx; i <= ex; i++) {
            for (int j = sy; j <= ey; j++) bw.write(result[i][j]);
            bw.write("\n");
        }
        bw.flush();
    }
}
