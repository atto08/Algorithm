package boj.implementation;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
배열돌리기4 - 골4
1) 틀렸습니다.
==> 회전하는 반복문 내에 문제있는 경우가 있을거라 추측하여 이를 수정
2) 틀렸습니다.

재시도 - 2시간 초과!
원인 >> 마지막 회전 + 백트래킹 실수가 있었음
!백트래킹을 완전히 이해할 수 있도록 시작전에 node 방문순서를 체크하기!
*/
public class Boj_17406 {
    static int N, M, K, result;
    static int[][] rcs;
    static boolean[] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N,M,K 저장
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        // 배열 A 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // rcs 저장
        rcs = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                rcs[i][j] = Integer.parseInt(st.nextToken());
                if (j != 2) {
                    rcs[i][j] -= 1;
                }
            }
        }

        for (int k = 0; k < K; k++) {
            visited = new boolean[K];
            visited[k] = true;
            dfs(k, 1, map);
        }

        System.out.println(result);
    }

    private static void dfs(int node, int depth, int[][] map) {

        // 시작점, 끝점
        int rms = rcs[node][0] - rcs[node][2];
        int cms = rcs[node][1] - rcs[node][2];
        int rps = rcs[node][0] + rcs[node][2];
        int cps = rcs[node][1] + rcs[node][2];

        if (depth == K) {
            // Point ! 마지막 회전
            int[][] map2 = rotate(rms, cms, rps, cps, map);
            // 모든 행의 합 중 최소 값 선별
            int[] sumArr = new int[N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    sumArr[i] += map2[i][j];
                }
            }

            Arrays.sort(sumArr);
            if (result == 0 || result > sumArr[0]) {
                result = sumArr[0];
            }

            return;
        }

        for (int k = 0; k < K; k++) {
            if (!visited[k]) {
                // 배열 돌리기
                visited[k] = true;
                dfs(k, depth + 1, rotate(rms, cms, rps, cps, map));
                // 백 트래킹
                visited[k] = false;
            }
        }
    }

    private static int[][] rotate(int startX, int startY, int endX, int endY, int[][] map) {

        int[][] map2 = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map2[i][j] = map[i][j];
            }
        }

        int stX = startX, stY = startY, edX = endX, edY = endY;
        while (stX < edX && stY < edY) {
            int temp = map2[stX][edY];

            int x = stX, y = edY;
            int idx = 0;
            while (idx < 4) {
                int nx = x + dx[idx];
                int ny = y + dy[idx];

                // 벽이 아니면 계속 회전
                if (nx >= stX && ny >= stY && nx <= edX && ny <= edY) {
                    map2[x][y] = map2[nx][ny];
                    x = nx;
                    y = ny;
                }
                // 벽 만나면 방향 전환
                else {
                    idx++;
                }
            }
            map2[stX + 1][edY] = temp;

            stX++;
            stY++;
            edX--;
            edY--;
        }
        return map2;
    }
}