package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
최단경로 - 골4
소요 시간: 24분

다익스트라 대표유형 문제
다익스트라 특징 -
양의 간선이 존재(양수 가중치) - 음수는 X
출발점이 존재O
 */
public class Boj_1753 {
    static class Node implements Comparable<Node> {
        int n, d;

        private Node(int n, int d) {
            this.n = n;
            this.d = d;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.d, other.d);
        }
    }

    static int V, E, K;
    static int[] dist;
    static ArrayList<ArrayList<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        dijkstra();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (dist[i] < Integer.MAX_VALUE) {
                sb.append(dist[i]);
            } else {
                sb.append("INF");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(K, 0));
        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Node adjNode : graph.get(current.n)) {
                if (dist[adjNode.n] > dist[current.n] + adjNode.d) {
                    dist[adjNode.n] = dist[current.n] + adjNode.d;
                    pq.offer(new Node(adjNode.n, dist[adjNode.n]));
                }
            }
        }
    }
}
