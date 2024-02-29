package boj.dfs;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_15650 {
    static int N, M;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        int[] arr = new int[M];
        for (int i = 1; i <= N; i++) {
            dfs(i, 1, arr);
            Arrays.fill(visited, false);
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int node, int depth, int[] arr) throws IOException {
        arr[depth - 1] = node;
        if (depth < M) {
            visited[node] = true;
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    dfs(i, depth + 1, arr);
                    visited[i] = false;
                }
            }
        } else if (depth == M) {
            boolean check = false;

            for (int i=M-1; i>0; i--){
                if (arr[i] <= arr[i-1]){
                    check = true;
                    break;
                }
            }
            if (!check){
                StringBuilder sb = new StringBuilder();
                for (int num : arr) {
                    sb.append(num).append(" ");
                }

                String result = sb.toString().trim();
                bw.write(result + "\n");
            }
        }
    }
}
