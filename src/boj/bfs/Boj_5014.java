package boj.bfs;

import java.io.*;
import java.util.*;
/*
스타트링크 - 실1
소요 시간: 31분
1) 틀렸습니다.(81%)
원인: 층의 범위를 0 ~ F로 설정해놨음
해결: 1~F로 변경
소요 시간: +4분
2) 통과
 */
public class Boj_5014 {
    static int F, S, G;
    static boolean[] visited;
    static int[] upDown = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken()); // 건물층 수
        S = Integer.parseInt(st.nextToken()); // 강호 현재위치
        G = Integer.parseInt(st.nextToken()); // 스타트링크 위치
        upDown[0] = Integer.parseInt(st.nextToken());
        upDown[1] = -Integer.parseInt(st.nextToken());

        visited = new boolean[F + 1];

        if (!bfs()) {
            System.out.println("use the stairs");
        }
    }

    private static boolean bfs() {
        Queue<Floor> q = new LinkedList<>();
        q.offer(new Floor(S, 0));
        visited[S] = true;

        boolean canGo = false;
        while (!q.isEmpty()) {

            Floor nowF = q.poll();

            if (nowF.f == G) {
                canGo = true;
                System.out.println(nowF.cnt);
                break;
            }

            for (int i = 0; i < 2; i++) {
                int nf = nowF.f + upDown[i];

                if (nf < 1 || nf > F) {
                    continue;
                }

                if (!visited[nf]) {
                    visited[nf] = true;
                    q.offer(new Floor(nf, nowF.cnt + 1));
                }
            }
        }
        return canGo;
    }

    static class Floor {
        int f, cnt;

        private Floor(int f, int cnt) {
            this.f = f;
            this.cnt = cnt;
        }
    }
}
