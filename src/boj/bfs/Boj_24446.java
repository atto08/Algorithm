package boj.bfs;

import java.io.*;
import java.util.*;

public class Boj_24446 {
    static StringTokenizer st;
    static int N, M, R;
    static boolean[] visited;
    static HashMap<Integer, HashSet<Integer>> map;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        map = new HashMap<>(N);
        depth = new int[N + 1];
        Arrays.fill(depth, -1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(key, k -> new HashSet<>()).add(val);
            map.computeIfAbsent(val, v -> new HashSet<>()).add(key);
        }

        bfs();

        for (int i = 1; i <= N; i++) {
            bw.write(depth[i] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(R);
        int count = 0;
        visited[R] = true;
        depth[R] = count;

        try {
            while (!q.isEmpty()) {
                int s = q.size();
                count++;
                for (int i = 0; i < s; i++) {
                    int p = q.poll();

                    for (int val : map.get(p)) {
                        if (!visited[val]) {
                            q.offer(val);
                            depth[val] = count;
                            visited[val] = true;
                        }
                    }
                }
            }
        } catch (NullPointerException ignored) {

        }
    }
}
