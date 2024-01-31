package boj.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_15965 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        // 500,000만 번째 소수를 구해야 하기때문에 배열의 크기가 훨씬 커야됨.
        boolean[] arr = new boolean[100000001];
        for (int i = 2; i < arr.length; i++) {
            if (!arr[i]) {
                for (int j = 2; i * j < arr.length; j++) {
                    arr[i * j] = true;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < arr.length; i++) {
            if (!arr[i]) {
                count++;
                if (count == K) {
                    System.out.println(i);
                    break;
                }
            }
        }

        br.close();
    }
}
