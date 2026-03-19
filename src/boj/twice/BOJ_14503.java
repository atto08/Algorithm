package boj.twice;

import java.io.*;
import java.util.*;

/*
로봇 청소기 - 골5
소요시간 - 70분

[문제설명]
- N * M 크기의 방이 주어짐. 0은 빈칸, 1은 벽.
- 로봇청소기는 진행방향이 존재.
- 로봇 청소기가 청소할 수 있는 칸의 갯수를 구하기

로봇청소기 움직임
1) 현재칸 청소여부 확인 및 청소
2) 주변 4칸 확인
    2-1) 청소 필요X
        - 진행방향 기준 후진 가능여부 판단
            - 후진가능 시 이동
            - 불가능 시 STOP
    2-2) 청소 필요O
        - 청소기 진행방향 반시계 90도 회전
        - 앞칸이 청소 안된 칸이면 해당 칸으로 이동

[풀이설명]
- 로봇 청소기의 움직임이라고 적은 내용을 주석으로 작성 후 그대로 작성함.

p.s
- 문제를 있는 그대로 코드로 옮긴 경험이라고 생각한다.
- 근데 Spot이라는 클래스를 두고 로봇청소기 클래스 내부에서 메서드를 만들어 사용해보려했는데 익숙치 않아서 실패함.
- 원인을 파악하고 다시 시도해보자. 화이팅.
 */
public class BOJ_14503 {
    static int N, M, result, R, C, D;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static boolean[][] isClean;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 첫번째 방의 크기 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 두번째 로봇 청소기 위치 및 방향 입력
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // 세번째 방의 먼지 정보 출력
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isClean = new boolean[N][M];
        result = 0;

        boolean isOver = false;
        while (!isOver) {
            // 현재 칸 청소여부 확인
            cleaningRoom();

            // 주변 4칸확인
            isOver = checkAround4();
        }

        System.out.println(result);
    }

    // 주변 4칸 확인
    private static boolean checkAround4() {

        for (int i = 0; i < 4; i++) {
            int nr = R + dr[i];
            int nc = C + dc[i];

            // 범위벗어나면 무시
            if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                continue;
            // 벽이거나 청소된 칸은 무시
            if (map[nr][nc] == 1 || isClean[nr][nc])
                continue;
            // 청소 O
            // 진행방향 반시계 90도 회전
            turnDirection();
            // 진행방향 기준 앞 칸이 청소X 칸이면 한칸 전진
            checkFront();
            return false;
        }

        // 청소 X
        // 진행방향 기준 후진 가능하면 후진 후 청소여부 확인 or 로봇청소기 STOP
        return checkBack();
    }

    private static boolean checkBack() {
        int nr = R - dr[D];
        int nc = C - dc[D];

        // 벽이 아니면 후진
        if (map[nr][nc] != 1) {
            R = nr;
            C = nc;
            return false;
        }
        // 벽이면 stop
        return true;
    }

    private static void checkFront() {
        int nr = R + dr[D];
        int nc = C + dc[D];

        // 빈칸이고 청소한적 없으면 이동
        if (map[nr][nc] == 0 && !isClean[nr][nc]) {
            R = nr;
            C = nc;
        }
    }

    private static void turnDirection() {
        if (D > 0) {
            D--;
        } else {
            D = 3;
        }
    }

    private static void cleaningRoom() {

        if (!isClean[R][C]) {
            isClean[R][C] = true;
            result++;
        }
    }
}
