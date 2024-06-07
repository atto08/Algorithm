package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
동전 0 - 실4
소요 시간: 12분

그리디 알고리즘 첫 풀이인줄 알았는데 dp라고 생각한 문제들 중에서도 greedy 문제가 꽤나 존재했음.
 */
public class Boj_11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] dp = new boolean[K + 1];
        for (int i = 0; i < N; i++) {
            int idx = Integer.parseInt(br.readLine());

            try {
                dp[idx] = true;
            } catch (ArrayIndexOutOfBoundsException ignore) {}
        }

        int min = 0, k = K;
        for (int i = K; i > 0; i--) {
            if (k == 0) {
                break;
            }

            if (dp[i]) {
                int x = k / i; // ㅂㅐ수
                min += x;
                k = k % i;
            }
        }
        System.out.println(min);
    }
}
