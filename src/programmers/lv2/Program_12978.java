package programmers.lv2;

import java.util.*;
/*
배달 - lv2
소요시간 - 1시간 초과

풀이설명:
다익스트라 알고리즘 활용

구현 놓친 부분
- 우선순위 큐를 활용해야했다. -> 단순 큐 사용했다.
- 더 짧은 거리로 재방문이 가능했어야함. -> 처음 방문했을때로 처리했다.
- 현재 노드에서 새로운 최단경로가 발견된 경우만 큐에 추가해야했다. -> 모든 인접노드를 다 큐에 넣었다.

p.s
어젠 잘 풀었는데 아쉽다.
내일 시험을 얼마나 잘보려고 ~ 그러는지 ~
 */
public class Program_12978 {
    public static int solution(int N, int[][] road, int K) {
        // 소요시간
        int[] eta = new int[N + 1];
        Arrays.fill(eta, Integer.MAX_VALUE);
        // 간선 가중치 있는 맵
        int[][] map = new int[N + 1][N + 1];
        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int time = r[2];
            // 이미 연결되어 있다면 최소값으로 갱신
            if (map[a][b] == 0 || time < map[a][b]) {
                map[a][b] = map[b][a] = time;
            }
        }

        return dijkstra(N, K, eta, map);
    }

    private static int dijkstra(int N, int K, int[] eta, int[][] map) {
        PriorityQueue<Delivery> q = new PriorityQueue<>();
        eta[1] = 0;
        q.offer(new Delivery(1, 0)); // 시작: 마을 1, 시간 0

        while (!q.isEmpty()) {
            Delivery now = q.poll();

            // 더 빠른 경로가 이미 있으면 무시
            if (eta[now.village] < now.time) continue;

            for (int i = 1; i <= N; i++) {
                if (map[now.village][i] > 0) {
                    int newTime = now.time + map[now.village][i];
                    if (newTime < eta[i]) {
                        eta[i] = newTime;
                        q.offer(new Delivery(i, newTime));
                    }
                }
            }
        }

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (eta[i] <= K) count++;
        }
        return count;
    }

    // compareTo 오버라이딩으로 우선순위 설정
    static class Delivery implements Comparable<Delivery> {
        int village; // 마을 번호
        int time;    // 걸린 시간

        public Delivery(int village, int time) {
            this.village = village;
            this.time = time;
        }

        @Override
        public int compareTo(Delivery o) {
            return this.time - o.time; // 시간이 짧은 게 우선
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}}, 3));
        System.out.println(solution(6, new int[][]{{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}}, 4));
    }
}
