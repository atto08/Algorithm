package boj.implementation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
수 이어 쓰기 - 실3
소요 시간: 13분

규칙 찾는데 시간소요
큐를 이용해 구현문제 해결
 */
public class Boj_1515 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Character> q = new LinkedList<>();

        String str = sc.next();
        for (int i = 0; i < str.length(); i++) q.offer(str.charAt(i));

        int result = 1;
        while (!q.isEmpty()) {
            String len = String.valueOf(result);
            for (int i = 0; i < len.length(); i++) {

                if (q.isEmpty()) break;

                if (len.charAt(i) == q.peek()) {
                    q.poll();
                }
            }
            result++;
        }
        System.out.println(result - 1);
    }
}
