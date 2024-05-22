package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
뱀과 사다리 게임 - 골5
소요 시간: 54분
1) 메모리 초과
원인: 도착한 칸 기준 1~6 주사위를 돌린 경우는 항상 같기 때문에 재방문할 이유가 없음
해결: 방문처리 추가

소요 시간: 30분
2) 통과
 */
public class Boj_16928 {
    static int N, M;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[11][10];

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) { // 사다리 정보
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x / 10][x % 10] = y;
        }

        for (int i = 0; i < M; i++) { // 뱀 정보
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            map[u / 10][u % 10] = v;
        }

        visited = new boolean[101];
        bfs();
    }

    private static void bfs() {
        Queue<Dice> q = new LinkedList<>();
        q.offer(new Dice(0, 1, 0));
        visited[1] = true;

        while (!q.isEmpty()) {
            Dice now = q.poll();

            if (now.x == 10 && now.y == 0) {
                System.out.println(now.cnt);
                break;
            }
            for (int dice = 1; dice <= 6; dice++) {
                int nextNum = now.x * 10 + now.y + dice;

                int nx = nextNum / 10;
                int ny = nextNum % 10;

                if (nx < 0 || ny < 0 || nx > 10 || nextNum > 100) {
                    continue;
                }

                if (!visited[nextNum]) {
                    visited[nextNum] = true;
                    if (map[nx][ny] > 0) {
                        int number = map[nx][ny];
                        nx = number / 10;
                        ny = number % 10;

                    }
                    q.offer(new Dice(nx, ny, now.cnt + 1));
                }
            }
        }
    }

    static class Dice {
        int x, y, cnt;

        private Dice(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
