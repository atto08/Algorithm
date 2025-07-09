package programmers.lv1;

/*
가장 가까운 같은 글자 - lv1
소요시간 - 6분

풀이설명:
- HashMap에 최근 문자의 인덱스 넣어서 사용.
- 없으면 -1, 있으면 현재인덱스 - 이전 인덱스 값 입력

p.s
자기전에 한문제 쉽게 풀어서 기분좋다!.
액땜 했나보다. 하하.
*/

import java.util.*;

public class Program_142086 {
    public static int[] solution(String s) {
        int[] result = new int[s.length()];

        HashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, i);
                result[i] = -1;
            } else {
                result[i] = i - map.get(c);
                map.put(c, i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("banana")));
        System.out.println(Arrays.toString(solution("foobar")));
    }
}
