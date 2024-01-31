package boj.dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_11403 {
    static StringTokenizer st;
    static int N;
    static boolean[] visited;
    static ArrayList<Integer>[] list;
    static int[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        // list를 배열로 받겠다.
        list = new ArrayList[N];
        visited = new boolean[N];
        result = new int[N][N];

        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    list[i].add(j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            dfs(i, i);
            for (int j = 0; j < N; j++) {
                if (j != N - 1) {
                    bw.write(result[i][j] + " ");
                } else {
                    bw.write(result[i][j] + "");
                }
            }
            bw.write("\n");
            Arrays.fill(visited, false);
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int node, int i) {
        if (!visited[node]) {
            visited[node] = true;
            for (int val : list[node]) {
                if (!visited[val]) {
                    dfs(val, i);
                    result[i][val] = 1;
                } else if (visited[val] && val == i) {
                    result[i][val] = 1;
                }
            }
        }

    }
}
