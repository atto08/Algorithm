package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
회의실 배정 - 실1
소요 시간: 1시간

원인: 그리디 문제라고 알고 시작해서 최선의 경우를 찾다가 오래걸림
해결: 문제를 예제를 살펴보니 끝나는 시간이 빠른 녀석 순서대로 우선순위 큐를 구현하면 될것같다고 생각됨

1) 틀렸습니다.(85%)

원인: 모든 e가 같은 값이고 s기준 내림 차순으로 입력값이 주어지면 값이 하나의 경우만 선택함 (반례)
해결: e값이 같다면 s값을 오름차순으로 뽑도록 compareTo 메소드 변경 >> 새로 배운 방법 - 알고 싶었던 구현 방법!

2) 통과
 */
public class Boj_1931 {
    static class Node implements Comparable<Node> {
        int s, e;

        private Node(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Node other) {
            if (this.e == other.e) {
                // e 값이 같으면 s 값을 기준으로 오름차순 정렬
                return Integer.compare(this.s, other.s);
            } else {
                // e 값이 다르면 e 값을 기준으로 오름차순 정렬
                return Integer.compare(this.e, other.e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Node> pq = new PriorityQueue<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.offer(new Node(start, end));
        }

        Node first = pq.poll();
        int e = first.e;
        int classRoomCnt = 1;
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.s >= e) {
                classRoomCnt++;
                e = now.e;
            }
        }

        System.out.println(classRoomCnt);
    }
}
