package boj.bfs;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Boj_2667 {
    static int N, result;
    static boolean[][] visited;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            char[] cArr = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (cArr[j] == '1') {
                    map[i][j] = 1;
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    result = 0;
                    dfs(i, j);
                    list.add(result);
                }
            }
        }

        Collections.sort(list);
        bw.write(list.size() + "\n");
        for (int num : list) {
            bw.write(num + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;
        map[x][y] = 0;
        result++;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
                continue;
            }

            if (!visited[nextX][nextY] && map[nextX][nextY] == 1) {
                dfs(nextX, nextY);
            }
        }
    }
}
