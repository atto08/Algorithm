package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
점프 점프 - 실2
소요 시간: 18분

1) 통과
1차원 배열에서 너비우선 탐색을 통해 가장 먼저 N에 도달하는 경우를 찾을 수 밖에없음.
- 1차원 배열 너비우선탐색 문제라고 생각함
 */
public class Boj_11060 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] map = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) map[i] = Integer.parseInt(st.nextToken());

        bfs(map, N);
    }

    private static void bfs(int[] map, int N) {
        boolean[] visited = new boolean[N];

        Queue<int[]> q = new LinkedList<>();
        visited[0] = true;
        q.offer(new int[]{0, 0});

        int result = -1;
        while (!q.isEmpty()) {
            int[] now = q.poll(); // 노드
            int node = now[0], cnt = now[1];

            if (node == N - 1) {
                result = cnt;
                break;
            }
            for (int i = node + 1; i <= node + map[node]; i++) {

                if (i >= N) continue;

                if (!visited[i]) {
                    visited[i] = true;
                    q.offer(new int[]{i, cnt + 1});
                }
            }
        }
        System.out.println(result);
    }
}
