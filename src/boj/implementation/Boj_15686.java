package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
1) 메모리 초과
소요 시간: 96분
원인: 모든 집에서 가장 가까운 치킨 집을 찾도록 함
==> queue의 크기가 메모리 초과를 발생시킴
해결: M개의 치킨 집에서 동시 다발적으로 집을 찾도록 함

2) 시간 초과
소요 시간: +36분
원인: 쓸데없이 방문한 모든 치킨 집을 방문함(중복순열 체크 X) >> 시간초과 발생
해결: dfs 함수에서 선택된 치킨 집의 인덱스를 파라미터로 전달하여 해당 치킨 집 다음부터 시작하도록 수정
>> 중복 계산을 피하고 시간 복잡도 감소
 */
public class Boj_15686 {
    static int N, M, homeCnt, chickenCnt, min;
    static int[][] map, home, chicken;
    static boolean[] visited;
    static boolean[][] mapVisited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 도시 정보 입력
        map = new int[N + 1][N + 1];
        Queue<int[]> house = new LinkedList<>();
        Queue<int[]> bbq = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    homeCnt++;
                    house.offer(new int[]{i, j});
                } else if (map[i][j] == 2) {
                    chickenCnt++;
                    bbq.offer(new int[]{i, j});
                }
            }
        }
        // 집 위치 입력
        home = new int[homeCnt + 1][2];
        for (int i = 1; i <= homeCnt; i++) {
            int[] h = house.poll();
            home[i][0] = h[0];
            home[i][1] = h[1];
        }
        // 치킨 집 위치 입력
        chicken = new int[chickenCnt + 1][2];
        for (int i = 1; i <= chickenCnt; i++) {
            int[] c = bbq.poll();
            chicken[i][0] = c[0];
            chicken[i][1] = c[1];
        }
        // 돌기
        for (int i = 1; i <= chickenCnt; i++) {
            visited = new boolean[chickenCnt + 1];
            visited[i] = true;
            dfs(i, 1);
        }
        System.out.println(min);
    }

    private static void dfs(int node, int depth) {

        System.out.println("node: " + node + " depth: " + depth);
        if (depth == M) {
            // 치킨집 M개만 유지
            for (int i = 1; i <= chickenCnt; i++) {
                if (!visited[i]) {
                    map[chicken[i][0]][chicken[i][1]] = 0;
                }
            }
            // M개의 치킨 집에서 가까운 집들을 찾아 치킨 거리의 최소 값 구하기
            mapVisited = new boolean[N + 1][N + 1];
            bfs();
            // 다시 치킨 집 원 위치
            for (int i = 1; i <= chickenCnt; i++) {
                if (!visited[i]) {
                    map[chicken[i][0]][chicken[i][1]] = 2;
                }
            }
            return;
        }

        // 중복 순열 제거를 위해 인덱스 node부터 탐색
        for (int i = node; i <= chickenCnt; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, depth + 1);
                visited[i] = false;
            }
        }
    }


    // 치킨 거리 구하기
    private static void bfs() {

        int distanceMin = 0;
        Queue<Edge> queue = new LinkedList<>();

        for (int c = 1; c <= chickenCnt; c++) {
            if (visited[c]) {
                queue.offer(new Edge(chicken[c][0], chicken[c][1], 0));
                mapVisited[chicken[c][0]][chicken[c][1]] = true;
            }
        }

        while (!queue.isEmpty()) {
            Edge xy = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = xy.x + dx[i];
                int ny = xy.y + dy[i];
                // 인덱스 범위 안에 있을때만
                if (nx > 0 && ny > 0 && nx <= N && ny <= N) {
                    // 집 만나면 종료!
                    if (!mapVisited[nx][ny]) {
                        mapVisited[nx][ny] = true;
                        if (map[nx][ny] == 1) {
                            distanceMin += xy.d + 1;
                        }
                        queue.offer(new Edge(nx, ny, xy.d + 1));
                    }
                }
            }
        }

        if (min == 0 || min > distanceMin) {
            min = distanceMin;
        }
    }

    static class Edge {
        int x;
        int y;
        int d;

        private Edge(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
