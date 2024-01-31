package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
블로그 참고 https://velog.io/@lifeisbeautiful/Java-%EB%B0%B1%EC%A4%80-1012%EB%B2%88-
%EC%9C%A0%EA%B8%B0%EB%86%8D-%EB%B0%B0%EC%B6%94-%EC%9E%90%EB%B0%94

위치가 1 이고 방문하지 않은 곳만 찾아서 좌표를 조회
1) 위치 값이 1인 곳을 찾는다
2) 해당 위치의 주변 값이 1인 붙어있는 곳들은 전부 0으로 바꾸고 방문한것으로 바꾼다
순회하면 시간초과 날거라고 생각했는데 위와 같은 방법으로 중복되지 않게 만들어준다면 걱정 x
 */
public class Boj_1012 {
    static StringTokenizer st;
    static int N, M, K, result;
    static boolean[][] visited;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            visited = new boolean[N][M];
            result = 0;

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map[x][y] = 1;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        result++;
                        bfs(i, j);
                    }
                }
            }
            bw.write(result + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        map[x][y] = 0;

        while (!q.isEmpty()) {
            int[] xy = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = xy[0] + dx[i];
                int nextY = xy[1] + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }

                if (!visited[nextX][nextY] && map[nextX][nextY] == 1) {
                    q.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                    map[nextX][nextY] = 0;
                }
            }
        }
    }
}
