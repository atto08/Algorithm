package boj.implementation;

import java.io.*;
import java.util.*;

/*
[1시간 내에 풀이 실패]
1) 예제3 경우 이해 X
==> 내 해석: 사과를 먹지않으면 현재 칸까지 꼬리가 한번에 줄어든다고 생각
            그래서 방문했던 모든 칸을 초기화 함
==> 올바른 해석: 사과를 먹지 않은 경우에 한칸 씩 꼬리가 이동한다
                방문했던 곳들을 한칸 씩 방문하지 않은 처리 해야함
2) 틀렸습니다
==> 먹은 사과를 치우지 않았음.
 */
public class Boj_3190 {
    static int N, K, L, sec;
    static int[][] map;
    static boolean[][] visited;
    static int[] secArr;
    static char[] commandArr;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        StringTokenizer st;
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = 1;
        }

        L = Integer.parseInt(br.readLine());
        secArr = new int[L];
        commandArr = new char[L];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            secArr[i] = Integer.parseInt(st.nextToken());
            commandArr[i] = st.nextToken().charAt(0);
        }

        bfs();

        System.out.println(sec);
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> visit = new LinkedList<>();
        queue.offer(new int[]{1, 1});
        visit.offer(new int[]{1, 1});
        int dir = 0;
        int idx = 0;

        while (!queue.isEmpty()) {
            ++sec;
            int[] xy = queue.poll();

            int nextX = xy[0] + dx[dir];
            int nextY = xy[1] + dy[dir];
            // 꼬리나 벽이 있다면
            if (nextX < 1 || nextY < 1 || nextX >= N + 1 || nextY >= N + 1) {
                break;
            }
            // 꼬리 or 몸통
            else if (visited[nextX][nextY]) {
                break;
            }

            visited[nextX][nextY] = true;

            // 사과가 없다면 꼬리 위치칸을 비우기
            if (map[nextX][nextY] == 0) {
                int[] rc = visit.poll();
                visited[rc[0]][rc[1]] = false;
            } else if (map[nextX][nextY] == 1) {
                map[nextX][nextY] = 0;
            }

            if (idx < L && sec == secArr[idx]) {
                char C = commandArr[idx];
                if (C == 'D') {
                    dir = cvtDirRight(dir);
                } else {
                    dir = cvtDirLeft(dir);
                }
                idx++;
            }
            queue.offer(new int[]{nextX, nextY});
            visit.offer(new int[]{nextX, nextY});
        }
    }

    private static int cvtDirRight(int dir) {
        if (dir == 3) {
            return 0;
        } else {
            return dir + 1;
        }
    }

    private static int cvtDirLeft(int dir) {
        if (dir == 0) {
            return 3;
        } else {
            return dir - 1;
        }
    }
}
