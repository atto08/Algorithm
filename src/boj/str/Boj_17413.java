package boj.str;

import java.io.*;

/*
단어 뒤집기2 - 실3
소요 시간 - 1시간

문제 설명:
- 문자열 S가 주어졌을 때, 문자열에서 단어만 뒤집으려 한다. 다만 <>로 감싸진 단어는 뒤집지 않는다.
- 단어는 알파벳 소문자와 숫자로 이뤄진 부분 문자열이고 연속하는 두 단어는 공백하나로 구분
- 태그는 단어가 X, 태그와 단어 사이에는 공백 X

풀이 설명:
- 문자열 S를 char타입의 배열로 구성하여 하나씩 붙이기 - 스택 방식 활용
    - 첫 문자가 '<'인지 체크
        - true  - 끝 문자가 '>'이면 result에 붙이고 sb초기화
        - false - 그냥 sb에 붙이기
    - 첫 문자가 '<'가 아니면
        - 끝 문자가 '<' or " "(공백)인 경우 체크
            - true  - 단어 반전시키고 1번 인덱스 부터 끝까지 추출
                - '<' 경우 sb.append(c);
                - ' ' 경우 result.append(c);
    - 나머지 경우는 sb.append(c); 반복
*/
public class Boj_17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();

        StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for (char c : arr) {
            sb.append(c);

            if (sb.charAt(0) == '<') {
                if (sb.charAt(sb.length() - 1) == '>') {
                    result.append(sb);
                    sb.setLength(0);
                }
            } else {
                if (sb.charAt(sb.length() - 1) == ' ') {
                    result.append(sb.reverse().substring(1)).append(' ');
                    sb.setLength(0);
                } else if (sb.charAt(sb.length() - 1) == '<') {
                    result.append(sb.reverse().substring(1));
                    sb.setLength(0);
                    sb.append(c);
                }
            }

        }
        if (sb.length() > 0) result.append(sb.reverse());

        System.out.println(result);
    }
}
