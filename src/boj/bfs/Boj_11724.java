package boj.bfs;

/*
3회차 풀이
연결 요소의 개수 - 실2
소요시간 - 12분

풀이설명:
- 1 ~ N번째 섬을 방문하며 인접한 섬들에 대해서 너비우선 탐색 방문

p.s
- 이전 두번은 dfs로 풀었더라.
*/

import java.util.*;
import java.io.*;

public class Boj_11724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[N + 1];
        int result = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                bfs(graph, visited, i);
                result++;
            }
        }

        System.out.println(result);
    }

    private static void bfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int node) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        visited[node] = true;

        while (!q.isEmpty()) {
            int idx = q.poll();

            for (int i : graph.get(idx)) {
                if (!visited[i]) {
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
    }
}
