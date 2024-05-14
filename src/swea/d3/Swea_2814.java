package swea.d3;

import java.io.*;
import java.util.StringTokenizer;
/*
최장 거리 - D3
소요 시간: 37분

1) 6/10 pass
원인: 백트래킹을 하지않아 경우의 수가 줄어듦
해결: 백트래킹을 추가

2) pass
 */
public class Swea_2814 {
    static int N, M, max;
    static boolean[] visited;
    static boolean[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new boolean[N + 1][N + 1];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int k = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[k][v] = graph[v][k] = true;
            }

            max = 0;
            for (int i = 1; i <= N; i++) {
                visited = new boolean[N + 1];
                visited[i] = true;
                dfs(i, 1);
            }

            bw.write("#" + test_case + " " + max + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void dfs(int node, int depth) {

        if (depth > max) {
            max = depth;
        }

        if (depth <= N) {
            for (int i = 1; i <= N; i++) {
                if (!visited[i] && graph[node][i]) {
                    visited[i] = true;
                    dfs(i, depth + 1);
                    visited[i] = false;
                }
            }
        }
    }
}