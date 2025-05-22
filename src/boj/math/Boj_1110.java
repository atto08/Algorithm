package boj.math;

import java.util.Scanner;
/*
더하기 사이클 - 브1
소요시간 - 8분
 */
public class Boj_1110 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        if(N < 10) sb.append(0);
        sb.append(N);

        int cnt = 0;
        while(true) {

            int num = Integer.parseInt(sb.toString());
            int sum = (num / 10) + (num % 10);

            int fn = 0;
            int ln = 0;
            if(num % 10 > 0) fn = num % 10;
            if(sum % 10 > 0) ln = sum % 10;
            sb = new StringBuilder().append(fn).append(ln);
            cnt++;
            if(Integer.parseInt(sb.toString()) == N) break;
        }

        System.out.println(cnt);
    }
}