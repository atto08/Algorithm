package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
숨바꼭질3 - 골5
소요 시간: 17분
>> 숨바꼭질2 이후 바로 풀어서 시간절약함

숨바꼭질2와 다르게 순간이동은 0초 후임
그래서 순간이동을 먼저 체크해줘야함

1) 틀렸습니다.
원인: 순간이동 경우을 맨 마지막에 체크해버림
해결: 순간이동 경우를 맨 처음으로 순서변경

2) 통과
 */

public class Boj_13549 {
    static int N, K, minSec;
    static boolean[] visit;
    static int[] dx = {2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visit = new boolean[100001];
        bfs();
    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(N, 0));

        while (!q.isEmpty()) {
            Point now = q.poll();
            visit[now.location] = true;

            if (now.location == K) {
                minSec = now.sec;
                break;
            }

            for (int i = 0; i < 3; i++) {
                int nx = now.location;
                if (i == 0) {
                    nx *= dx[i];
                } else {
                    nx += dx[i];
                }

                if (nx < 0 || nx > 100000) {
                    continue;
                }

                if (!visit[nx]) {
                    if (i == 0) {
                        q.offer(new Point(nx, now.sec));
                    } else {
                        q.offer(new Point(nx, now.sec + 1));
                    }
                }
            }
        }

        System.out.print(minSec);
    }

    static class Point {
        int location, sec;

        private Point(int location, int sec) {
            this.location = location;
            this.sec = sec;
        }
    }
}
