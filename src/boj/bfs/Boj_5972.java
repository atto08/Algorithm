package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
택배 배송 - 골5
소요 시간: 31분
1) 메모리 초과
원인: N <= 50,000 인데, 이차원 배열을 사용해서 그런거 같음
해결: 인접리스트 사용

소요 시간: + 10분
2) 시간 초과
원인: 다익스트라 알고리즘이 아니라 일단 제한을 확인하기 위해 완전탐색으로 구현했기때문에 당연한 결과

소요 시간: + 50분
3) 통과
다익스트라 알고리즘 다시 공부
>> 우선순위 큐를 사용한 다익스트라 알고리즘의 구현 및 이점 학습
 */
public class Boj_5972 {
    static int N, M;
    static ArrayList<ArrayList<CowRoad>> graph;
    static int[] dist;

    static class CowRoad implements Comparable<CowRoad> {
        int P, C;

        private CowRoad(int P, int C) {
            this.P = P;
            this.C = C;
        }

        @Override
        public int compareTo(CowRoad other) {
            return Integer.compare(this.C, other.C);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new CowRoad(B, C));
            graph.get(B).add(new CowRoad(A, C));
        }

        dijkstra(1);

        System.out.println(dist[N]);
    }

    private static void dijkstra(int start) {

        PriorityQueue<CowRoad> pq = new PriorityQueue<>();
        pq.add(new CowRoad(start, 0));

        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE); // 시작점 기준 전부 최단 거리를 구할 것이기 때문에 최대값으로 초기값 설정.
        dist[start] = 0; // 출발점의 초기값 설정. 자기 자신이기 때문에 가중치 0

        while (!pq.isEmpty()) {
            CowRoad current = pq.poll();

            for (CowRoad adjNode : graph.get(current.P)) { // 현재 노드에서 인접한(adjacent) 노드들 확인
                if (dist[adjNode.P] > dist[current.P] + adjNode.C) { // 최단 거리 > 현재 거리 + 인접한 노드로 가는 가중치
                    dist[adjNode.P] = dist[current.P] + adjNode.C;
                    pq.add(new CowRoad(adjNode.P, dist[adjNode.P]));
                }
            }
        }
    }
}