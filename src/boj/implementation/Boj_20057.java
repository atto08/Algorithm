package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
마법사 상어와 토네이도 - 골3
소요 시간: 2시간 초과

원인 >
1) 반복하는 규칙 구현 중 제대로 확인하지 않은상태로 코드 작성
>> 발견한 규칙대로가 아닌 움직임을 보임
2) 남아있는 모래양을 출력함.
>> 문제를 똑바로 읽지 않은게 분명.
3) 토네이도가 간 지점에 모래를 없애지 않음.
>> 값이 크게 나옴

 결론 > 정신을 제대로 차리지 않았다고 생각함
 1. 문제를 제대로 읽고
 2. 간단한 것 부터 구현하도록 하기
 */
public class Boj_20057 {
    static int N, totalSand;
    static int[][] A;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static double[] percent = {0.05, 0.1, 0.1, 0.07, 0.07, 0.02, 0.02, 0.01, 0.01};
    // 5% 10% 10% 7% 7% 2% 2% 1% 1%
    static int[][] spreadSandX = {
            {0, -1, 1, -1, 1, -2, 2, -1, 1},
            {2, 1, 1, 0, 0, 0, 0, -1, -1},
            {0, -1, 1, -1, 1, -2, 2, -1, 1},
            {-2, -1, -1, 0, 0, 0, 0, 1, 1}};
    static int[][] spreadSandY = {
            {-2, -1, -1, 0, 0, 0, 0, 1, 1},
            {0, 1, -1, -1, 1, -2, 2, -1, 1},
            {2, 1, 1, 0, 0, 0, 0, -1, -1},
            {0, -1, 1, -1, 1, -2, 2, -1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1][N + 1];

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                if (A[i][j] > 0) {
                    totalSand += A[i][j];
                }
            }
        }

        int dir = 0, cnt = 1;
        int r = N / 2 + 1, c = N / 2 + 1;
        boolean trigger = false;
        while (!trigger) {

            for (int i = 0; i < cnt; i++) {
                r = r + dx[dir];
                c = c + dy[dir];

                // 모래 흩뿌리기
                // a알파
                int sand = A[r][c];
                A[r][c] = 0;
                int sum = 0;
                for (int j = 0; j < 9; j++) {
                    int nr = r + spreadSandX[dir][j];
                    int nc = c + spreadSandY[dir][j];
                    if (nr > 0 && nc > 0 && nr <= N && nc <= N) {
                        A[nr][nc] += sand * percent[j];
                    }
                    sum += sand * percent[j];
                }
                int nx = r + dx[dir];
                int ny = c + dy[dir];
                if (nx > 0 && ny > 0 && nx <= N && ny <= N) {
                    A[nx][ny] += sand - sum;
                }

                // 모래도 다 날리고 도착지 도착했으면 종료
                if (r == 1 & c == 1) {
                    trigger = true;
                    break;
                }
            }
            // 방향 전환 & 가는 이동 횟수
            if (dir == 0) {
                dir++;
            } else if (dir == 1) {
                dir++;
                cnt++;
            } else if (dir == 2) {
                dir++;
            } else {
                dir = 0;
                cnt++;
            }
        }

        int insideSand = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                insideSand += A[i][j];
            }
        }

        System.out.println(totalSand - insideSand);
    }
}
