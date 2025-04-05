package programmers.lv1;

import java.util.HashMap;
/*
숫자 문자열과 영단어 - lv1
소요 시간 - 30분

풀이 설명:
- 숫자인지 체크해보기
  1) 숫자O -> 그대로 StringBuilder에 넣어
  2) 숫자X -> 숫자 나올때 까지 or 끝까지 갈때까지 기다렸다가 해당 단어만들고 해당 단어에 맞는 숫자 붙이기
 */
public class Program_81301 {
    static String[] numbers = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    public static int solution(String s) {

        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < numbers.length; i++) map.put(numbers[i], i);

        StringBuilder sb = new StringBuilder();
        StringBuilder word = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            int now = s.charAt(i);

            if(now < 58) {
                sb.append(now - 48);
            } else {
                word.append(s.charAt(i));
                if(map.get(word.toString()) != null) {
                    sb.append(map.get(word.toString()));
                    word.delete(0, word.length());
                }
            }
        }

        return Integer.parseInt(sb.toString());
    }

    public static void main(String[] args) {
        System.out.println(solution("one4seveneight"));
        System.out.println(solution("23four5six7"));
        System.out.println(solution("2three45sixseven"));
        System.out.println(solution("123"));
    }
}
