package boj.bfs;

import java.io.*;
import java.util.*;

/*
시작 접근은 좋았음
1) 시간초과 발생
==> 겹치는 경우에도 계속 방문
방문여부 배열 생성
==> indexOutBoundException
다음 수가 될 nextX의 크기를 nextX > N의 젤 큰수 || nextX < 젤 작은수 로 지정해줌
 */
public class Boj_1697 {
    static int[] dx = {-1, 1, 2};
    static int N, K;
    static int[] visited = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Arrays.fill(visited, -1);
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();
        System.out.println(visited[K]);
    }

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        visited[N] = 0;

        boolean check = false;
        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int i = 0; i < 3; i++) {
                int nextX = x;
                if (i != 2) {
                    nextX += dx[i];
                } else {
                    nextX *= dx[i];
                }

                if (nextX < 0 || nextX > 100000) {
                    continue;
                }

                if (visited[nextX] == -1) {
                    visited[nextX] = visited[x] + 1;
                    if (N == K) {
                        return;
                    }
                    queue.offer(nextX);
                }
            }
        }
    }
}
