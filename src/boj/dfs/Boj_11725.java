package boj.dfs;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj_11725 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static boolean[] visited;
    static int[] result;
    static HashMap<Integer, HashSet<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new HashMap<>();
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        result = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int key = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(key, k -> new HashSet<>()).add(val);
            map.computeIfAbsent(val, v -> new HashSet<>()).add(key);
        }

        dfs(1);

        for (int i = 2; i <= N; i++) {
            bw.write(result[i] + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int node) {

        if (!visited[node]) {
            visited[node] = true;

            for (int val : map.get(node)) {
                if (!visited[val]) {
                    result[val] = node;
                    dfs(val);
                }
            }
        }
    }
}
