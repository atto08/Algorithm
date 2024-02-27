package boj.dfs;

import java.io.*;
import java.util.*;

public class Boj_1167 {
    static StringTokenizer st;
    static ArrayList<ArrayList<Edge>> graph;
    static boolean[] visited;
    static int N, max, maxIdx;

    static class Edge {
        int vertex;
        int weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>(N + 1);
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            while (true) {
                int v = Integer.parseInt(st.nextToken());
                if (v == -1)
                    break;

                int w = Integer.parseInt(st.nextToken());
                graph.get(k).add(new Edge(v, w));
            }
        }

        dfs(1, 0);

        visited = new boolean[N + 1];
        dfs(maxIdx, 0);

        System.out.println(max);
    }

    public static void dfs(int node, int distance) {
        if (distance > max) {
            max = distance;
            maxIdx = node;
        }
        visited[node] = true;
        for (Edge edge : graph.get(node)) {
            if (!visited[edge.vertex]) {
                dfs(edge.vertex, distance + edge.weight);
            }
        }

    }
}