package boj.bfs;

import java.util.PriorityQueue;
import java.util.Scanner;
/*
아기 상어 - 골3
 */
public class Boj_16236 {
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    static int[][] map;

    static class Shark implements Comparable<Shark> {
        int y, x, dist;

        public Shark(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }

        @Override
        public int compareTo(Shark o) {
            // 1) 이동거리(dist) 오름차순
            if (this.dist != o.dist) {
                return Integer.compare(this.dist, o.dist);
            }
            // 2) y(행) 오름차순
            if (this.y != o.y) {
                return Integer.compare(this.y, o.y);
            }
            // 3) x(열) 오름차순
            return Integer.compare(this.x, o.x);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        map = new int[N][N];
        int curY = 0, curX = 0;

        // 상어 초기 위치 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    curY = i;
                    curX = j;
                    map[i][j] = 0;
                }
            }
        }

        int size = 2, eat = 0, move = 0;

        while (true) {
            PriorityQueue<Shark> pq = new PriorityQueue<>();
            boolean[][] visited = new boolean[N][N];

            pq.add(new Shark(curY, curX, 0));
            visited[curY][curX] = true;

            boolean ate = false;

            while (!pq.isEmpty()) {
                Shark cur = pq.poll();

                // 먹을 수 있는 물고기 발견
                if (map[cur.y][cur.x] != 0 && map[cur.y][cur.x] < size) {
                    map[cur.y][cur.x] = 0;
                    eat++;
                    move += cur.dist;
                    curY = cur.y;
                    curX = cur.x;
                    ate = true;
                    break;
                }

                // 4방 탐색
                for (int d = 0; d < 4; d++) {
                    int ny = cur.y + dy[d];
                    int nx = cur.x + dx[d];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                    if (visited[ny][nx] || map[ny][nx] > size) continue;

                    visited[ny][nx] = true;
                    pq.add(new Shark(ny, nx, cur.dist + 1));
                }
            }

            if (!ate) break;         // 더 이상 먹을 물고기 없음
            if (eat == size) {       // 사이즈 증가
                size++;
                eat = 0;
            }
        }

        System.out.println(move);
        sc.close();
    }
}
