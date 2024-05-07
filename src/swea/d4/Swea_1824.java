package swea.d4;

import java.io.*;
import java.util.*;

/*
혁진이의 프로그램 검증 - D4
소요 시간: 1시간 초과(100분)
원인1: '@'를 만나지않을때 내 코드또한 무한루프에 걸림
해결1: 방문처리를 4차원배열[r][c][m][d]로 구성해서 똑같은 기록의 방문이 존재하면 탈출하도록 수정

원인2: '?'를 만날때 Math.random을 사용해서 무작위를 넣었음 >> 4방향 모두 동일한 확률이 있다는 소리는 4경우를 다넣어라는 이야기였음
즉, 문제의 의도를 잘못 파악함
해결2: '?'를 만날때 4방향을 동시에 넣어줌

의외로 빡구현 문제
아쉬운 부분
1. 겹치는 요소를 한번에 체크하기위해 4차원 배열을 사용할 생각을 하지못한점
2. 문제 질문의 의도를 제대로 파악하지 못한 것
3. 깔끔한 코드 구성을 위해 코드를 치지도않고 생각과 노트에 끄적이다가 시간을 과소비함
 */
public class Swea_1824 {
    static int R, C;
    static String result;
    static boolean trigger;
    static String[][] map;
    static int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};
    static String[] directions = {">", "<", "v", "^", "_", "|"};
    static boolean[][][][] visited;
    static Queue<RCMD> q;
    static List<String> numbers = new ArrayList<>(), dirOrders = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 10; i++) {
            numbers.add(String.valueOf(i));
            if (i < 6) {
                dirOrders.add(directions[i]);
            }
        }

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new String[R][C];

            for (int i = 0; i < R; i++) {
                map[i] = br.readLine().split("");
            }

            result = "NO";
            trigger = false;
            q = new LinkedList<>();
            visited = new boolean[R][C][16][4]; // 행,열,메모리,방향

            bfs();

            bw.write("#" + test_case + " " + result + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static void bfs() {

        visited[0][0][0][0] = true;
        checkOrder(new RCMD(0, 0, 0, 0));

        if (trigger) {
            result = "YES";
        } else {

            while (!q.isEmpty()) {
                checkOrder(q.poll());
                if (trigger) {
                    result = "YES";
                }
            }
        }
    }

    private static void checkOrder(RCMD now) {

        int r = now.r, c = now.c, memory = now.m, direction = now.d;

        if (numbers.contains(map[r][c])) { // 숫자인지
            memory = Integer.parseInt(map[r][c]);

            checkArrayIndex(r, c, memory, direction);

        } else if (dirOrders.contains(map[r][c])) { // 방향인지
            int idx = 0;
            for (int i = 0; i < directions.length; i++) {
                if (directions[i].equals(map[r][c])) {
                    idx = i;
                }
            }

            if (idx < 4) { // > < v ^
                direction = idx;
            } else if (idx == 4) { // _
                if (memory == 0) {
                    direction = 0;
                } else {
                    direction = 1;
                }
            } else if (idx == 5) { // |
                if (memory == 0) {
                    direction = 2;
                } else {
                    direction = 3;
                }
            }
            checkArrayIndex(r, c, memory, direction);

        } else if (map[r][c].equals("+")) {
            if (memory == 15) {
                memory = 0;
            } else {
                memory++;
            }
            checkArrayIndex(r, c, memory, direction);

        } else if (map[r][c].equals("-")) {
            if (memory == 0) {
                memory = 15;
            } else {
                memory--;
            }
            checkArrayIndex(r, c, memory, direction);

        } else if (map[r][c].equals("?")) {
            for (int dir = 0; dir < 4; dir++) { // 4방향 모두 넣어주기
                checkArrayIndex(r, c, memory, dir);
            }
        } else if (map[r][c].equals("@")) {
            trigger = true;
        } else {
            checkArrayIndex(r, c, memory, direction);
        }
    }

    private static void checkArrayIndex(int r, int c, int memory, int dir) {

        int nr = r + dr[dir];
        int nc = c + dc[dir];

        if (nr < 0) {
            nr = R - 1;
        } else if (nc < 0) {
            nc = C - 1;
        } else if (nr >= R) {
            nr = 0;
        } else if (nc >= C) {
            nc = 0;
        }

        if (!visited[nr][nc][memory][dir]) {
            visited[nr][nc][memory][dir] = true;
            q.offer(new RCMD(nr, nc, memory, dir));
        }
    }

    static class RCMD {
        int r, c, m, d;

        private RCMD(int r, int c, int m, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.d = d;
        }
    }
}