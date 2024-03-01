package boj.dfs;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_15651 {
    static int N, M;
    static boolean[][] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[M + 1][N + 1];

        int[] arr = new int[M];
        for (int i = 1; i <= N; i++) {
            dfs(i, 1, arr);
            if (M > 1) {
                for (int j = 2; j <= M; j++) {
                    Arrays.fill(visited[j], false);
                }
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int node, int depth, int[] arr) throws IOException {

        arr[depth - 1] = node;
        if (depth < M) {
            for (int i = 1; i <= N; i++) {
                if (!visited[depth][i]) {
                    visited[depth][node] = true;
                    dfs(i, depth + 1, arr);
                    visited[depth][node] = false;
                }
            }

        } else if (depth == M) {
            StringBuilder sb = new StringBuilder();
            for (int num : arr) {
                sb.append(num).append(" ");
            }
            String result = sb.toString().trim();
            bw.write(result + "\n");
        }
    }
}
