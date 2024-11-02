package boj.implementation;

import java.util.Scanner;
/*
3의 배수 - 실5
소요시간: 30분

1) 틀렸습니다 (91%)
원인: N이 한자리 수 인 경우를 생각안함.

 */
public class Boj_1769 {
    static int cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] numbers = sc.next().toCharArray();

        int len = numbers.length;

        if (len == 1) {
            int number = numbers[0] - '0';
            printResult(number);
        }
        while (len > 1) {

            cnt++;
            int number = 0;
            for (int i = 0; i < len; i++) number += numbers[i] - '0';

            if (number < 10) {
                printResult(number);
                break;
            }

            String num = String.valueOf(number);
            len = num.length();
            numbers = num.toCharArray();
        }
    }

    private static boolean isThird(int number) {
        return number % 3 == 0;
    }

    private static void printResult(int number) {
        System.out.println(cnt);
        if (isThird(number)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
