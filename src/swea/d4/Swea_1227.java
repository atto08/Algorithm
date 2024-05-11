package swea.d4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
/*
미로2 - D4
소요 시간: 20분
bfs 최단거리 찾는 대표유형문제
복습차 다시 풀어봄
 */
public class Swea_1227 {
    static int result;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int test_case = 1; test_case <= 10; test_case++) {
            int T = Integer.parseInt(br.readLine());

            map = new char[100][100];
            visited = new boolean[100][100];
            for (int i = 0; i < 100; i++) {
                map[i] = br.readLine().toCharArray();
            }

            result = 0;
            bfs();
            bw.write("#" + T + " " + result + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{1, 1});
        visited[1][1] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            int x = now[0], y = now[1];
            if (map[x][y] == '3') {
                result = 1;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!visited[nx][ny] && map[nx][ny] != '1') {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }
}

