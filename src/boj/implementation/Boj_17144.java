package boj.implementation;

import java.io.*;
import java.util.*;
/*
미세먼지 안녕! - 골4
소요시간 - 90분

[풀이설명]
- 코드 주석처리

p.s
- 1시간내에 이정도 난이도의 문제를 풀 수 있어야한다고 생각하는데, 쉽지않다.
- 기능을 최대한 쪼개는 것도 좋지만 문제에 따라서 너무 나누려고 하다가 좀 걸리지 않았나싶다.
- 그래도 발전하고 있다고 생각하니까 시간을 단축하는 것과 앞서 깨우친 단계별 접근으로 계속해서 시도하자.
 */
public class Boj_17144 {

    static int R, C, T;
    static int[][] A, A2;
    static int[] dr = {0, -1, 0, 1}, dc = {1, 0, -1, 0};
    static int[][] acPos = new int[2][2];

    static class Dust {
        int r, c, dust;

        public Dust(int r, int c, int dust) {
            this.r = r;
            this.c = c;
            this.dust = dust;
        }
    }

    private static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        A = new int[R][C];
        int airCleaner = 0;
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                A[r][c] = Integer.parseInt(st.nextToken());
                if (A[r][c] == -1) {
                    acPos[airCleaner][0] = r;
                    acPos[airCleaner][1] = c;
                    airCleaner++;
                }
            }
        }
    }

    // 1) 미세먼지 확산
    private static void spreadDust() {
        // 미세먼지 위치 및 양 입력
        Queue<Dust> dustPos = new LinkedList<>();
        A2 = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (A[r][c] > 0) {
                    dustPos.add(new Dust(r, c, A[r][c]));
                }
            }
        }
        // 동시에 미세먼지 확산
        while (!dustPos.isEmpty()) {
            Dust now = dustPos.poll();

            int divA = now.dust / 5; // 확산되는 미세먼지 양
            int cnt = 0;            // 확산된 방향 수
            // 인접4방향 확인
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                // 칸을 벗어나는 경우
                if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                // 공기청정기인 경우
                if (A[nr][nc] == -1) continue;
                cnt++;  // 확산방향 증가
                A2[nr][nc] += divA; // 미세먼지 확산
            }
            A2[now.r][now.c] += now.dust - (divA * cnt);
        }
        // 확산된 미세먼지 덮어쓰기
        A = A2;
        // 공기청정기 위치 기록
        for (int i = 0; i < 2; i++) {
            int r = acPos[i][0], c = acPos[i][1];
            A[r][c] = -1;
        }
    }

    // 2) 공기청정기 작동
    private static void powerOnAirCleanMachine() {
        for (int i = 0; i < 2; i++) {
            int r = acPos[i][0], c = acPos[i][1];
            // 미세먼지 청소(밀기)
            blowDust(i, r, c, 0);
        }
    }

    // ud = 0 -> 0 1 2 3
    // ud = 1 -> 0 3 2 1
    private static void blowDust(int ud, int r, int c, int dir) {

        int nA = A[r][c];
        if (A[r][c] == -1) nA = 0;  // 시작점 = 공기청정기

        int nr = r + dr[dir];
        int nc = c + dc[dir];
        // 넘어가면 방향전환
        if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
            if (dir == 0) {
                if (ud == 0) {
                    dir++;
                } else {
                    dir = 3;
                }
            } else if (dir == 3) {
                if (ud == 0) {
                    dir = 0;
                } else {
                    dir--;
                }
            } else {
                if (ud == 0) {
                    dir++;
                } else {
                    dir--;
                }
            }
            // 바뀐 방향으로 갱신
            nr = r + dr[dir];
            nc = c + dc[dir];
        }
        // 종착지가 공기청정기
        if (A[nr][nc] == -1) return;
        // 다음 칸 확인
        blowDust(ud, nr, nc, dir);
        A[nr][nc] = nA; // 먼지 이동
    }

    // 3) 먼지 총합 구하기
    private static int calculateDustSum() {
        int sum = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (A[r][c] > 0) {
                    sum += A[r][c];
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        // 0) 입력 값
        inputData();
        for (int t = 0; t < T; t++) {
            // 1) 미세먼지 확산
            spreadDust();
            // 2) 공기청정기 작동
            powerOnAirCleanMachine();
        }
        // 3) 미세먼지 총합 계산
        int result = calculateDustSum();
        // 4) 출력
        System.out.println(result);
    }
}