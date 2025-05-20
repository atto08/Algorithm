package swea.d4;

import java.util.*;
/*
괄호 짝짓기 - D4
소요시간 - 10분

swea 환경 적응차 풀이 진행
 */
public class Swea_1218 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        for (int test_case = 1; test_case <= 10; test_case++) {
            int N = sc.nextInt();
            String str = sc.next();
            Stack<Character> stack = new Stack<>();
            boolean isCorrect = true;
            for (char c : str.toCharArray()) {
                if (!isLeft(c)) {
                    if (stack.isEmpty()) {
                        isCorrect = false;
                        break;
                    } else {
                        if (stack.peek() == cvtDir(c)) {
                            stack.pop();
                        } else {
                            isCorrect = false;
                            break;
                        }
                    }
                } else {
                    stack.add(c);
                }
            }
            int result;
            if (!isCorrect) {
                result = 0;
            } else {
                result = 1;
            }
            System.out.println("#" + test_case + " " + result);
        }
    }

    private static boolean isLeft(char c) {
        return c == '<' || c == '(' || c == '[' || c == '{';
    }

    private static char cvtDir(char c) {
        if (c == ']') {
            return '[';
        } else if (c == '}') {
            return '{';
        } else if (c == ')') {
            return '(';
        }
        return '<';
    }
}