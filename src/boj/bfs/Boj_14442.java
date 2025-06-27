package boj.bfs;

import java.util.*;
import java.io.*;

/*
벽 부수고 이동하기2 - 골3
소요시간 - 80분

문제설명:
- K개의 벽을 부술수 있으면서 (N,M)에 최단거리로 도착하는 값을 구하기.
- 0: 이동가능 / 1: 벽 & K개까지 벽부수기 가능

풀이설명:
- x,y,dist,벽을 부순여부 k
- x,y,dist를 증가시키면서 bfs탐색.
- 길(0)을 만나면 그냥 전진
- 벽(1)을 만나면 k가 K보다 작으면 부수고(k+1) 이동 가능
- 나머진 불가

1차시도 - 틀렸습니다.(25분)
- 먼저 방문한 경우에만 따라서 값을 출력하도록 구현되어 있었음.
해결 방법
- k값이 적은 값을 우선순위 뽑기로 변경 -> (큐 -> 우선순위 큐)

2차시도 - 시간초과(+24분)
- 아래 2가지 포인트를 놓침
    1) k(부순 벽 갯수)별 최단거리 방문여부 체크
    -> 기존에 dist[nx][ny] > nd 조건으로 방문여부 판단했었음.
    -> 같은 좌표 (nx, ny)에 대해 더 짧은 거리로만 방문하는 경우만 허용
    -> 즉, 다른 k값으로 방문했을 경우는 고려X -> 경로 탐색 누락 또는 중복 삽입 발생.
    2) 우선순위 큐 사용
    -> k가 작은 기준으로 우선순위 방문하는 것이 최단거리라는 보장은 없다. - 알고있는 내용인데 틀리니까 안일했다.
    -> d가 작은 기준으로 하던지, 원래대로 Queue로 접근하는게 맞는방법.
해결 방법
- 1번은 3차원 방문배열 visited[N][M][K]을 사용해서 k별 최단 방문을 체크.
- 2번은 다시 큐를 사용해서 bfs로 돌면서 가장먼저 나오는 N,M에 도착한 정보를 리턴하면 됨.

p.s
- 어제 잘된 경우를 살려서 dist에 꽂혔나보다.
- 방문배열을 사용이 굳이 필요하지 않다고 여겼는데, 웬걸, 3차원배열로 k별 방문을 체크하며 접근하는게 가장 좋은 접근법.
- 나는 오늘도 배운다.
*/

public class Boj_14442 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs(map, N, M, K));
    }

    private static int bfs(char[][] map, int N, int M, int K) {
        int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

        Queue<Move> q = new LinkedList<>();
        q.offer(new Move(0, 0, 1, 0));
        boolean[][][] visited = new boolean[N][M][K + 1];
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Move now = q.poll();

            if (now.x == N - 1 && now.y == M - 1) return now.d;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                int nd = now.d + 1;
                int nk = now.k + 1;

                // 벽이 아니면 그냥 이동
                if (map[nx][ny] == '0') {
                    if (!visited[nx][ny][now.k]) {
                        visited[nx][ny][now.k] = true;
                        q.offer(new Move(nx, ny, nd, now.k));
                    }
                }
                // 벽인데 아직 부술 수 있는 기회가 남아 있다면
                else if (map[nx][ny] == '1' && now.k < K) {
                    // 벽을 하나 부숴야 하니까 nk로
                    if (!visited[nx][ny][nk]) {
                        visited[nx][ny][nk] = true;
                        q.offer(new Move(nx, ny, nd, nk));
                    }
                }
            }
        }
        return -1;
    }

    static class Move {
        int x, y, d, k;

        public Move(int x, int y, int d, int k) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
        }
    }
}