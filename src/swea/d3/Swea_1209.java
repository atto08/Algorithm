package swea.d3;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
Sum - D3
소요 시간: 40분
원인: 가로 세로 지정을 서로 반대로함

이전에 풀었던 문제
전 풀이: 가로, 세로, 대각선 2경우의 이중포문 사용 - 260~290ms
재 풀이: bfs로 행,열 대각선 2경우를 넣은 큐 구현 - 170ms - 속도개선
 */
public class Swea_1209 {
    static int[][] map;
    static int max;
    static int[] dx = {0, 1, 1, 1}, dy = {1, 0, 1, -1};
    static Queue<Sum> q;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int test_case = 1; test_case <= 10; test_case++) {
            map = new int[100][100];
            int T = Integer.parseInt(br.readLine());

            StringTokenizer st;
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            max = 0;
            q = new LinkedList<>();
            bfs();

            bw.write("#" + test_case + " " + max + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void bfs() {
        q.offer(new Sum(0, 0, map[0][0], 2)); // 0,0 대각 우하
        q.offer(new Sum(0, 99, map[0][99], 3)); // 0,99 대각 좌하

        for (int i = 0; i < 100; i++) {
            q.offer(new Sum(0, i, map[0][i], 1));// 세로
            q.offer(new Sum(i, 0, map[i][0], 0));// 가로
        }

        while (!q.isEmpty()) {
            Sum now = q.poll();
            if (now.x == 99 || now.y == 99) {
                if (max < now.sum) {
                    max = now.sum;
                }
            }

            int nx = now.x + dx[now.dir];
            int ny = now.y + dy[now.dir];

            if (nx < 0 || ny < 0 || nx > 99 || ny > 99) {
                continue;
            }

            q.offer(new Sum(nx, ny, now.sum + map[nx][ny], now.dir));
        }
    }

    static class Sum {
        int x, y, sum, dir;

        private Sum(int x, int y, int s, int d) {
            this.x = x;
            this.y = y;
            this.sum = s;
            this.dir = d;
        }
    }
}