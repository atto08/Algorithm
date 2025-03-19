package boj.implementation;

import java.util.Scanner;
/*
수 이어 쓰기 1 - 실4
소요 시간 - 50분

풀이 설명:
- 자리수 기준으로 1, 2, 3 ... 갯수가 몇개인지 세어놓고 더했음.
p.s
요즘 집중을 못한다 생각함.
정신 차려라.
이따구로 취업 못한다.
*/
public class Boj_1748 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = String.valueOf(N).length();
        int[] arr = new int[L + 1];
        int sum = 0;
        int result = 0;
        for (int i = 1; i <= L; i++) {
            if (i == L) {
                arr[i] = N - sum;
            } else {
                arr[i] = 9 * (int) Math.pow(10, i - 1);
                sum += arr[i];
            }
            result += arr[i] * i;
        }
        System.out.println(result);
    }
}
