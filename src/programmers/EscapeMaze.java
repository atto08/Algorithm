package programmers;
/*
미로 탈출 -
소요 시간: 1시간 20분

1) 40점
원인: h와 w를 배열 크기에 서로 반대로 지정함

2) 69점
원인: 시간 초과 문제라고 생각됨
해결: 레버 찾기와 출구 찾기 2번의 bfs 분리 함 -> 중간 중복 시간 제거.

3) 100점

느낀 부분-
한번에 탐색하려 시도 >> 예상하지 못한 시간적인 문제가 발생
문제의 의도대로 시나리오를 작성해 순서대로 탐색하도록 구현하는 것이 효율적이였다.
*/

import java.util.Queue;
import java.util.LinkedList;

public class EscapeMaze {
    static char[][] map;
    static boolean pullLever = false, canExit = false;
    static boolean[][] visited;
    static int w, h, sx, sy, lx, ly, ld, result;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    static class Node {
        int x, y, dist;

        private Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public int solution(String[] maps) {

        h = maps.length;
        w = maps[0].length();

        map = new char[h][w];
        visited = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                map[i][j] = maps[i].charAt(j);
                if (map[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
            }
        }

        result = -1;
        bfs("lever"); // 레버 찾기

        if (pullLever) {
            visited = new boolean[h][w];
            bfs("exit"); // 출구 찾기
        }

        return result;
    }

    private static void bfs(String kind) {
        Queue<Node> q = new LinkedList<>();
        if (kind.equals("lever")) {
            q.offer(new Node(sx, sy, 0));
            visited[sx][sy] = true;
        } else {
            q.offer(new Node(lx, ly, ld));
            visited[lx][ly] = true;
        }

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (!pullLever && map[now.x][now.y] == 'L') {
                pullLever = true;
                lx = now.x;
                ly = now.y;
                ld = now.dist;
                break;
            }

            if (!canExit && pullLever && map[now.x][now.y] == 'E') {
                result = now.dist;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;

                if (!visited[nx][ny] && map[nx][ny] != 'X') {
                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny, now.dist + 1));
                }
            }
        }
    }
}
