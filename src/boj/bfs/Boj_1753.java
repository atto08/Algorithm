package boj.bfs;

import java.io.*;
import java.util.*;

/*
최단 경로 - 골4 (다익스트라 대표유형)
소요 시간: 1시간 초과
>> 도착지점을 설정하지 않으면 언제 탐색을 종료해야할지 기준을 정하지 못함
다익스트라 유형 학습1 - 블로그 참조.
 */
class Pair implements Comparable<Pair> {
    int node;
    int cost;

    public Pair(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Pair o) {
        return cost - o.cost;
    }
}

public class Boj_1753 {
    static int V, E, K;
    static List<Pair>[] graph;
    static int[] dist; // 최단거리 배열
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        graph = new ArrayList[V + 1];
        dist = new int[V + 1];

        Arrays.fill(dist, INF);

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Pair(v, w));
        }

        dijkstra(K);
        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) {
                bw.write("INF\n");
            } else {
                bw.write(dist[i] + "\n");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static void dijkstra(int start) {
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        boolean[] check = new boolean[V + 1];
        queue.add(new Pair(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Pair now = queue.poll();
            int cur = now.node;

            if (check[cur]) continue;
            check[cur] = true;

            for (Pair n : graph[cur]) {
                if (dist[n.node] > dist[cur] + n.cost) {
                    dist[n.node] = dist[cur] + n.cost;
                    queue.add(new Pair(n.node, dist[n.node]));
                }
            }
        }
    }
}
