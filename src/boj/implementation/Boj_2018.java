package boj.implementation;

import java.io.*;
/*
수들의 합 5 - 실5
소요 시간 - 30분

투 포인터 개념 복습 및 구현
 */
public class Boj_2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int si = 1, ei = 1, sum = 1, result = 0;

        while (si <= N) {
            if (sum == N) result++;

            if (sum >= N) {
                sum -= si++;
            } else {
                sum += ++ei;
            }
        }

        System.out.println(result);
    }
}
