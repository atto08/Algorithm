package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj_11724 {
    static boolean[] visited;
    static int result;
    static HashMap<Integer, HashSet<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        map = new HashMap<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        result = N;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(key, k -> new HashSet<>()).add(val);
            map.computeIfAbsent(val, v -> new HashSet<>()).add(key);
        }

        for (int i = 1; i <= N; i++)
            try {
                dfs(i);
            } catch (NullPointerException ignored) {

            }

        System.out.println(result);
        br.close();
    }

    public static void dfs(int num) {
        if (!visited[num]) {
            visited[num] = true;

            for (int val : map.get(num)) {
                if (!visited[val]) {
                    dfs(val);
                }
            }
        } else {
            result--;
        }
        /*
        이렇게 해도 속도차이가 크게 있지는 않음.
        for (int val : map.getOrDefault(num, new HashSet<>())) {
            if (!visited[val]) {
                dfs(val);
            }
        }
         */
    }
}
