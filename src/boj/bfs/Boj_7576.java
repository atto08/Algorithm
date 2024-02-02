package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
동시 다발적으로 탐색을 해야하는 문제였음
1) 시간초과
==> 단순 이중배열 사용문제로 착각. 해서 인접리스트를 사용함.
2) 시간초과
==> 블로그를 참조함

원래 경로 문제처럼 x,y값이 1인 곳을 방문했는지 안했는지 체크하며 이중포문으로 돌았음
==> 이게 시간초과의 원인이라고 생각함

이 문제처럼 한번에 여러 곳에서 탐색을 해야하는 문제는
x,y값이 1인 곳을 미리 q에 배열로 집어넣고
탐색하면서 0이 아니고 더 낮은 방문 숫자로 방문이 가능하면 map[x][y] 값을 바꿔주기
 */
public class Boj_7576 {
    static StringTokenizer st;
    static int N, M, result;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static int[][] map;
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        bfs();

        br.close();
    }

    public static void bfs() {

        while (!q.isEmpty()) {
            int[] xy = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = xy[0] + dx[i];
                int nextY = xy[1] + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }

                if (map[nextX][nextY] != -1 && map[nextX][nextY] != 1) {
                    if (!visited[nextX][nextY]) {
                        visited[nextX][nextY] = true;
                        q.offer(new int[]{nextX, nextY});
                        map[nextX][nextY] = map[xy[0]][xy[1]] + 1;
                    } else {
                        if (map[xy[0]][xy[1]] + 1 < map[nextX][nextY]) {
                            q.offer(new int[]{nextX, nextY});
                            map[nextX][nextY] = map[xy[0]][xy[1]] + 1;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                result = Math.max(result, map[i][j]);
            }
        }
        System.out.println(result - 1);
    }
}
