package programmers.lv2;

/*
스킬트리 - lv2
소요시간 - 49분

풀이설명:
- 가능한 스킬트리 갯수 리턴

스킬 순서대로 순서 매기기. - 해시맵으로 구현
ex) c=0, b=1, d=2

skill_trees의 skill별 순회
- 해시맵에 없는 스킬은 무시
- 있는 스킬은 선행스킬 습득여부 확인
    - 즉, 길이 3의 스킬트리가 존재하면 0~2 확인하면서 현재 스킬 레벨과 맞는지 체크
    - 안맞으면 선행스킬 습득X -> 불가능한 스킬트리.

*/

import java.util.*;

public class Program_49993 {
    public static int solution(String skill, String[] skill_trees) {

        HashMap<Character, Integer> st = new LinkedHashMap<>();
        for (int n = 0; n < skill.length(); n++) {
            st.put(skill.charAt(n), n);
        }

        int answer = 0;
        for (String sk : skill_trees) {

            int sp = 0;
            boolean cant = false;
            for (char s : sk.toCharArray()) {
                if (!st.containsKey(s)) continue;    // 등록된 스킬이 아니면 무시

                if (sp != st.get(s)) {  // 현재배워야할 스킬이 아니면 종료
                    cant = true;
                    break;
                }
                sp++;
            }

            if (!cant) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("CBD", new String[]{"BACDE", "CBADF", "AECB", "BDA"}));
    }
}