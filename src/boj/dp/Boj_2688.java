package boj.dp;

import java.io.*;
import java.util.*;

/*
줄어들지 않아 - 실1
1) 틀렸습니다.
==> sum 의 타입과 배열의 타입을 int 로 사용
타입의 범위를 고려해야함
2) 통과
==> int 타입 long로 변경
 */
public class Boj_2688 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            long[] dp = new long[10];
            Arrays.fill(dp, 1);

            if (N > 1) {
                int cnt = 1;
                while (cnt != N) {
                    long[] numbers = new long[10];
                    long sum1 = 0;

                    for (int i = 0; i < 10; i++) {
                        sum1 += dp[i];
                        numbers[i] = dp[i];
                    }

                    dp[0] = sum1;
                    for (int i = 1; i < 10; i++) {
                        dp[i] = dp[i - 1] - numbers[i - 1];
                    }
                    cnt++;
                }
            }

            long sum = 0;
            for (long num : dp) {
                sum += num;
            }
            bw.write(sum + "\n");
        }
        bw.flush();
    }
}
