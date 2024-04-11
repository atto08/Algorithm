package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
토마토 - 골5
이 문제처럼 한번에 여러 곳에서 탐색을 해야하는 문제는
x,y값이 1인 곳을 미리 q에 배열로 집어넣고
탐색하면서 0이 아니고 더 낮은 방문 숫자로 방문이 가능하면 map[x][y] 값을 바꿔주기

BFS에 대해 다시 학습 후 17086번 풀고 다시 시도 + 배열 원리
소요 시간: 18분
>> 알고리즘 풀이 방법의 사용 경우를 명확하게 구분하는 것의 중요성을 느꼈다.
 */
public class Boj_7569 {
    static int N, M, H, result, notRipens;
    static int[][][] map;
    static boolean[][][] visited;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, -1, 0, 1, 0, 0};
    static int[] dh = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[N][M][H];
        visited = new boolean[N][M][H];
        // 층
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    map[n][m][h] = Integer.parseInt(st.nextToken());
                    if (map[n][m][h] == 1) {
                        q.offer(new int[]{n, m, h});
                        visited[n][m][h] = true;
                        map[n][m][h] = 0;
                    } else if (map[n][m][h] == 0) {
                        notRipens++;
                    }
                }
            }
        }

        bfs();

    }

    private static void bfs() {
        while (!q.isEmpty()) {
            int[] now = q.poll();

            int x = now[0], y = now[1], h = now[2];
            for (int i = 0; i < 6; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nh = h + dh[i];

                if (nx < 0 || ny < 0 || nh < 0 || nx >= N || ny >= M || nh >= H) {
                    continue;
                }

                if (!visited[nx][ny][nh] && map[nx][ny][nh] == 0) {
                    q.offer(new int[]{nx, ny, nh});
                    visited[nx][ny][nh] = true;
                    map[nx][ny][nh] = map[x][y][h] + 1;
                    notRipens--;
                    if (map[nx][ny][nh] > result) {
                        result = map[nx][ny][nh];
                    }
                }
            }
        }

        if (notRipens > 0) {
            result = -1;
        }

        System.out.println(result);
    }
}
