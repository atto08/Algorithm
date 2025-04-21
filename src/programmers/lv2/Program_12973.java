package programmers.lv2;

import java.util.*;
/*
짝지어 제거하기 - lv2
소요시간 - 6분

풀이설명:
- 문자열의 첫번째 문자를 스택에 넣어놓고 다음 문자와 비교하기
    - 앞에 문자와 현재문자가 같으면 제거 / 다르면 넣기
    - 결과적으로 스택의 크기가 0이면 성공 & 아니면 실패
p.s
스택 활용한 자료구조 대표문제라고 생각들어서 빠르게 풀었다 생각함.
 */
public class Program_12973 {
    public static int solution(String s) {
        int result = 0;
        Stack<Character> stack = new Stack<>();
        stack.add(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.add(s.charAt(i));
            } else {
                if (stack.peek() == s.charAt(i)) {
                    stack.pop();
                } else {
                    stack.add(s.charAt(i));
                }
            }
        }

        if (stack.isEmpty()) result = 1;

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution("baabaa"));
        System.out.println(solution("cdcd"));
    }
}
