package codetree;

import java.io.*;
import java.util.*;

/*
F[i][j] = [i][j]학생의 신봉 음식
B[i][j] = [i][j]학생의 신앙심

1) 아침 시간
- 모든 학생은 신앙심 +1
- B[i][j] + 1씩

2) 점심 시간
- 학생[i][j] 기준 상하좌우 인접한 학생들과 신봉음식(B)가 완전히 같은 경우만 그룹 형성
- 그룹내 대표자 선발
    - 신앙심이 가장 큰 사람
    - B[r1][c1] == B[r2][c2]
        - r이 가장 작은사람
        - c가 가장 작은 사람


3) 저녁 시간

x는 간절함 (x = B - 1), B = 1
- x는 1을 제외한 나머지 신앙심을 사용
- B는 1이됨

y는 전파 대상의 신앙심

방향 - B를 4로 나눈 나머지로
0상 1하 2좌 3우 방향으로만 전파

강한 전파(x > y) 경우
- 간절함(x) -= (y + 1)
- 대상의 B += 1
- F는 전파자의 F와 동화됨
- 전파자의 간절함(x)이 0이되면 전파 종료

약한 전파(x <= y) 경우
- F는 전파자의 F만큼 더해짐
- 대상의 B += x
- 간절함(x) == 0
*/
public class Main_25년상반기오전1번 {

    static int N, T;

    // F(신봉 음식), B(신앙심)
    static int[][] F, B;

    // Test 별 결과
    static long[] result;

    // 방문체크
    static boolean[][] visited;

    // 인접방향 체크 - 상하좌우
    static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    // 신봉 음식 체크 순서
    // 민트초코우유(7) >> 민트초코(6) >> 민트우유(5) >> 초코우유(3) >> 우유(1) >> 초코(2) >> 민트(4)
    static final int[] seq = {7, 6, 5, 3, 1, 2, 4};

    // 대표자 정하기 위한 가장 높은 좌표
    static int maxBr, maxBc, maxBelief;

    // 대표자들 위치
    static int[][] leaders;

    static class Pair implements Comparable<Pair> {
        int b, r, c, k;

        public Pair(int b, int r, int c, int k) {
            this.b = b;
            this.r = r;
            this.c = c;
            this.k = k;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.k != other.k) return Integer.compare(this.k, other.k);
            if (this.b != other.b) return Integer.compare(other.b, this.b);
            if (this.r != other.r) return Integer.compare(this.r, other.r);
            return Integer.compare(this.c, other.c);
        }
    }

    // ** 아침 시간 **
    private static void morning() {
        // N*N B[i][j] 신앙심 +1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                B[i][j]++;
            }
        }
    }

    // ** 점심 시간 **
    private static void afternoon() {

        visited = new boolean[N][N];

        // 대표자 위치 담긴 큐
        Queue<int[]> leaderQ = new LinkedList<>();

        // 인접체크 & 대표자 위치 파악
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    maxBr = i;
                    maxBc = j;
                    maxBelief = B[i][j];
                    dfsAndMaxVal(i, j, F[i][j]);
                    leaderQ.offer(new int[]{maxBr, maxBc});
                }
            }
        }

        visited = new boolean[N][N];
        leaders = new int[leaderQ.size()][3];
        int idx = 0; // 대표자 좌표넣기 위한 인덱스 값
        // 신앙심 증감
        while (!leaderQ.isEmpty()) {
            int[] now = leaderQ.poll();
            maxBr = now[0];
            maxBc = now[1];
            int food = F[maxBr][maxBc];

            visited[maxBr][maxBc] = true;  // 대표 사전 방문
            dfsAndSelecLeader(maxBr, maxBc, food);

            leaders[idx][0] = now[0];
            leaders[idx][1] = now[1];
            leaders[idx][2] = food;

            idx++;
        }
    }

    // 우선순위 구할때 종류 갯수 세는 용도
    private static int getCount(int kind) {
        if (kind == 1 || kind == 2 || kind == 4) return 1;
        if (kind == 3 || kind == 5 || kind == 6) return 2;
        return 3;
    }

    // *** 저녁 시간 ***
    private static void evening() {

        // 그룹의 대표자들의 신앙 전파시간
        // 대표자 우선순위 구하기

        // 그룹 작은 순서 -> 신앙심 큰 순서 -> x값이 작은 순서 -> y값이 작은 순서
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int[] leader : leaders) {
            int r = leader[0], c = leader[1], k = getCount(leader[2]);
            pq.offer(new Pair(B[r][c], r, c, k));
        }

        // 우선순위 대로 전파 시작
        while (!pq.isEmpty()) {
            Pair now = pq.poll();
            // 다른 대표자에게 전파당한 학생은 전파 X
            if (now.b != B[now.r][now.c]) continue;

            // 전파 방향
            int dir = B[now.r][now.c] % 4;
            spreadF(now.r, now.c, dir);
        }

        // N*N 순회
        result = new long[8];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int i = F[r][c];
                result[i] += B[r][c];
            }
        }
    }


    // 전파 시작
    private static void spreadF(int r, int c, int dir) {

        // 간절함
        int x = B[r][c] - 1;
        // 전파자의 신앙심 1
        B[r][c] = 1;

        // 확인할 위치
        int nr = r;
        int nc = c;

        while (true) {

            // 자리 이동
            nr += dr[dir];
            nc += dc[dir];

            // 범위를 벗어나면 전파 종료
            if (nr < 0 || nc < 0 || nr >= N || nc >= N) break;

            // 간절함(x) == 0 이면 종료
            if (x <= 0) break;

            // 신봉 음식이 완전 같으면 건너뛰기
            if (F[r][c] == F[nr][nc]) continue;

            // 전파 대상의 신앙심
            int y = B[nr][nc];

            // 강한 전파
            if (x > y) {
                // 신앙심 증가 및 간절함 감소
                x -= ++y;
                B[nr][nc] = y;
                // 신봉음식 동화
                F[nr][nc] = F[r][c];
            }
            // 약한 전파
            else {
                // 신앙심 x증가 및 간절함 = 0
                y += x;
                B[nr][nc] = y;
                x = 0;
                // 신봉음식 추가
                F[nr][nc] |= F[r][c];
                break;
            }
        }
    }


    // 대표자 선정 및 나머지 신앙심 증감
    private static void dfsAndSelecLeader(int r, int c, int food) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

            if (visited[nr][nc]) continue;

            if (food != F[nr][nc]) continue;
            visited[nr][nc] = true;
            B[nr][nc]--;    // 대표자가 아닌 신봉자 신앙심 -1
            B[maxBr][maxBc]++; // 대표 신봉자 신앙심 +1
            dfsAndSelecLeader(nr, nc, food);
        }
    }

    // 인접 체크 및 대표자 위치 파악
    private static void dfsAndMaxVal(int r, int c, int food) {
        visited[r][c] = true;

        // 대표자의 좌표와 신앙심 체크하기
        // 신앙심이 가장 큰 경우
        if (maxBelief < B[r][c]) {
            maxBr = r;
            maxBc = c;
            maxBelief = B[r][c];
        }
        // 신앙심이 같은 경우
        else if (maxBelief == B[r][c]) {
            // r이 가장 작은 경우
            if (r < maxBr) {
                maxBr = r;
                maxBc = c;
                maxBelief = B[r][c];
            }
            // r이 같은 경우
            else if (r == maxBr) {
                // c가 가장 작은 경우
                if (c < maxBc) {
                    maxBr = r;
                    maxBc = c;
                    maxBelief = B[r][c];
                }
            }
        }
        // 상하좌우 인접 확인
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // N * N 벗어난 경우
            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
            // 방문한 경우
            if (visited[nr][nc]) continue;
            // 신앙 음식이 다른 경우
            if (food != F[nr][nc]) continue;

            dfsAndMaxVal(nr, nc, food);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        F = new int[N][N];
        // 공백 x
        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < N; j++) {
                char c = str.charAt(j);
                if (c == 'T') { // 민트 4
                    F[i][j] = 4;
                } else if (c == 'C') { // 초코 2
                    F[i][j] = 2;
                } else { // 우유 1
                    F[i][j] = 1;
                }
            }
        }

        B = new int[N][N];
        // 공백 o
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 사용자 입력 끝

        for (int t = 1; t <= T; t++) {
            // *** 1. 아침 시간 ***
            morning();
            // *** 2. 점심 시간 ***
            afternoon();
            // *** 3. 저녁 시간 ***
            evening();

            // 아래순서대로 신봉자들의 신앙심(B) 총합 출력
            // 민트초코우유 -> 민트초코 -> 민트우유 -> 초코우유 -> 우유 -> 초코 -> 민트
            for (int i = 0; i < seq.length; i++) {
                int idx = seq[i];
                bw.write(result[idx] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
/*
개~~ 오래걸렸다. 6시간은 걸린 듯.
접근을 아예 못하는건 아니다. 하지만, 규칙을 찾고 단계별로 차례대로 구현하는 습관이 덜 잡혀있다.

"|=" 연산자도 처음 봤다.
해설을 좀 참조해서 해결한 경향이 있지만 남은 기간 잘해야지.
 */
