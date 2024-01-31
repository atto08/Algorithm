package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2644 {
    static StringTokenizer st;
    static int N, M, start, end, result;
    static boolean[] visited;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        result = -1;
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        map = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            map[k][v] = map[v][k] = 1;
        }

        dfs(start, 0);
        System.out.println(result);
    }

    public static void dfs(int node, int depth) {
        visited[node] = true;

        if (node == end) {
            result = depth;
        }
        for (int i = 1; i <= N; i++) {
            if (map[node][i] == 1 && !visited[i]) {
                dfs(i, depth + 1);
            }
        }
    }
}
