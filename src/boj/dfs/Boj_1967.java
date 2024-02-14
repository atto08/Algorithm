package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1) 메모리 초과
이중 배열을 사용했음
N의 범위를 잘못 계산하여 메모리 초과 발생
1. 연결된 노드와 가중치를 갖는 클래스를 생성
2. 해당 클래스 타입을 갖는 배열리스트를 인접리스트로 구현
 */
public class Boj_1967 {
    static StringTokenizer st;
    static int N, max, result;
    static ArrayList<ArrayList<Edge>> graph;
    static boolean[] visited;

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

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(k).add(new Edge(v, w));
            graph.get(v).add(new Edge(k, w));
        }

        result = 0;
        for (int i = 1; i <= N; i++) {
            if (graph.get(i).size() == 1) {
                dfs(i);
                Arrays.fill(visited, false);
            }
        }

        System.out.println(result);
    }

    public static void dfs(int node) {
        visited[node] = true;
        for (Edge edge : graph.get(node)) {
            if (!visited[edge.vertex]) {
                max += edge.weight;
                dfs(edge.vertex);
                max -= edge.weight;
            }
        }

        if (max > result) {
            result = max;
        }
    }
}
