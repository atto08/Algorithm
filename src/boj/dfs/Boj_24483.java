package boj.dfs;

import java.io.*;
import java.util.*;
/*
깊이 순서 * 방문 순서 가 정답임
1) 틀렸습니다
==> 깊이 순서 * 노드 번호를 곱함 ㅋㅋ
ex)
9 9 1
1 2
2 3
2 4
4 6
6 8
1 5
5 6
5 7
9 1
node : 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9
depth: 0 - 1 - 2 - 2 - 4 - 3 - 5 - 4 - 1
visit: 1 - 2 - 3 - 4 - 6 - 5 - 7 - 8 - 9
 */
public class Boj_24483 {
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
                sum += (long) visit[i] * depth[i];
            }
        }
        bw.write(String.valueOf(sum));
        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int node, int d) {
        visited[node] = true;
        depth[node] = d;
        visit[node] = ++v;

        HashSet<Integer> set = map.get(node);
        if (set != null) {
            List<Integer> valList = new ArrayList<>(set);
            Collections.sort(valList);
            for (int val : valList) {
                if (!visited[val]) {
                    dfs(val, d + 1);
                }
            }
        }
    }
}

