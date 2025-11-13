package boj.implementation;

import java.util.*;
import java.io.*;

/*
감시 - 골3
소요시간 - 90분

[풀이설명]
- map[][]: 맵의 정보를 나타내는 빈칸(0), cctv(1~5), 벽(6)
- min: 결과 값(가능한 사각지대 최소 크기)
- CCTV: cctv 별로 cctv 종류(번호)와 위치(x,y)를 기억하기 위한 타입
- repeat[][]: cctv 별로 감시 가능한 방향을 따로 저장. 1~5 인덱스 사용
- dx, dy[]: 상 우 하 좌 순으로 탐색
- visited[][]: 감시되는 구역 여부 판단

- 접근 방법: CCTV 갯수만큼 가능한 방향의 조합을 모두 탐색하는 방향으로 구현
- DFS 알고리즘을 활용해 List<CCTV>의 갯수만큼 인덱스 차례로 CCTV 확인
- CCTV 별로 감시 가능한 방향 전부 탐색
    - 방향 모두 감시 처리
    - 다음 카메라 탐색 DFS
    - 방향 모두 감시 처리 해제

p.s
- 지난번 카페에서 시도했다가 집중력 저하 이슈로 못 푼 문제.
- print 메서드 만들어서 찍어보는거 좋은거같음.
- 집에서 빡세게 집중하면 그래도 푸는구나. 그나마 다행이라 생각한다. 시간은 좀 더 줄일 수 있도록 열심히하자.
 */
public class Boj_15683 {
    static int[][] map;
    static int N, M, min;
    static List<CCTV> cctvPos;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int[][] repeat = {{0, 0}, {4, 1}, {2, 2}, {4, 2}, {4, 3}, {1, 4}};

    static class CCTV {
        int x, y, num;

        public CCTV(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    private static void printVisit() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(1 + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void checkBoundary(CCTV cctv, int dir, Queue<int[]> q) {

        int nx = cctv.x, ny = cctv.y;
        while (true) {
            nx += dx[dir];
            ny += dy[dir];
            // 범위 벗어나면 종료
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                break;
            }
            // 벽 or CCTV
            if (map[nx][ny] > 0) {
                if (map[nx][ny] < 6) { // CCTV 통과
                    continue;
                } else { // 벽 종료
                    break;
                }
            }

            if (!visited[nx][ny]) {
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    private static void dfs(int depth, int cnt) {
        if (depth == cctvPos.size()) {
            min = Math.min(min, cnt);
            return;
        }
//        printVisit();
        CCTV cctv = cctvPos.get(depth);
        for (int i = 0; i < repeat[cctv.num][0]; i++) {
            Queue<int[]> xy = new LinkedList<>();
            if (cctv.num == 2) {
                // d 방향으로 전부 방문처리
                for (int j = i; j <= i + repeat[cctv.num][1]; j += 2) {
                    int d = j % 4;
                    checkBoundary(cctv, d, xy);
                }
                // DFS
                dfs(depth + 1, cnt - xy.size());
                // d 방향 전부 방문 X처리
                while (!xy.isEmpty()) {
                    int[] now = xy.poll();
                    visited[now[0]][now[1]] = false;
                }
            } else {
                // d 방향으로 전부 방문처리
                for (int j = i; j < i + repeat[cctv.num][1]; j++) {
                    int d = j % 4;
                    checkBoundary(cctv, d, xy);
                }
                // DFS
                dfs(depth + 1, cnt - xy.size());
                // d 방향 전부 방문 X처리
                while (!xy.isEmpty()) {
                    int[] now = xy.poll();
                    visited[now[0]][now[1]] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        cctvPos = new ArrayList<>();
        int notBlank = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) {
                    notBlank++;
                    visited[i][j] = true;
                    if (map[i][j] < 6) {
                        cctvPos.add(new CCTV(i, j, map[i][j]));
                    }
                }
            }
        }

        min = N * M - notBlank;
        dfs(0, min);

        System.out.println(min);
    }
}