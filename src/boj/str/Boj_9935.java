package boj.str;

import java.util.Scanner;

/*
문자열 폭발 - 골4
소요 시간 - 50분

문제 설명:
- 문자열에 폭발 문자열이 존재하면 폭발 문자열이 폭발하여 해당 문자는 문자에서 사라지고 남은 문자열이 합쳐짐
- 이 과정이 반복되어 폭발 문자열이 전부 폭발했을때 남은 문자열을 리턴
- 남은 문자가 없을 경우 "FRULA" 리턴
- 1 <= 문자열 길이 <= 1,000,000  /  1 <= 폭발 문자열 <= 36
- 두 문자열 모두 알파벳 소문자와 대문자, 숫자 0, 1 ~ 9 로 이뤄짐

풀이 설명:
1차 시도 - 46% 메모리 초과
- 원인: 문자열의 불변성 때문에 메모리 초과 발생
        - Java의 String은 불변 객체
        - 즉, replace() 메서드를 호출할 때 마다 새로운 문자열을 생성함.
            - 다음 문자열(nxStr)이 기존 문자열(str)과 다르면 계속 갱신 -> 불필요한 문자열 객체가 계속 생성
            - -> 새로운 문자열 생성으로 인한 메모리 초과 발생

2차 시도 - 통과
- 해결 방법: 가변 문자열(StringBuilder) 사용
- StringBuilder를 스택처럼 사용해서 문자를 하나씩 추가
- 폭발 문자열 체크 - 스택 처럼 사용
    - sb.substring(sb.length() - 폭발 문자열의 길이) == 현재 문자에서 우측에 폭발 문자열 크기만큼
 */
public class Boj_9935 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String bomb = sc.next();

        StringBuilder sb = new StringBuilder();
        int bombLen = bomb.length();

        for (char c : str.toCharArray()) {
            sb.append(c);

            // 폭발 문자열과 일치하는 부분이 있는지 확인
            if (sb.length() >= bombLen && sb.substring(sb.length() - bombLen).equals(bomb)) {
                sb.setLength(sb.length() - bombLen); // 폭발 문자열 삭제
            }
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb);
    }
}