package boj.implementation;

import java.util.Arrays;
import java.util.Scanner;
/*
방 번호 - 실5
소요 시간: 35분
 */
public class Boj_1475 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] arr = new int[9];
        String str = String.valueOf(N);
        for (int i = 0; i < str.length(); i++) {
            int num = Integer.parseInt(str.substring(i, i + 1));
            if (num == 9) {
                arr[6]++;
            } else {
                arr[num]++;
            }
        }

        if (arr[6] % 2 != 0) {
            arr[6] = arr[6] / 2 + 1;
        } else {
            arr[6] /= 2;
        }
        Arrays.sort(arr);
        System.out.println(arr[8]);
    }
}
