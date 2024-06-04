package boj.bfs;

import java.io.*;
import java.util.*;

/*
특정 거리의 도시찾기 - 실2
소요 시간: 40분

메모리: 259MB / 시간: 896ms
 */
public class Boj_18352 {
    static int N, M, K, X;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 도로 M개
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
        }

        bfs();
    }

    private static void bfs() {

        Queue<City> q = new LinkedList<>();
        q.offer(new City(X, 0));
        visited[X] = true;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (!q.isEmpty()) {

            City now = q.poll();
            if (now.d > K) {
                break;
            }

            if (now.d == K) {
                pq.offer(now.n);
                continue;
            }

            for (int n : graph.get(now.n)) {
                if (!visited[n]) {
                    visited[n] = true;
                    q.offer(new City(n, now.d + 1));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (pq.isEmpty()) {
            sb.append(-1);
        } else {
            while (!pq.isEmpty()) {
                sb.append(pq.poll()).append("\n");
            }
        }
        System.out.println(sb);
    }

    static class City {
        int n, d;

        private City(int n, int d) {
            this.n = n;
            this.d = d;
        }
    }
}
