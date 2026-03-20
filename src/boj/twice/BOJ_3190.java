package boj.twice;

import java.io.*;
import java.util.*;

/*
뱀 - 골4
소요시간 - 90분

[문제설명]
-
- 뱀 게임이 몇초에 끝나는지 구하기

[풀이설명]

조건을 토대로 다음과 같는 순서로 움직이도록 구현했다.

map[x][y] == 0은 빈칸, 1은 사과가존재하는 칸, -1은 뱀의 몸이 있는 칸

1) 뱀은 벽이나 자신의 몸을 만나면 멈춘다.
    -> 배열의 범위를 벗어나거나 map[x][y] == -1이면 break
2) 1을 통과하면 빈칸이나 사과가 있는 칸
    -> 사과가 없다면 꼬리가 있는 칸을 제거 map[x][y] == 0
3) 2를 지나면 사과의 존재유무와 상관없이 머리는 이동
4) sec초가 지난 후 방향전환이 이뤄지는 초가 되었다면, 방향전환 실시

p.s
- 1. 방향전환 메서드 리턴값을 후위 증감연산자로 설정해서 원래값을 던진 후에 소용없는 값이 증감되었다.
- 2. (0,0) ~ (N-1, N-1)으로 설정하고 뱀의 시작위치를 0,0으로 인식했다. 하지만 문제에선 1,1이 맨위 좌측이라고 언급했다.
- 3. 잘해놓고 초별 방향 길이를 L이 아니라 K로 지정해놨다..
- 글 똑바로 읽고 실전에서 실수하지 않도록 정신차리고 연습하자.
 */
public class BOJ_3190 {
    static int N, K, L;
    static int[][] map;
    static Direction[] directions;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int k = 1; k <= K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            map[x][y] = 1; // 사과위치 입력
        }

        L = Integer.parseInt(br.readLine());
        directions = new Direction[L];
        // X는 오름차순으로 제공 초 걱정 X
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            directions[i] = new Direction(x, c); // 초별 회전방향 입력
        }

        Deque<Spot> deque = new ArrayDeque<>();
        deque.offer(new Spot(0, 0));
        map[0][0] = -1;

        int sec = 0, dir = 0, ti = 0;
        while (!deque.isEmpty()) {
            // 현재 뱀의 위치
            Spot now = deque.peekFirst();

            // 다음칸에 위치
            int nx = now.x + dx[dir];
            int ny = now.y + dy[dir];

            // 시간 경과
            sec++;

            // 벽이면 종료
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) break;

            // 뱀이면 종료
            if (map[nx][ny] == -1) break;

            // 사과 X
            if (map[nx][ny] == 0) {
                Spot rm = deque.pollLast();
                map[rm.x][rm.y] = 0;
            }

            // 머리칸 갱신
            deque.offerFirst(new Spot(nx, ny));
            map[nx][ny] = -1;

            if (ti < L) {
                if (directions[ti].x == sec) {
                    dir = turnDirection(dir, ti++);
                }
            }
        }

        System.out.println(sec);
    }

    private static int turnDirection(int dir, int idx) {
        char d = directions[idx].c;

        if (d == 'L') {
            if (dir > 0) {
                return --dir;
            }
            return 3;
        }

        if (dir < 3) {
            return ++dir;
        }
        return 0;
    }

    static class Spot {
        int x, y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Direction {
        int x;
        char c;

        public Direction(int x, char c) {
            this.x = x;
            this.c = c;
        }
    }
}
