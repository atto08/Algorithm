package boj.math;

import java.io.*;
import java.util.StringTokenizer;

public class Boj_2740 {
    static StringTokenizer st;
    static int N, M, K;
    static int[][] A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        B = new int[M][K];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < K; k++) {
                bw.write(method(i, k) + " ");
            }
            bw.write("\n");
        }

        bw.flush();
    }

    public static int method(int x, int y) {
        int sum = 0;

        for (int m = 0; m < M; m++)
            sum += A[x][m] * B[m][y];

        return sum;
    }
}
