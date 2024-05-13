package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
파이프 옮기기1 - 골5
소요 시간: 80분 초과
1) 풀지 못함
원인: 중간에 대각,우,하 방향으로 가기 위해서는 각 방향이 갈수 있는 색칠된 칸에 모두 갈수 있어야 갈수 있다는 조건을 읽음
>> 이 조건을 그냥 벽과 맵을 넘어가면 다 갈 수 있는 경우라고 읽고 풀었음

소요 시간: +50분
2) 시간 초과(89%)
원인: 완전탐색을 하면서 중복되는 경우로 인한 시간초과라고 생각됨
해결: (N,N) == 1인 경우를 예외처리 해주었는데 통과됨
>> 테스트 케이스 안에 (N,N) == 1인 경우가 있다고 생각되지만 정확한 원인이 무엇이였는지는 모르겠음.
 */
public class Boj_17070 {
    static int N, result;
    static int[][] map;
    static int[] dr = {0, 1, 1}, dc = {1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;
        if(map[N][N] != 1){
            bfs();
        }
        System.out.println(result);
    }

    static class Pipe {
        int r1, c1, r2, c2, dir;

        private Pipe(int r1, int c1, int r2, int c2, int dir) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.dir = dir;
        }
    }

    // dir = ) 0 = 가로 상태 / 1 = 대각선 상태 / 2 = 세로 상태
    private static void bfs() {

        Queue<Pipe> q = new LinkedList<>();
        q.offer(new Pipe(1, 1, 1, 2, 0)); // 시작점에서 도착점에 걸치는 경우를 찾는 방식

        while (!q.isEmpty()) {
            Pipe now = q.poll();

            if (now.r2 == N && now.c2 == N) {
                result++;
            }

            if (now.dir == 0) { // 파이프의 방향 = 가로 상태
                for (int i = 0; i < 2; i++) {
                    int nr = now.r2 + dr[i];
                    int nc = now.c2 + dc[i];

                    if (nr > N || nc > N) {
                        continue;
                    }

                    if (i == 1) { // 대각선으로 가려면 세방향을 모두 확인해줘야함
                        int cnt = canGo(now.r2, now.c2);
                        if (cnt == 3) { // 갈수 있을때만
                            if (map[nr][nc] == 0) {
                                q.offer(new Pipe(now.r2, now.c2, nr, nc, 1));
                            }
                        }
                    } else { // 가로방향 한방향 확인
                        if (map[nr][nc] == 0) {
                            q.offer(new Pipe(now.r2, now.c2, nr, nc, 0));
                        }
                    }
                }
            } else if (now.dir == 1) { // 파이프 방향 = 대각선 상태
                for (int i = 0; i < 3; i++) {
                    int nr = now.r2 + dr[i];
                    int nc = now.c2 + dc[i];

                    if (nr > N || nc > N) {
                        continue;
                    }

                    if (i == 1) { // 대각선 세방향 모두 확인
                        int cnt = canGo(now.r2, now.c2);
                        if (cnt == 3) { // 갈수 있을때만
                            if (map[nr][nc] == 0) {
                                q.offer(new Pipe(now.r2, now.c2, nr, nc, 1));
                            }
                        }
                    } else { // 가로/세로 방향 한방향 확인
                        if (map[nr][nc] == 0) {
                            q.offer(new Pipe(now.r2, now.c2, nr, nc, i));
                        }
                    }
                }
            } else { // 파이프 방향 = 세로 상태
                for (int i = 1; i < 3; i++) {
                    int nr = now.r2 + dr[i];
                    int nc = now.c2 + dc[i];

                    if (nr > N || nc > N) {
                        continue;
                    }

                    if (i == 1) { // 대각선 세방향 모두 확인
                        int cnt = canGo(now.r2, now.c2);
                        if (cnt == 3) { // 갈수 있을때만
                            if (map[nr][nc] == 0) {
                                q.offer(new Pipe(now.r2, now.c2, nr, nc, 1));
                            }
                        }
                    } else { // 세로 한방향 확인
                        if (map[nr][nc] == 0) {
                            q.offer(new Pipe(now.r2, now.c2, nr, nc, 2));
                        }
                    }
                }
            }
        }
    }

    private static int canGo(int r, int c) {
        int cnt = 0;

        for (int j = 0; j < 3; j++) {
            int nr = r + dr[j];
            int nc = c + dc[j];

            if (nr > N || nc > N) {
                continue;
            }

            if (map[nr][nc] == 1) {
                continue;
            }

            cnt++;
        }
        return cnt;
    }
}
