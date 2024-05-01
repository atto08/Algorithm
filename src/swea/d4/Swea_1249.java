package swea.d4;

import java.io.*;
import java.util.PriorityQueue;
/*
보급로 - D4
소요 시간: 31분
방문체크를 하되 우선순위 큐를 이용해 가장 적은 복구시간이 걸리는 녀석들을 우선 방문하도록 구현함
>> 스스로 우선순위 큐를 구현해서 사용한 첫 문제!
 */
public class Swea_1249 {
    static int N, result;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] arr = br.readLine().split("");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(arr[j]);
                }
            }
            visited = new boolean[N][N];
            result = 0;
            bfs();

            bw.write("#" + test_case + " " + result + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void bfs() {
        PriorityQueue<Pair> q = new PriorityQueue<>();
        q.offer(new Pair(0, 0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Pair now = q.poll();

            if (now.x == N - 1 && now.y == N - 1) {
                result = now.w;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new Pair(nx, ny, now.w + map[nx][ny]));
                }
            }
        }
    }

    static class Pair implements Comparable<Pair> {
        int x, y, w;

        private Pair(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.w, other.w);
        }
    }
}
