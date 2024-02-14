package boj.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_11050 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int result = 1;
        for (int i = N; i > 0; i--) {
            if (i > K)
                result *= i;
        }

        for (int i = N - K; i > 0; i--) {
            result /= i;
        }

        System.out.println(result);
    }
}
