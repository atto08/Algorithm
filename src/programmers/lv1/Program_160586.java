package programmers.lv1;
/*
대충 만든 자판 - lv1
소요시간 - 20분

풀이설명:
- keymap[] 만큼 돌면서 문자별로 가장빠른 인덱스를 배열에 갖고있기
ex)
A B C D / A A B B
1 1 2 5 / 1 1 1 1

A S A / B G Z
1 2 1 / 1 2 3
*/

import java.util.Arrays;

public class Program_160586 {
    public static int[] solution(String[] keymap, String[] targets) {

        int[] keys = new int[26];
        Arrays.fill(keys, Integer.MAX_VALUE);

        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                int idx = key.charAt(i) - 65;
                if (keys[idx] > i + 1) {
                    keys[idx] = i + 1;
                }
            }
        }

        int[] result = new int[targets.length];
        for (int i = 0; i < targets.length; i++) {
            for (char c : targets[i].toCharArray()) {
                int idx = c - 65;
                if (keys[idx] == Integer.MAX_VALUE) {
                    result[i] = -1;
                    break;
                } else {
                    result[i] += keys[idx];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"ABACD", "BCEFD"}, new String[]{"ABCD", "AABB"})));
        System.out.println(Arrays.toString(solution(new String[]{"AA"}, new String[]{"B"})));
        System.out.println(Arrays.toString(solution(new String[]{"AGZ", "BSSS"}, new String[]{"ASA", "BGZ"})));
    }
}