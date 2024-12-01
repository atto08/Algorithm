package boj.bfs;

import java.io.*;
import java.util.*;

/*
케빈 베이컨의 6단계 법칙 - 실1
소요 시간 - 60분

원인:
0. 그래프 문제를 오랜만에 풀었음
1. 다 방문하지않는 경우가 있지않을까 라는 생각함
2. 시간 복잡도 개념을 다시 본 직후라 시간 계산하며 이해하는데 시간 소모

 */
public class Boj_1389 {
    static int N, M, minIdx, minCnt;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        minCnt = Integer.MAX_VALUE;
        minIdx = Integer.MAX_VALUE;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph.get(s).add(e);
            graph.get(e).add(s);
        }

        for (int n = 1; n <= N; n++) bfs(n);

        System.out.println(minIdx);
    }

    private static void bfs(int n) {
        boolean[] visited = new boolean[N + 1];
        visited[n] = true;
        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(n, 0));
        int sum = 0;
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int depth : graph.get(now.n)) {
                if (!visited[depth]) {
                    q.offer(new Node(depth, now.d + 1));
                    visited[depth] = true;
                    sum += now.d + 1;
                }
            }
        }

        if (minCnt > sum) {
            minCnt = sum;
            minIdx = n;
        } else if (minCnt == sum) {
            if (minIdx > n) {
                minIdx = n;
            }
        }
    }

    static class Node {
        int n, d;

        private Node(int n, int d) {
            this.n = n;
            this.d = d;
        }
    }
}
