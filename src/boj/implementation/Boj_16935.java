package boj.implementation;

import java.io.*;
import java.util.StringTokenizer;

/*
배열 돌리기3 - 골5
소요 시간: 80분 초과
연산 4번까지는 구현했으나 3~4번 구현에 시간을 거의 빼앗김

소요 시간: +70분
5~6번은 시간 초과 이 후
넣을 곳과 넣어야 할 수가 있는 번호칸의 인덱스 값을 맞춰주는 구조를 잡는데 시간을 잡아먹음
2차원 배열에 대해서 자세하게 공부할 수 있는 문제였다.
 */
public class Boj_16935 {
    static int N, M, R;
    static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) { // R번 연산 수행
            int operator = Integer.parseInt(st.nextToken());
            rotate(operator);
        }

        for (int i = 0; i < N; i++) { // A 출력
            for (int j = 0; j < M; j++) {
                bw.write(A[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        br.close();
    }

    private static void rotate(int operator) {

        if (operator == 1) { // 1번
            int num = N - 1;
            for (int i = 0; i < N / 2; i++) {
                int[] arr = new int[M]; // i번째 행
                for (int j = 0; j < M; j++) {
                    arr[j] = A[i][j];
                    A[i][j] = A[num][j];
                }

                for (int j = 0; j < M; j++) {
                    A[num][j] = arr[j];
                }
                num--;
            }
        } else if (operator == 2) { // 2번
            int num = M - 1;
            for (int j = 0; j < M / 2; j++) {
                int[] arr = new int[N];
                for (int i = 0; i < N; i++) {
                    arr[i] = A[i][j];
                    A[i][j] = A[i][num];
                }

                for (int i = 0; i < N; i++) {
                    A[i][num] = arr[i];
                }
                num--;
            }
        } else if (operator == 3) { // 3번
            int m = N;
            int[][] A2 = new int[M][N];
            for (int i = 0; i < M; i++) {
                int y = 0;
                for (int j = N - 1; j >= 0; j--) {
                    A2[i][y] = A[j][i];
                    y++;
                }
            }
            A = A2;
            N = M;
            M = m;
        } else if (operator == 4) { // 4번
            int m = N;
            int[][] A2 = new int[M][N];
            int x = 0;
            for (int i = M - 1; i >= 0; i--) {
                for (int j = 0; j < N; j++) {
                    A2[x][j] = A[j][i];
                }
                x++;
            }
            A = A2;
            N = M;
            M = m;
        }
        // 5번 & 6번
        else {
            int[] sx = {0, 0, N / 2, N / 2}, sy = {0, M / 2, M / 2, 0}; // 넣을 값 인덱스 배열
            // 1번 배열
            int[][] arr = new int[N / 2][M / 2];
            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < M / 2; j++) {
                    arr[i][j] = A[i][j];
                }
            }

            int[] tx = {0, N / 2, 0, -N / 2}, ty = {M / 2, 0, -M / 2, 0}; // 넣어야 할 곳 인덱스 배열
            if (operator == 5) { // 5번
                for (int r = 3; r > 0; r--) { //4 -> 1 & 3 -> 4 & 2 -> 3
                    for (int i = sx[r]; i < N / 2 + sx[r]; i++) {
                        for (int j = sy[r]; j < M / 2 + sy[r]; j++) {
                            A[i + tx[r]][j + ty[r]] = A[i][j];
                        }
                    }
                }

                for (int i = sx[0]; i < N / 2 + sx[0]; i++) {// arr -> 2
                    for (int j = sy[0]; j < M / 2 + sy[0]; j++) {
                        A[i + tx[0]][j + ty[0]] = arr[i][j];
                    }
                }

            } else { // 6번
                for (int r = 1; r <= 3; r++) { //2 -> 1 & 3 -> 2 & 4 -> 3
                    for (int i = sx[r]; i < N / 2 + sx[r]; i++) {
                        for (int j = sy[r]; j < M / 2 + sy[r]; j++) {
                            A[i - tx[r - 1]][j - ty[r - 1]] = A[i][j];
                        }
                    }
                }

                for (int i = 0; i < N / 2; i++) { //arr -> 4
                    for (int j = 0; j < M / 2; j++) {
                        A[i - tx[3]][j - ty[3]] = arr[i][j];
                    }
                }
            }
        }
    }
}
