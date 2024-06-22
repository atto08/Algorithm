package boj.implementation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
쿠키의 신체측정 - 실4
소요 시간: 1시간

원인: 탐색 출발점을 잡는데 오래걸림
해결:
머리 부터 찾은 후 심장에서 왼팔, 오른팔, 몸통을 찾고
몸통의 끝에서 양다리를 찾도록 구현
 */
public class Boj_20125 {

    static int N;
    static char[][] map;
    static int[] dx = {0, 0, 1}, dy = {-1, 1, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new char[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            char[] arr = sc.next().toCharArray();
            for (int j = 1; j <= N; j++) {
                map[i][j] = arr[j - 1];
            }
        }

        boolean trigger = false;
        for (int i = 1; i <= N; i++) {
            if (!trigger) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] == '*') {
                        measureCookie(i, j);
                        trigger = true;
                        break;
                    }
                }
            }
        }

        System.out.println(sb);
    }

    private static void measureCookie(int x, int y) {

        sb.append(x + 1).append(" ").append(y).append("\n"); // 심장 위치

        boolean[][] visited = new boolean[N + 1][N + 1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x + 1, y});
        visited[x + 1][y] = true;

        int[] size = new int[5];
        int bx = x + 1;
        while (!q.isEmpty()) { // 왼팔, 오른팔, 몸통
            int[] now = q.poll();

            for (int i = 0; i < 3; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 1 || ny < 1 || nx > N || ny > N) continue;

                if (!visited[nx][ny] && map[nx][ny] == '*') {
                    size[i]++;
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                    if (i == 2) {
                        bx++;
                    }
                }
            }
        }

        size[3]++;
        size[4]++;
        q.offer(new int[]{bx + 1, y - 1});
        q.offer(new int[]{bx + 1, y + 1});
        while (!q.isEmpty()) { // 왼다리, 오른다리
            int[] now = q.poll();

            int nx = now[0] + 1;

            if (nx < 1 || nx > N) continue;

            if (!visited[nx][now[1]] && map[nx][now[1]] == '*') {
                if (now[1] == y - 1) {
                    size[3]++;
                } else {
                    size[4]++;
                }
                q.offer(new int[]{nx, now[1]});
            }
        }

        for (int num : size) {
            sb.append(num).append(" ");
        }
    }
}
