package boj.implementation;

import java.util.Scanner;
/*
돌 게임 - 실5
소요 시간: 10분

원인: 간단한 문제였지만, 트릭이나 규칙이 있는지 의심했음
해결: 3보다 크면 -3을 하고 작으면 -1을 해도 결과는 변하지 않는걸 알게됨
 */
public class Boj_9655 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        boolean who = false;
        while (N != 0) {

            if (N >= 3) {
                N -= 3;
            } else {
                N -= 1;
            }
            who = !who;
        }

        if(who){
            System.out.println("SK");
        } else{
            System.out.println("CY");
        }
    }
}
