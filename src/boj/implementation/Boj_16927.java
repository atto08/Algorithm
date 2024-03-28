package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
배열 돌리기2 - 골5
1) 시간 초과
소요시간 : 53분
2) 시간 초과
소요시간 : +37분

배열돌리기1 과 차이점)
1) 1 ≤ R ≤ 1,000
2) 1 ≤ R ≤ 10^9

블로그 참조
Point) R 반복 줄이기 = 테두리 줄이기
 */
public class Boj_16927 {
    static int N, M, R;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int n = N, m = M;
        for (int i = 0; i < Math.min(M, N) / 2; i++) {
            /*
             * i : 회전을 시작할 좌표
             * Point1) 2*N + 2*M - 4 : 처음엔 가장 겉 테두리의 갯수, 그 다음엔 각 변 길이가 2씩 줄도록 해서 넣어줌
             */
            rotate(i, 2 * n + 2 * m - 4);
            n -= 2;
            m -= 2;
        }

        print();
    }

    private static void rotate(int start, int len) {

        // Point2) 나누기 연산을 사용 반복되는 반복 최소화
        int cir = R % len;

        // 새롭게 구해낸 회전 횟수 만큼 회전시킴
        for (int r = 0; r < cir; r++) {

            int temp = map[start][start]; // 마지막에 넣을 값 미리 빼놓음

            int x = start, y = start;

            int idx = 0;
            while (idx < 4) {

                int nx = x + dx[idx];
                int ny = y + dy[idx];

                // 벽이 아니면 계속 회전
                if (nx >= start && ny >= start && nx < N - start && ny < M - start) {
                    map[x][y] = map[nx][ny];
                    x = nx;
                    y = ny;
                }
                // 벽 만나면 방향 전환
                else {
                    idx++;
                }
            }
            map[start + 1][start] = temp;    // 마지막에 미리 빼놨던 값 넣기
        }
    }

    private static void print() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]);
                if (j != M - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
