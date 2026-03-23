package boj.twice;

import java.io.*;
import java.util.*;

/*
연구소 - 골4
소요시간 - 120분

[문제 설명]
- 목표: 맵에 벽 3개를 세워서 만들 수 있는 '안전 영역(0)'의 최댓값 구하기
- 조건: 빈칸(0), 벽(1), 바이러스(2) / 바이러스는 상하좌우 빈칸으로 전파됨

[풀이 로직: 완전 탐색(조합) + BFS]
1. 백트래킹(DFS)을 활용해 빈칸(0) 중 벽을 세울 3개의 좌표 조합을 모두 구한다.
2. 벽이 3개 세워질 때마다 BFS를 통해 바이러스를 전파시킨다.
3. 바이러스 전파가 끝난 후, 남은 안전 영역의 크기를 계산하여 최댓값을 갱신한다.

[회고]
- 시간 복잡도 우려: 브루트포스라 시간 초과를 걱정했으나, N과 M의 최대 크기가 8로 매우 작아 전체 탐색으로 충분히 통과 가능했음.
- 2차원 배열의 조합 탐색: 벽을 세울 때 앞서 방문한 칸을 중복 탐색하지 않도록 2중 반복문 인덱스를 제어하는 부분에서 헷갈림이 있었으나, 로직 자체는 정상이었음.
- 🚨 트러블 슈팅: 참조 타입(Reference Type) 매개변수 전달 이슈
  -> 증상: 첫 번째 탐색 이후 백트래킹이 정상적으로 진행되지 않음. 앞서 작성한 탐색 반복문 문제인 줄 알았음.
  -> 원인: DFS에서 넘겨준 2차원 배열(cpMap)을 BFS 내부에서 직접 수정(바이러스 전파)해버린 것이 원인.
  -> 깨달음: 자바에서 원시 타입은 값 자체가 복사되지만, 배열과 같은 참조 타입은 '메모리 주소값'이 전달됨. 따라서 매개변수로 넘긴 배열을 조작하면 원본 배열이 오염됨. 시뮬레이션(BFS) 시에는 반드시 원본 상태 유지를 위해 배열을 한 번 더 복사(Deep Copy)해서 사용해야 함!

p.s
- 그래도 기능별로 구현해 각각 단위 테스트 선 진행 후 통합테스트를 진행하는 습관이 생긴거 같다.
- 기본 개념이 흔들렸던 부분에서 실수가 나왔지만, 전화위복으로 더 성장해보자.
 */
public class BOJ_14502 {
    static int N, M, max, wallCnt;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        wallCnt = 3; // 세울 벽 3개
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    wallCnt++;
                }
            }
        }

        max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 빈칸이면 벽을 세워본다
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(i, j, map, 1);
                    map[i][j] = 0;
                }
            }
        }

        System.out.println(max);
    }

    // 1) 벽 세우기
    private static void dfs(int x, int y, int[][] map, int cnt) {

        // 벽 3개 세운 상태
        if (cnt == 3) {
            // 바이러전파 및 안전영역 확인
            bfs(map);
            return;
        }

        int nx = x, ny = y;
        while (nx < N) {
            while (ny < M) {
                if (map[nx][ny] == 0) {
                    map[nx][ny] = 1;
                    dfs(nx, ny, map, cnt + 1);
                    map[nx][ny] = 0;
                }
                ny++;
            }
            nx++;
            ny = 0;
        }
    }

    // 2) 바이러스 전파 & 안전영역 확인
    private static void bfs(int[][] map) {
        int[][] cpMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cpMap[i][j] = map[i][j];
            }
        }

        // 바이러스 좌표 입력
        Queue<Virus> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cpMap[i][j] == 2) {
                    q.offer(new Virus(i, j));
                }
            }
        }

        // 바이러스 전파
        int virusCnt = 0; // 바이러스 영역 수
        while (!q.isEmpty()) {
            Virus now = q.poll();

            virusCnt++;
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                // 영역 벗어나면 무시
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                // 벽이나 바이러스면 무시
                if (cpMap[nx][ny] > 0)
                    continue;

                // 영역 바이러스 전파
                q.offer(new Virus(nx, ny));
                cpMap[nx][ny] = 2;
            }
        }

        // 전체영역 - (감염된 영역 + 벽의 갯수) = 안전영역
        int ground = N * M - (virusCnt + wallCnt);
        max = Math.max(max, ground);
    }

    static class Virus {
        int x, y;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}