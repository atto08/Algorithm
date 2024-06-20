package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
파티 - 골3
소요 시간: 55분

1) 통과
풀이 방법:
X를 제외한 모든 마을에서 X까지 최단 거리 구하기
X를 출발점으로 모든 마을까지 걸리는 시간 구하기

위 두 경우를 더한 마을 중 최댓값이 나오는 마을 출력
 */
public class Boj_1238 {
    static class Node implements Comparable<Node> {
        int n, t;

        private Node(int n, int t) {
            this.n = n;
            this.t = t;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.t, other.t);
        }
    }

    static int N, M, X;
    static ArrayList<ArrayList<Node>> village;
    static int[] dist, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        village = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            village.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            village.get(s).add(new Node(e, t));
        }

        result = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            dijkstra(i, i == X);
        }

        Arrays.sort(result);
        System.out.println(result[N]);
    }

    private static void dijkstra(int start, boolean trigger) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Node adjNode : village.get(current.n)) {
                if (dist[adjNode.n] > dist[current.n] + adjNode.t) {
                    dist[adjNode.n] = dist[current.n] + adjNode.t;
                    pq.offer(new Node(adjNode.n, dist[adjNode.n]));
                }
            }
        }
        if (trigger) {
            for (int i = 1; i <= N; i++) {
                result[i] += dist[i];
            }
            return;
        }

        result[start] += dist[X];
    }
}
