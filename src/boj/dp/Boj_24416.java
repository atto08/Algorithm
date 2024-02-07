package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_24416 {
    static int N, result1, result2;
    static int[] f;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        f = new int[N + 1];

        result1 = fibonacci(N);
        System.out.println(result1 + " " + result2);

    }

    public static int fibonacci(int n) {
        f[1] = f[2] = 1;
        for (int i = 3; i <= N; i++) {
            f[i] = f[i - 1] + f[i - 2];
            result2++;
        }
        return f[n];
    }
}
