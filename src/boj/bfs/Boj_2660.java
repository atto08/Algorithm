package boj.bfs;

import java.io.*;
import java.util.*;

/*
회장 뽑기 - 골5
소요 시간 - 약 1시간

[설명]
- 현재 노드 N을 기준으로 가장 멀리 있는 node를 방문하기 위해 걸리는 T가 N의 회장후보 점수.
- 각각의 노드에서 모든 노드에 대해서 최단 거리로 방문하는 경우를 체크해야함.
- 이 중 배열리스트를 사용하여 각각의 노드에서 bfs로 방문할 노드에 대한 최단경로 체크.
 */
public class Boj_2660 {
    static int f1, f2;
    static ArrayList<ArrayList<Integer>> map;
    static boolean[] visited;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        map = new ArrayList<>();
        for (int i = 0; i <= N; i++) map.add(new ArrayList<>());

        // 관계도 형성
        while (f1 >= 0 || f2 >= 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            f1 = Integer.parseInt(st.nextToken());
            f2 = Integer.parseInt(st.nextToken());

            if (f1 == -1 && f2 == -1) break;

            map.get(f1).add(f2);
            map.get(f2).add(f1);
        }

        for (int idx = 1; idx <= N; idx++) {
            visited = new boolean[N + 1];
            visited[idx] = true;
            bfs(idx);
        }

        Node first = pq.poll(); // 첫번째 회장 후보
        int cnt = 1; // 회장 후보 수
        int minScore = first.d; // 가장 작은 점수
        StringBuilder sb = new StringBuilder(); // 회장 후보들
        sb.append(first.i).append(" ");

        // 회장 후보 순서대로 뽑기
        while (!pq.isEmpty()) {

            Node now = pq.poll();
            if (now.d == minScore) {
                cnt++;
                sb.append(now.i).append(" ");
            } else {
                break;
            }
        }
        bw.write(minScore + " " + cnt + "\n");
        bw.write(sb + " ");

        bw.flush();
    }

    static void bfs(int node) {

        Queue<Node> q = new LinkedList<>();
        for (int n : map.get(node)) {
            q.offer(new Node(1, n));
            visited[n] = true;
        }

        int depth = 1;
        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.d > depth) depth = now.d;

            for (int n : map.get(now.i)) {
                if (!visited[n]) {
                    visited[n] = true;
                    q.offer(new Node(now.d + 1, n));
                }
            }
        }

        pq.offer(new Node(depth, node));
    }

    static class Node implements Comparable<Node> {
        int d, i;

        Node(int d, int i) {
            this.d = d;
            this.i = i;
        }

        @Override
        public int compareTo(Node other) {
            if (this.d == other.d) return Integer.compare(this.i, other.i);
            return Integer.compare(this.d, other.d);
        }
    }
}
