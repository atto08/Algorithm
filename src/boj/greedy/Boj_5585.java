package boj.greedy;

import java.util.Scanner;

public class Boj_5585 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int money = sc.nextInt();
        int result = 0;
        int payBack = 1000 - money;
        int[] cash = {500, 100, 50, 10, 5, 1};
        for (int i = 0; i < 6; i++) {

            if (payBack == 0) break;

            if (cash[i] > payBack) continue;

            result += payBack / cash[i];
            payBack %= cash[i];
        }

        System.out.println(result);
    }
}
