package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj_25195 {
    static StringTokenizer st;
    static int N, M, s;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static HashSet<Integer> gomgom;
    static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        result = "Yes";
        graph = new ArrayList<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(k).add(v);
        }

        s = Integer.parseInt(br.readLine());
        gomgom = new HashSet<>(s);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < s; i++) {
            int location = Integer.parseInt(st.nextToken());
            gomgom.add(location);
        }

        dfs(1);

        System.out.println(result);
        br.close();
    }

    public static void dfs(int node) {
        visited[node] = true;

        if (!graph.get(node).isEmpty()) {
            for (int num : graph.get(node)) {
                if (!visited[num]) {
                    visited[num] = true;
                    dfs(num);
                }
                visited[num] = false;
            }
        } else {
            boolean checker = false;
            for (int num : gomgom) {
                if (visited[num]) {
                    checker = true;
                }
            }
            if (!checker) {
                result = "yes";
            }
        }
    }
}
