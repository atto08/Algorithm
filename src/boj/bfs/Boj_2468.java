package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 출력 주의 - 높이는 주어지지 X 안전영역이 최대갯수인 경우를 출력해야됨!. - 블로그 참고하고 이해
==> 첫째 줄에 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수를 출력한다
ArrayStoreException
==> 일차원 배열만 사용가능한 Arrays.fill(); 메소드를 이차원 배열에 사용했을 때 발생
 */

public class Boj_2468 {
    static StringTokenizer st;
    static int N, maxH, result;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        result = 0;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (maxH < num) {
                    maxH = num;
                }
                map[i][j] = num;
            }
        }

        for (int h = 0; h < maxH; h++) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > h) {
                        count++;
                        bfs(i, j, h);
                    }
                }
            }
            if (count > result) {
                result = count;
            }
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }
        }

        System.out.println(result);
        br.close();
    }

    public static void bfs(int x, int y, int h) {
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

                if (!visited[nextX][nextY] && map[nextX][nextY] > h) {
                    q.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }
    }
}
