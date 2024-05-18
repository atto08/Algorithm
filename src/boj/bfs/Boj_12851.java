package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
숨바꼭질2 - 골4
소요 시간: 52분
풀이 설명-
처음에 방문체크를 4가지 경우별로 생각함
한번 방문하면 다똑같다는 걸 알아차림(규칙 발견) >> 일차원배열 방문 구현
>> 한번 방문 이후 나오는 숫자들은 반복해서 나오기 때문

1) 틀렸습니다. (46%)
원인: NullPointException 발생
해결: 반복문의 조건을 q가 빌때까지로 변경함

2) 통과

 */
public class Boj_12851 {
    static int N, K, minSec = Integer.MAX_VALUE, minCnt;
    static boolean[] visit;
    static int[] dx = {0, -1, 1, 2};

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

            if (now.sec > minSec) {
                break;
            }

            if (now.location == K && now.sec <= minSec) {
                if (now.sec < minSec) {
                    minSec = now.sec;
                    minCnt = 0;
                }
                minCnt++;
            }

            visit[now.location] = true;

            for (int i = 1; i <= 3; i++) {
                int nx = now.location;
                if (i != 3) {
                    nx += dx[i];
                } else {
                    nx *= dx[i];
                }

                if (nx < 0 || nx > 100000) {
                    continue;
                }

                if (!visit[nx]) {
                    q.offer(new Point(nx, now.sec + 1));
                }
            }
        }

        System.out.print(minSec + "\n" + minCnt);
    }

    static class Point {
        int location, sec;

        private Point(int location, int sec) {
            this.location = location;
            this.sec = sec;
        }
    }
}
