package programmers.lv2;
/*
괄호 회전하기 - lv2
소요시간 - 44분

풀이설명:
- 0 <= x <= s.length() 만큼 회전한 문자열 중 올바른 괄호 문자열갯수는?
- 스택에 넣으면서 확인하기
    1) '{', '[', '(' 왼쪽이면 스택에 넣기.
    2) '}', ']', ')' 오른쪽이면 스택에 있는지 체크.
        - 서로 다른괄호 or 오른쪽 괄호부터 시작하는 경우 -> 올바르지 않은 괄호

p.s
- 나 괄호문제 이해 진짜 못했었다.
- 하지만 오늘 나 깨달아버렸다.
- 덕분에 수월하게 풀었고 스타트가 좋다. 하하
*/

import java.util.Stack;

public class Program_76502 {
    static int result;

    public static int solution(String s) {
        result = 0;
        StringBuilder sb = new StringBuilder().append(s);
        for (int i = 0; i < s.length(); i++) {
            isCorrect(sb.toString());                   // 올바른 괄호여부 체크
            sb.append(sb.charAt(0)).deleteCharAt(0);    // 1칸 회전
        }

        return result;
    }

    private static boolean isLeft(char c) {
        if (c == ']' || c == ')' || c == '}') return false;
        return true;
    }

    private static void isCorrect(String s) {
        // 아래 단계로 확인하기
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            // 1) 스택에 쌓기
            if (isLeft(c)) {
                stack.add(c);
                continue;
            }
            // 2) 스택에 있는지 체크하고 같으면 제거. &
            if (stack.isEmpty()) {   // 오른쪽 괄호부터 존재 -> 올바르지 않은 괄호
                stack.add(c);
                break;
            } else {
                // 같은 괄호가 나옴 -> 올바른 괄호
                if (((c == ']') && (stack.peek() == '[')) ||
                        ((c == ')') && (stack.peek() == '(')) ||
                        ((c == '}') && (stack.peek() == '{'))) {
                    stack.pop();
                } else {    // 서로 다른 괄호 -> 올바르지 않은 괄호
                    stack.add(c);
                    break;
                }
            }
        }

        if (stack.isEmpty()) result++;
    }

    public static void main(String[] args) {
        System.out.println(solution("[](){}"));
        System.out.println(solution("}]()[{"));
        System.out.println(solution("[)(]"));
    }
}
