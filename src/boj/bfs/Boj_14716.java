package boj.bfs;

import java.util.*;
import java.io.*;

/*
현수막 - 실1
소요시간 - 40분

 풀이설명:
- 큐를 사용한 너비우선탐색으로 1을 찾아낸 순간부터 0만나기 전까지만
- 1을 언제부터 판별해야하지.
	1) - 일단 1을 다 넣어놓고.
	   - 뽑을때마다 방문한 녀석이면 그냥통과.?
	2) - 배열(정보)에 입력값 넣으면서 1 카운트 - 전체갯수 total 갖고있기.
	   - 이중포문 돌면서 1만나면 주변녀석 스캔. & total 깎으면서.

- 2번으로 선택.

p.s
- 애초에 (1 ≤ M, N ≤ 250) 조건 때문에 total이 없어도 엄청난 속도차이가 나지는 않는거 같다.
- 일단은 가장 효율적인 방법으로 선택해서 풀어낸 것에 박수를.
 */
public class Boj_14716 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        int[] dx = {0, 0, -1, 1, -1, -1, 1, 1}, dy = {-1, 1, 0, 0, -1, 1, -1, 1};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j, map, visited, dx, dy, N, M);
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    private static void bfs(int x, int y, int[][] map, boolean[][] visited, int[] dx, int[] dy, int N, int M) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if (!visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }
}