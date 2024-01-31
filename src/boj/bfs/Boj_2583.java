package boj.bfs;

import java.io.*;
import java.util.*;

// 괜히 N과 M + 그림예제 때문에 초반에 ArrayIndex 예외 발생 == 어렵게 생각말자..
public class Boj_2583 {
    static StringTokenizer st;
    static int M, N, K, w;
    static boolean[][] visited;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], 1);
        }

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int m1 = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int m2 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            for (int m = m1; m < m2; m++) {
                for (int n = n1; n < n2; n++) {
                    map[m][n] = 0;
                }
            }
        }

        List<Integer> list = new ArrayList<>(K);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    w = 1;
                    bfs(i, j);
                    list.add(w);
                }
            }
        }
        Collections.sort(list);

        bw.write(list.size() + "\n");
        StringBuilder str = new StringBuilder();
        for (int num : list) {
            str.append(num).append(" ");
        }
        String result = str.toString().trim();
        bw.write(result);

        bw.flush();
        br.close();
        bw.close();
    }

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] xy = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = xy[0] + dx[i];
                int nextY = xy[1] + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }

                if (!visited[nextX][nextY] && map[nextX][nextY] == 1) {
                    q.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                    map[nextX][nextY] = 0;
                    w++;
                }
            }
        }
    }
}
