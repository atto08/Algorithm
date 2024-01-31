package boj.dfs;

import java.io.*;
import java.util.*;

public class Boj_24481 {
    static StringTokenizer st;
    static int N, M, R, number;
    static boolean[] visited;
    static HashMap<Integer, HashSet<Integer>> map;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        map = new HashMap<>();
        number = 0;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        result = new int[N + 1];
        Arrays.fill(result, -1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(key, k -> new HashSet<>()).add(val);
            map.computeIfAbsent(val, v -> new HashSet<>()).add(key);
        }

        dfs(R, number);

        for (int i = 1; i <= N; i++) {
            bw.write(result[i] + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int node, int num) {
        try {
            HashSet<Integer> set = map.get(node);

            if (!visited[node]) {
                visited[node] = true;
                result[node] = num;

                List<Integer> valList = new ArrayList<>(set);
                Collections.sort(valList);
                for (int val : valList) {
                    if (!visited[val]) {
                        dfs(val, num + 1);
                    }
                }
            }
        } catch (NullPointerException ignored) {
        }
    }
}
