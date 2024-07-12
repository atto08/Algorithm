package boj.bfs;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
녹색 옷 입은 애가 젤다지? - 골4
소요 시간: 40분

다익스트라 알고리즘 유형(2차원 배열) - 학습!
>> (2차원 배열 에서 다익스트라 알고리즘 사용 및 적용 방법 새로 배움)
 */
public class Boj_4485 {

    static class Zelda implements Comparable<Zelda> {
        int x, y, c;

        private Zelda(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(Zelda other) {
            return Integer.compare(this.c, other.c);
        }
    }

    static int N;
    static int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        int problem = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) break;

            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bw.write("Problem " + problem + ": " + dijkstra(map) + "\n");
            problem++;
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static int dijkstra(int[][] map) {

        int[][] dist = new int[N][N];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = map[0][0];

        PriorityQueue<Zelda> pq = new PriorityQueue<>();
        pq.offer(new Zelda(0, 0, map[0][0]));

        while (!pq.isEmpty()) {

            Zelda current = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                int nc = current.c + map[nx][ny];
                if (nc < dist[nx][ny]) {
                    dist[nx][ny] = nc;
                    pq.offer(new Zelda(nx, ny, nc));
                }
            }
        }
        return dist[N - 1][N - 1];
    }
}
