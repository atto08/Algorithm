package boj.bfs;

import java.util.*;
import java.io.*;

/*
인구이동 - 골4
소요시간 - 100분

문제설명:
- 인구 이동이 며칠 동안 발생하는지 구하기
- N*N 땅 (1 <= N <= 50), (1 <= L <= R <= 100), (1 <= A[r][c] <= 100)
- L <= 인구차이 <= R - 두 나라가 공유하는 국경선을 하루 동안 연다.
	- 인구이동 시작 open & 연합 해체 후 모든 국경선 close
	- 인접한 한칸만 이동 가능 --> 연합
	- 각 칸의 인구수 = (연합 인구 수) / (연합 구성 칸 갯수) --> 소수점은 버림

풀이설명:
- 각 나라별로 주변 국가와 L이상 R이하인지 체크해야됨. - 절댓값으로 해야됨 인구수 차이니까 더 큰 쪽에서 작은 쪽.
N * N 돌면서 bfs로 주변 연결된 L<= R>= 인 곳까지만 쭉 가고.

고민한 부분:
1) 한번 나라 전체를 훑을때(인구이동이 일어나는 1회당) 먼저 인구이동이 일어나는 지점에 대해서 인구이동 업데이트는
    - 한 사이클을 돌 동안의 모든 인접한 경우를 모아뒀다가 한꺼번에 처리해야할까?
    - 인접여부에 따라서 먼저 진행해야할까?
        - 일단 인접한 녀석들에 대해서 bfs를 통해 (연합국가 총인원수) / (연합국가 갯수) 를 진행하는 방향 -> 우선진행
고민한 이유: 아래 조건때문에 고민함.
"인접한 두 나라의 인구 차가 L 이상, R 이하라면 국경선을 연다. 그 후, 연합을 이루는 각 칸의 인구 수를 (연합 총 인구) / (칸 수)의 소수점 버림 값으로 바꾼다."
-> 이 조건을 나는 한사이클 동안 map[r][c]가 인접한 나라끼리 먼저 이동해도 되는가가 의문이였음.
-> 조건을 다시 읽어보면, 문제가 되지않는다. (조건을 제대로 이해해야한다.)

2) 범위내 근접국가 방문여부 체크는 언제해야될까?
- 범위에 벗어난 경우면 안하는 방향으로 우선진행
문제 아닌 이유: 유동인구가 없었기 때문에 안하는게 맞음.

3) bfs에서 국가cnt가 1개일 때, 방문여부 돌려야하지않나 -> 필요없음.
문제 아닌 이유: 인구이동 X -> 어차피 주변에 이동가능하지 않아서 방문체크 복구 필요x 때문.

p.s
- 생각하는걸 주석으로 정리하면서 적어놓으니까 훨씬 수월했다.
- 고민되는 부분이나 헷갈리는 지문에서는 우선 더 맞는 쪽으로 시도를 해봄
     - ? 를 앞에 달아 놓고 우선진행함.
- 간만에 골드 문제 온전히 내머리로 구현해서 풀었다.
- 그치만 100분 소요는 심했다.
    - 집중력, 코드 작성안하고 고민한 시간이 더 길었던 것.
- 오늘을 기점으로 앞으로도 이런 접근 지속하보자구.
*/
public class Boj_16234 {
    static int N, L, R;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited;
        int day = 0; // 최종 결과 값.
        while (true) {
            int cnt = 0;
            visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!visited[r][c]) {
                        cnt += bfs(r, c, visited, map);
                    }
                }
            }
            if (cnt == 0) break;
            day++;
        }

        System.out.println(day);
    }

    private static int bfs(int x, int y, boolean[][] visited, int[][] map) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> union = new ArrayList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        union.add(new int[]{x, y});

        int cnt = 1;            // 범위내에 있는 국가 갯수
        int sum = map[x][y];    // 범위내 국가 총 인원 수
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0], c = now[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

                // 범위내 국가 방문여부 & 인구차이 체크
                int dis = Math.abs(map[nr][nc] - map[r][c]);
                if (!visited[nr][nc] && (dis >= L && dis <= R)) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                    union.add(new int[]{nr, nc});
                    cnt++;              // 연합 국가증가
                    sum += map[nr][nc]; // 연합 총 인원추가
                }
            }
        }

        if (cnt == 1) return 0;

        int np = sum / cnt;
        for (int[] now : union) {
            int r = now[0], c = now[1];
            map[r][c] = np;
        }
        return 1;
    }
}
