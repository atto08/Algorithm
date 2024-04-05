package boj.implementation;

import java.math.BigInteger;
import java.util.Scanner;

/*
1) 메모리 초과
소요 시간: 75분
원인: 배열의 크기가 너무 커짐

자료구조를 배열에서 큐로 바꿔서 시도
이건 이진법으로 보면된다. 4랑 7을 0과 1로 빗대고 보면 된다..!
다음날 20분 동안 생각하다 보였다
K의 크기중 10의 7제곱~ 9제곱은 format함수로 치환하기에 너무 큰 숫자였음
그래서 BigInteger를 사용해서 해결
소요시간: + 60분
 */
public class Boj_2877 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();

        int cnt = 1;
        int N = K;
        while (N > 0) {
            N -= (int) Math.pow(2, cnt);
            cnt++;
        }

        String binaryStr = String.format("%0" + (cnt - 1) + "d", new BigInteger(Integer.toBinaryString(Math.abs(N))));
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < binaryStr.length(); i++) {
            if (binaryStr.charAt(i) == '0') {
                result.append('7');
            } else {
                result.append('4');
            }
        }

        System.out.println(result.toString());
    }
}
