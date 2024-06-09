package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
영일랜드 - 실2
소요 시간: 40분
원인: 백트래킹 문제인데 너무 복잡하게 생각함.
깊이가 N에 도달하지 못하면 모든 놀이기구를 경험할 수 없음.
위 부분을 또 예외처리해야한다고 잘못 생각함.

 */
public class Boj_31871 {
    static int N, M, max;
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[u][v] = Math.max(graph[u][v], d);
        }

        max = -1;
        for (int i = 1; i <= N; i++) {
            if (graph[0][i] > 0) {
                visited = new boolean[N + 1];
                visited[0] = true;
                visited[i] = true;
                dfs(i, 1, graph[0][i]);
            }
        }
        System.out.println(max);
    }

    private static void dfs(int node, int depth, int sum) {

        if (depth == N) {
            if (graph[node][0] != 0) {
                sum += graph[node][0];
                max = Math.max(sum, max);
            }
        } else if (depth < N) {

            for (int i = 0; i <= N; i++) {
                if (!visited[i] && graph[node][i] > 0) {
                    visited[i] = true;
                    dfs(i, depth + 1, sum + graph[node][i]);
                    visited[i] = false;
                }
            }
        }
    }
}
