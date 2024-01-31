package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_24447 {
    static StringTokenizer st;
    static boolean[] visited;
    static int[] depth, visit;
    static HashMap<Integer, HashSet<Integer>> map;
    static int N, M, R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        depth = new int[N + 1];
        visit = new int[N + 1];
        map = new HashMap<>(N);
        Arrays.fill(depth, -1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(key, k -> new HashSet<>()).add(val);
            map.computeIfAbsent(val, v -> new HashSet<>()).add(key);
        }

        bfs();

        // 정점의 범위는 5 <= N <= 100,000
        // 즉 방문 순서 100,000 과 깊이 순서 100,000개가 곱해지니 int 의 범위를 벗어남. 약 1000억
        long sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += (long) visit[i] * depth[i];
        }

        System.out.println(sum);
        br.close();
    }

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(R);
        visited[R] = true;
        int d = 0;
        depth[R] = d;
        int t = 1;
        visit[R] = t++;

        try {
            while (!q.isEmpty()) {
                int s = q.size();
                d++;
                for (int i = 0; i < s; i++) {
                    int num = q.poll();
                    List<Integer> list = new ArrayList<>(map.get(num));
                    Collections.sort(list);
                    for (int val : list) {
                        if (!visited[val]) {
                            q.offer(val);
                            visited[val] = true;
                            visit[val] = t++;
                            depth[val] = d;
                        }
                    }
                }
            }
        } catch (NullPointerException ignored) {

        }
    }
}
