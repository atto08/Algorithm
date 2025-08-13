package programmers.lv2;

/*
석유 시추 - lv2
소요시간 - 56분

풀이설명:
- bfs

0 ~ m 길이동안
1인 곳의 위치로 시작해서 카운트

1차시도 --> 효율성 전체 (시간초과)
0 ~ m길이 만큼 방문할때마다 연결된 석유를 찾는 시간때문에 발생했다 생각함.

그렇다면,
- 각 붙어있는 석유별로 번호 부여.
- 해당 석유번호의 크기를 key & val 값으로 저장.
- 0 ~ m 열을 돌면서 석유이면서 방문하지 않은 석유번호 체크하며 더하기.

2차시도 --> 통과

p.s
- 40분안에 풀지 못해 아쉽다.
- 처음에 풀때, 너무 쉬운 부분에서 의심했어햐했는데 그러지 못했다.
- 그래도 코드의 문제점을 바로 파악하고 그것이 맞는 접근이였다.
- 다음엔 더 잘할 듯. 사전에 방지하는 코드 작성하는 습관도 길러라.
*/

import java.util.*;

public class Program_250136 {
    static int N, M, L;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public static int solution(int[][] land) {
        int result = 0;
        N = land.length;
        M = land[0].length;

        boolean[][] visited = new boolean[N][M];
        int[][] island = new int[N][M];

        HashMap<Integer, Integer> map = new HashMap<>();
        L = 1; // 섬의 번호.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && land[i][j] == 1) {
                    bfs(land, island, visited, i, j, map);
                }
            }
        }

        for (int m = 0; m < M; m++) {
            result = Math.max(result, getOil(m, island, land, map));
        }

        return result;
    }

    private static void bfs(int[][] land, int[][] island, boolean[][] visited, int x, int y, HashMap<Integer, Integer> map) {
        visited[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        int size = 0;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int n = now[0], m = now[1];
            island[n][m] = L;
            size++;

            for (int i = 0; i < 4; i++) {
                int nx = n + dx[i];
                int ny = m + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if (!visited[nx][ny] && land[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        map.put(L, size);
        L++;
    }

    private static int getOil(int m, int[][] island, int[][] land, HashMap<Integer, Integer> map) {
        int result = 0;

        boolean[] visited = new boolean[map.size() + 1];
        for (int n = 0; n < N; n++) {
            if (land[n][m] < 1) continue;

            int num = island[n][m];
            if (!visited[num]) {
                visited[num] = true;
                result += map.get(num);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}}));
        System.out.println(solution(new int[][]{{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}}));
    }
}
