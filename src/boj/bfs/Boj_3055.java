package boj.bfs;

import java.util.*;
import java.io.*;

/*
탈출 - 골4
소요시간 - 100분

문제설명:
- 고슴도치가 비버 굴로 이동할 수 있는 최소 시간을 구하시오.
- R*C 크기 '.' = 비어있는곳, '*' = 물, X = 돌
- 인접칸 상하좌우 이동가능 & 비버집 못가면 "KAKTUS" 출력

루프 종료 조건
- 고슴도치가 비버집에 도착했을때,
- 고슴도치가 더이상 이동이 불가할때,

풀이설명:
- 고슴도치 먼저 이동 가능한지 체크.
- 물은 계속해서 퍼져야함
	- list에 좌표 갖고 있기 -> 테두리만 갖고있는 방법?

->> 너무 한번에 동작해야한다고 생각한듯. -> 오히려 구현하는데 조건 걸기 까다롭다는 생각이 듦.
+ 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다.
-> 라는 조건에서 물퍼짐이 먼저라는 생각함.

그래서!
1시간 후 접근 방식 수정 (+ 50분 소요)
- 2차원배열 dist에 물좌표를 시작점으로 벽, 비버집을 제외한 모든 지역에 최소로 도착하는 정보를 우선 탐색
- 고슴도치로 거리를 재가면서 아래 조건을 만족하도록 구현.
[조건] - dist[nx][ny] > 현재 걸린 시간 + 현재 위치가 빈 공간과 비버 집인 경우만
- 이렇게 구현하면 앞서 고민한 내용들 필요 X

p.s
- BFS 정복 ~ing
- R,C 범위가 50이하길래 dist[][]를 하나 더 써도 메모리 초과가 나지 않을거 같았다.
- 그리고 1시간 지난 후이긴 했는데, 물에 잠기는 이차원배열을 만들어놓고 고슴도치 이동여부 판단하는 구현방식?
-> 지난주 포함 bfs문제 풀면서 배웠던 방법 응용 잘한것 같다.

*/

public class Boj_3055 {
    static int R, C;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        int[][] dist = new int[R][C];
        List<Point> points = new ArrayList<>();
        int hx = 0, hy = 0;
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'S') {
                    hx = i;
                    hy = j;
                } else if (map[i][j] == '*') {
                    points.add(new Point(i, j));
                }
            }
        }

        for (int i = 0; i < R; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        submerge(points, map, dist);
        canMove(map, dist, hx, hy);
    }

    private static void canMove(char[][] map, int[][] dist, int hx, int hy) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        visited[hx][hy] = true;
        q.offer(new Point(hx, hy, 0));

        int result = -1;
        while (!q.isEmpty()) {
            Point now = q.poll();

            if (map[now.x][now.y] == 'D') {
                result = now.d;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

                int nd = now.d + 1;
                if (!visited[nx][ny] && nd < dist[nx][ny] && (map[nx][ny] == '.' || map[nx][ny] == 'D')) {
                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny, nd));
                }
            }
        }
        if (result < 0) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(result);
        }
    }

    private static void submerge(List<Point> water, char[][] map, int[][] dist) {
        Queue<Point> q = new LinkedList<>();

        for (Point p : water) {
            q.offer(new Point(p.x, p.y));
            dist[p.x][p.y] = 0;
        }

        while (!q.isEmpty()) {
            Point now = q.poll();
            int x = now.x, y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

                if (dist[nx][ny] == Integer.MAX_VALUE) {
                    if (map[nx][ny] == 'D') {
                        dist[nx][ny] = Integer.MAX_VALUE;
                    } else if (map[nx][ny] == 'X') {
                        dist[nx][ny] = -1;
                    } else {
                        q.offer(new Point(nx, ny));
                        dist[nx][ny] = dist[x][y] + 1;
                    }
                }
            }
        }
    }

    static class Point {
        int x, y, d;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
