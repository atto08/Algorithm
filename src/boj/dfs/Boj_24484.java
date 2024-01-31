package boj.dfs;

import java.io.*;
import java.util.*;

// sum의 타입도 중요함.
public class Boj_24484 {
    static StringTokenizer st;
    static int N, M, R, v;
    static boolean[] visited;
    static HashMap<Integer, HashSet<Integer>> map;
    static int[] depth, visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        map = new HashMap<>();
        v = 0;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        depth = new int[N + 1];
        visit = new int[N + 1];
        Arrays.fill(depth, -1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(key, k -> new HashSet<>()).add(val);
            map.computeIfAbsent(val, v -> new HashSet<>()).add(key);
        }

        dfs(R, 0);

        long sum = 0;
        for (int i = 1; i <= N; i++) {
            if (depth[i] != -1) {
                sum += (long) depth[i] * visit[i];
            }
        }
        bw.write(String.valueOf(sum));
        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int node, int d) {
        try {
            HashSet<Integer> set = map.get(node);

            if (!visited[node]) {
                visited[node] = true;
                depth[node] = d;
                visit[node] = ++v;

                List<Integer> valList = new ArrayList<>(set);
                valList.sort(Comparator.reverseOrder());
                for (int val : valList) {
                    if (!visited[val]) {
                        dfs(val, d + 1);
                    }
                }
            }
        } catch (NullPointerException ignored) {
        }
    }
}
