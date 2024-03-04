package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
1) 문자열의 길이가 증가하는 방식 (S -> T 를 찾는)
==> 메모리 초과
2) 문자열의 길이가 감소하는 방식 (T -> S 를 찾는)
==> 통과
 */
public class Boj_12904 {
    static String S, T;
    static int result;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        bfs();

        System.out.println(result);
    }

    private static void bfs() {
        Queue<String> q = new LinkedList<>();
        q.offer(T);

        while (true) {
            String str = q.poll();

            if (str.length() <= S.length())
                break;

            String answer;
            if (str.charAt(str.length() - 1) == 'A') {
                answer = rmA(str);
            } else if (str.charAt(str.length() - 1) == 'B') {
                answer = rmBReverse(str);
            } else {
                break;
            }

            if (answer.equals(S)) {
                result = 1;
                break;
            }
            q.offer(answer);
        }
    }

    private static String rmA(String str) {
        return str.substring(0, str.length() - 1);
    }

    private static String rmBReverse(String str) {
        StringBuilder sb = new StringBuilder(str.substring(0, str.length() - 1));
        return sb.reverse().toString();
    }
}
