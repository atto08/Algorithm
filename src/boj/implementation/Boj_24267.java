package boj.implementation;

import java.util.Scanner;

/*
시간 복잡도 공부 -
알고리즘 수업 - 알고리즘의 수행 시간 6 - 브2

규칙적으로 콤비네이션(조합)을 사용하면 금방 풀릴문제.
>> 이를 생각해내는 과정이 어려움 >> 블로그 참고

연산에 있어서 반복문이 끼치는 시간적인 영향을 조금이나마 이해한거같음. + 좀 더 깊은 공부 필요.
 */
public class Boj_24267 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextInt();

        System.out.println(N * (N - 1) * (N - 2) / 6 + "\n" + 3);
    }
}