package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
시그널 - 실1
소요 시간: 52분

bfs 응용해서 구현. 각 숫자마다 칸을 차지하는 갯수가 존재하는데 2,3,5는 11칸 0,6,9는 12칸
이 겹치는 부분은 경우를 조건문으로 각 숫자별 칠해지지않는 부분 or 칠해지는 부분을 구분하여 구현함
 */
public class Boj_16113 {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static StringBuilder sb;
    static int[] numbers = {0, 0, 0, 0, 0, 1, 0, 7, 0, 4, 0, -1, -1, 8};
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = N / 5;

        map = new char[5][M];
        visited = new boolean[5][M];
        String message = br.readLine();
        for (int i = 0; i < 5; i++) {
            map[i] = message.substring(i * M, (i + 1) * M).toCharArray();
        }

        sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == '#') {
                    bfs(i, j);
                }
            }
        }

        System.out.println(sb.toString());
    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new int[]{x, y});

        int cnt = 0;
        boolean[][] check = new boolean[5][3];
        while (!q.isEmpty()) {
            int[] now = q.poll();
            cnt++;
            check[now[0] - x][now[1] - y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= M) {
                    continue;
                }

                if (!visited[nx][ny] && map[nx][ny] == '#') {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        if (cnt == 11) {
            if (check[1][2] && check[3][0]) {
                sb.append(2);
            } else if (check[1][2] && check[3][2]) {
                sb.append(3);
            } else {
                sb.append(5);
            }
        } else if (cnt == 12) {
            if (!check[2][1]) {
                sb.append(0);
            } else if (!check[1][2] && check[3][0]) {
                sb.append(6);
            } else {
                sb.append(9);
            }
        } else {
            sb.append(numbers[cnt]);
        }
    }
}
