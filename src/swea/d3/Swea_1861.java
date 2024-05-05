package swea.d3;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
정사각형 방 - D3
소요 시간: 28분
현재 방보다 1이 더커야만 이동할 수 있기 때문에 방문처리가 필요하지 않음
모든 방에서 시작하는 경우에서 가장 많은 방을 방문하고
그 가장많이 방문하는 횟수가 동률인 경우엔 숫자가 더 낮은 방을 기억하도록 구현하면 되는 간단문제
 */
public class Swea_1861 {
    static int N, room, max;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            max = 0;
            room = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    bfs(i, j);
                }
            }

            bw.write("#" + test_case + " " + room + " " + max + "\n");

        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void bfs(int x, int y) {

        int node = map[x][y], visitCnt = 1;
        Queue<Room> q = new LinkedList<>();
        q.offer(new Room(x, y, visitCnt));

        while (!q.isEmpty()) {
            Room now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }

                if (map[nx][ny] == map[now.x][now.y] + 1) {
                    visitCnt++;
                    q.offer(new Room(nx, ny, visitCnt));
                }
            }
        }

        if (max == visitCnt) {
            if (node < room) {
                room = node;
            }
        } else if (max < visitCnt) {
            max = visitCnt;
            room = node;
        }
    }

    static class Room {
        int x, y, visit;

        private Room(int x, int y, int visit) {
            this.x = x;
            this.y = y;
            this.visit = visit;
        }
    }
}
