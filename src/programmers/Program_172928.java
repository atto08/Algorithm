package programmers;

import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

/*
공원 산책 - level1
소요 시간: 39분

bfs 응용 구현문제
 */
public class Program_172928 {
    static char[][] map;
    static int H, W;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    static HashMap<Character, Integer> dir = new HashMap<>();

    public int[] solution(String[] park, String[] routes) {

        H = park.length;
        W = park[0].length();
        map = new char[H][W];
        for (int i = 0; i < H; i++) map[i] = park[i].toCharArray();

        dir.put('E', 0);
        dir.put('S', 1);
        dir.put('W', 2);
        dir.put('N', 3);

        int x = 0, y = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'S') {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        return bfs(x, y, routes);
    }

    private static int[] bfs(int x, int y, String[] routes) {
        int ex = x, ey = y;
        Queue<Park> q = new LinkedList<>();
        q.offer(new Park(x, y));

        for (int i = 0; i < routes.length; i++) {
            int nx = ex, ny = ey;

            String[] move = routes[i].split(" ");
            char order = move[0].charAt(0);
            int cnt = Integer.parseInt(move[1]);

            int idx = dir.get(order);
            boolean canMove = true;
            while (cnt > 0) {
                nx += dx[idx];
                ny += dy[idx];

                if (nx < 0 || ny < 0 || nx >= H || ny >= W || map[nx][ny] == 'X') {
                    canMove = false;
                    break;
                }
                cnt--;
            }

            if (canMove) {
                ex = nx;
                ey = ny;
            }
        }

        return new int[]{ex, ey};
    }

    static class Park {
        int x, y;

        private Park(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
