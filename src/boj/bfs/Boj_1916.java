package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
최소 비용 구하기 - 골5
소요 시간: 30분 - 데이크스트라 배운 유형 그대로 사용해봤기 때문

1) 시간 초과
원인: 방문처리를 하지않아 불필요한 작업까지 수행 >> 수행시간 증가++
해결: 방문처리를 함

2) 통과

우선순위큐가 항상 최단거리라는 값을 보장해주기 때문에 재방문여부가 필요없고,
재방문을 하지않는다면 최단거리를 찾는 시간이 단축된다.
>> 위와 같은 이유로 방문처리를 하여 불필요한 반복작업을 제거하면 수행시간이 단축됨
 */
public class Boj_1916 {
    static class Node implements Comparable<Node> {
        int idx, dist;

        private Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    static int N, M, start, end;
    static ArrayList<ArrayList<Node>> graph;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Node(e, w));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra();

        System.out.println(dist[end]);
    }

    private static void dijkstra() {
        dist = new int[N + 1];
        visited = new boolean[N + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentIdx = current.idx;

            if (visited[currentIdx]) continue;
            visited[currentIdx] = true;

            for (Node adjNode : graph.get(currentIdx)) {
                if (dist[adjNode.idx] > dist[currentIdx] + adjNode.dist) {
                    dist[adjNode.idx] = dist[currentIdx] + adjNode.dist;
                    pq.offer(new Node(adjNode.idx, dist[adjNode.idx]));
                }
            }
        }
    }
}
