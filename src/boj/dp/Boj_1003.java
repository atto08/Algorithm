package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 블로그 참고
public class Boj_1003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            if (N < 2) {
                if (N == 1) {
                    System.out.println(0 + " " + 1);
                } else {
                    System.out.println(1 + " " + 0);
                }
            } else {
                int[] arr = new int[N + 1];
                arr[1] = 1;

                for (int i = 2; i < arr.length; i++) {
                    arr[i] = arr[i - 1] + arr[i - 2];
                }

                System.out.println(arr[N - 1] + " " + arr[N]);
            }
        }
        br.close();
    }
}
