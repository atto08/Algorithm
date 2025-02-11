package programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;

/*
게임 맵 최단거리 - lv2(개념정리 및 재풀이)
소요시간 - 30분
분류 - BFS

문제 설명:
- 시작점 (0,0)에서 상대 팀 진영 (N-1, M-1)까지 도착하는 최단 거리를 구하는 문제.
- 맵 정보: maps[][] (1 = 길, 0 = 벽)

풀이 방법:
- BFS(너비 우선 탐색)를 사용하여 최단 경로 탐색.
- 큐를 이용해 현재 위치에서 상하좌우 이동.
  - 벽(0) 또는 맵을 벗어난 경우는 제외.
  - 방문하지 않은 길(1)인 경우만 이동 가능하며, 거리(d)를 증가시켜 큐에 삽입.
- BFS의 특성상 가장 먼저 목표 지점 (N-1, M-1)에 도착하는 경우가 최단 거리.
- 모든 탐색 후 목표 지점에 도달하지 못하면 -1 반환.
*/
public class Program_1844 {
    static int N, M;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        visited = new boolean[N][M];

        return bfs(0, 0, maps);
    }

    private static int bfs(int x, int y, int[][] map) {
        visited[x][y] = true;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 1)); // 시작 위치와 거리(1) 초기화

        int result = -1; // 목표 지점에 도달하지 못할 경우 -1 반환
        while (!q.isEmpty()) {
            Node now = q.poll();

            // 목표 지점 (N-1, M-1)에 도착하면 최단 거리 반환
            if (now.x == N - 1 && now.y == M - 1) {
                result = now.d;
                break;
            }

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                // 맵 범위를 벗어나면 제외
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                // 방문하지 않았고, 벽(0)이 아닌 경우 이동 가능
                if (!visited[nx][ny] && map[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny, now.d + 1)); // 거리 증가 후 큐에 삽입
                }
            }
        }
        return result;
    }

    static class Node {
        int x, y, d;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static void main(String[] args) {
        Program_1844 pg = new Program_1844();
        int[][] ex1 = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        int[][] ex2 = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}};
        System.out.println(pg.solution(ex1)); // 11
        System.out.println(pg.solution(ex2)); // -1
    }
}