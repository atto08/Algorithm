package programmers.lv2;
/*
2회차 풀이
올바른 괄호 - lv2
소요시간 - 5분

풀이설명:
'('은 넣고 ')'은 스택에 '('있는지 확인하고 소거하기. 없으면 브레이크
*/

import java.util.*;

public class Program_12909 {
    public static boolean solution(String s) {
        boolean answer = true;

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    answer = false;
                    break;
                } else {
                    stack.pop();
                }
            }
        }

        if (!stack.isEmpty()) answer = false;
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("()()"));
        System.out.println(solution("(())()"));
        System.out.println(solution(")()("));
        System.out.println(solution("(()("));
    }
}