package boj.dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_13265 {
    static StringTokenizer st;
    static int N, M;
    static ArrayList<ArrayList<Integer>> map;
    static int[] visit;
    static boolean[] visited;
    static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new ArrayList<>();
            visit = new int[N + 1];
            visited = new boolean[N + 1];
            result = "possible";

            for (int i = 0; i <= N; i++) {
                map.add(new ArrayList<>());
            }

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int k = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                map.get(k).add(v);
                map.get(v).add(k);
            }

            for (int i = 1; i <= N; i++) {
                dfs(i, false);
                Arrays.fill(visit, 0);
                Arrays.fill(visited, false);
            }
            bw.write(result + "\n");

        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int node, boolean check) {
        if (!visited[node]) {
            visited[node] = true;
            if (!check) {
                visit[node] = 1;
            } else {
                visit[node] = -1;
            }

            for (int num : map.get(node)) {
                if (visit[num] != visit[node]) {
                    dfs(num, !check);
                } else {
                    result = "impossible";
                    break;
                }
            }
        }
    }
}
