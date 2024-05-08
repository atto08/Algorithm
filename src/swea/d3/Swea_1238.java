package swea.d3;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
Contact - D3
소요 시간: 29분
단방향 그래프 탐색 문제
 */
public class Swea_1238 {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int test_case = 1; test_case <= 10; test_case++) {

            graph = new ArrayList<>();
            for (int i = 0; i <= 100; i++) {
                graph.add(new ArrayList<>());
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                if (!graph.get(from).contains(to)) {
                    graph.get(from).add(to);
                }
            }

            visited = new boolean[101];
            max = 0;
            bfs(start);
            bw.write("#" + test_case + " " + max + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void bfs(int start) {
        Queue<Node> q = new LinkedList<>();
        visited[start] = true;
        q.offer(new Node(start, 0));

        int maxNode = start, maxDepth = 0;
        while (!q.isEmpty()) {
            Node now = q.poll();

            if (maxDepth == now.depth) {
                if (maxNode < now.node) {
                    maxNode = now.node;
                }
            } else if (maxDepth < now.depth) {
                maxNode = now.node;
                maxDepth = now.depth;
            }

            for (Integer val : graph.get(now.node)) {
                if (!visited[val]) {
                    visited[val] = true;
                    q.offer(new Node(val, now.depth + 1));
                }
            }
        }
        max = maxNode;
    }

    static class Node {
        int node, depth;

        private Node(int node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}
