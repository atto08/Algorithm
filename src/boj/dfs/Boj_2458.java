package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 규칙을 잘찾았따.. 히히
public class Boj_2458 {
    static StringTokenizer st;
    static int N, M, result;
    static boolean[] visited;
    static int[][] map;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        result = 0;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        map = new int[N + 1][N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
        }

        for (int i = 1; i <= N; i++) {
            dfs(i, i);
            Arrays.fill(visited, false);
        }

        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (i != j && map[i][j] == 0) {
                    count++;
                }
            }
            if (count == 0) {
                result++;
            }
        }

        System.out.println(result);
        br.close();
    }

    public static void dfs(int node, int i) {
        visited[node] = true;

        for (int num : graph[node]) {
            if (!visited[num]) {
                map[i][num] = map[num][i] = 1;
                dfs(num, i);
            }
        }
    }
}
