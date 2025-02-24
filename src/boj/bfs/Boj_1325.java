package boj.bfs;

import java.io.*;
import java.util.*;

/*
효율적인 해킹 - 실1
소요 시간 - 1시간 초과
분류 - BFS

문제 설명:
- 컴퓨터는 신뢰하는 관계와 신뢰하지 않는 관계가 존재.
- A가 B를 신뢰하는 경우(A -> B)에는 B를 해킹하면 A도 해킹 가능
- 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 구하기(다수면 오름차순)
- N: 컴퓨터 개수 & M: 컴퓨터 간의 신뢰 관계 (1 <= N <= 10,000 && 1 <= M <= 100,000)

풀이 설명:
1차 시도 - 틀렸습니다(1%) 28분
- 이중 배열리스트 사용 & B -> A 단방향 그래프로 설정
- 1 ~ N번 노드 별 방문 초기화 후 방문하지 않은 노드 방문(연결된 모든 노드 탐색)
- 속도: 약 10.6초

2차 시도 - 시간 초과(21%) +36분
- A -> B 단방향 그래프로 수정
- 속도: 약 1.4초

3차 시도 - 시간 초과(29%) +15분(참고)
- 배열리스트 타입의 배열 사용으로 수정

4차 시도 - 통과(100%) +8분
- 한 번에 해킹 가능한 최대 컴퓨터 수 Max 갱신과 컴퓨터를 구하는 로직 통합

 */
public class Boj_1325 {
    static int N, M;
    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static int[] connect;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[k].add(v);
        }

        connect = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            bfs(i);
        }

        int max = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            pq.offer(new Node(i, connect[i]));
            if (max < connect[i]) max = connect[i];
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (max == now.c) {
                sb.append(now.n).append(" ");
            } else {
                break;
            }
        }

        System.out.println(sb.toString().trim());
    }

    private static void bfs(int node) {
        Queue<Integer> q = new LinkedList<>();
        visited[node] = true;
        q.offer(node);
        connect[node]++;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int n : graph[now]) {
                if (!visited[n]) {
                    visited[n] = true;
                    connect[n]++;
                    q.offer(n);
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int n, c;

        public Node(int n, int c) {
            this.n = n;
            this.c = c;
        }

        public int compareTo(Node other) {
            if (this.c == other.c) return Integer.compare(this.n, other.n);
            return Integer.compare(other.c, this.c);
        }
    }
}
