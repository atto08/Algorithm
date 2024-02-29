package boj.dfs;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백 트래킹 연습 첫 문제
배열에 넣어 저장하는 부분 블로그 참고
 */
public class Boj_15649 {
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
            StringBuilder sb = new StringBuilder();
            for (int num : arr) {
                sb.append(num).append(" ");
            }

            String result = sb.toString().trim();
            bw.write(result + "\n");
        }
    }
}
