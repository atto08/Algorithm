package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
for 문과 bfs 두 경우에서 두번 체크하는 조건식으로 만들다가 좀 오래걸림
bfs 에서만 state에 따라 체크해주면 된다는 것을 캐치하고
반복문에서의 조건을 모두 지움
 */
public class Boj_10026 {
    static int N, result1, result2;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        result1 = result2 = 0;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            char[] cArr = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (cArr[j] == 'B') {
                    map[i][j] = 0;
                } else if (cArr[j] == 'R') {
                    map[i][j] = 1;
                } else {
                    map[i][j] = 2;
                }
            }
        }

        // 0 == 일반인 / 1 == 적록색약
        for (int s = 0; s < 2; s++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (s == 0) {
                            result1++;
                            bfs(i, j, map[i][j], s);
                        } else {
                            result2++;
                            bfs(i, j, map[i][j], s);
                        }
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }
        }

        System.out.println(result1 + " " + result2);
        br.close();
    }

    public static void bfs(int x, int y, int k, int state) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] xy = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = xy[0] + dx[i];
                int nextY = xy[1] + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
                    continue;
                }

                if (state == 0) {
                    if (!visited[nextX][nextY] && map[nextX][nextY] == k) {
                        q.offer(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                    }
                } else {
                    if (k > 0) {
                        if (!visited[nextX][nextY] && map[nextX][nextY] > 0) {
                            q.offer(new int[]{nextX, nextY});
                            visited[nextX][nextY] = true;
                        }
                    } else {
                        if (!visited[nextX][nextY] && map[nextX][nextY] == 0) {
                            q.offer(new int[]{nextX, nextY});
                            visited[nextX][nextY] = true;
                        }
                    }
                }
            }
        }
    }
}
