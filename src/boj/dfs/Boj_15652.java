package boj.dfs;

import java.io.*;
import java.util.StringTokenizer;

public class Boj_15652 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static boolean[] visited;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        arr = new int[M + 1];

        for (int i = 1; i <= N; i++) {
            dfs(i, 1);
            visited[i] = true;
        }

        bw.flush();
    }

    public static void dfs(int node, int depth) throws IOException {

        arr[depth] = node;
        if (depth < M) {
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    dfs(i, depth + 1);
                }
            }
        } else if (depth == M) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] <= arr[i]) {
                    sb.append(arr[i]).append(" ");
                } else {
                    sb = new StringBuilder();
                    break;
                }
            }
            if (sb.toString().length() > 0) {
                bw.write(sb.toString().trim() + "\n");
            }
        }
    }
}
