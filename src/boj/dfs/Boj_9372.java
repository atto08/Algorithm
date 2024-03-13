package boj.dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_9372 {
    static int N, M, cnt;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> map;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            cnt = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new ArrayList<>();
            for (int i = 0; i <= N; i++)
                map.add(new ArrayList<>());

            visited = new boolean[N + 1];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int k = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                map.get(k).add(v);
                map.get(v).add(k);
            }

            dfs(N);

            System.out.println(cnt);
        }
    }

    private static void dfs(int node) {
        visited[node] = true;

        for (int num : map.get(node)) {
            if (!visited[num]) {
                cnt++;
                dfs(num);
            }
        }
    }
}
