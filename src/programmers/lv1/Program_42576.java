package programmers.lv1;

import java.util.*;

/*
완주하지 못한 선수 - lv1
소요시간 - 10분 이내

p.s
 - 해시맵과 정렬 2가지 방식으로 접근하여 성능을 비교해 봤습니다.
 - 테스트 결과, 정렬 방식이 해시 방식보다 약 4~5배 더 느린 것을 확인했습니다.
 - 원인은 시간 복잡도의 차이(O(n) vs O(n log n))에 있습니다.
 - 특히 정렬 과정에서 발생하는 수많은 문자열 비교(compareTo)와 요소 이동 비용이 대량의 데이터 처리 시 성능 저하의 주된 요인임을 파악했습니다.
 */
public class Program_42576 {

    public static void main(String[] args) {
        String[] p1 = {"leo", "kiki", "eden"},
                p2 = {"marina", "josipa", "nikola", "vinko", "filipa"},
                p3 = {"mislav", "stanko", "mislav", "ana"};
        String[] c1 = {"kiki", "eden"},
                c2 = {"marina", "josipa", "nikola", "filipa"},
                c3 = {"mislav", "stanko", "ana"};

        System.out.println(solution1(p1, c1));
        System.out.println(solution1(p2, c2));
        System.out.println(solution1(p3, c3));
    }

    // 해시 맵 사용
    public static String solution1(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String p : participant) {
            // if-else를 통한 key 존재 여부 체크 로직을 한 줄로 대체 가능
            // [대체 가능 코드] map.put(p, map.getOrDefault(p, 0) + 1);
            if (!map.containsKey(p)) {
                map.put(p, 1);
            } else {
                map.put(p, map.get(p) + 1);
            }
        }

        for (String c : completion) {
            int cnt = map.get(c);
            if (cnt == 1) {
                map.remove(c);
            } else {
                map.put(c, cnt - 1);
            }
        }

        // 결과가 하나라는 확신이 있다면 iterator()를 사용하는 것이 더 직관적
        // [대체 가능 코드] answer = map.keySet().iterator().next();
        String answer = "";
        for (String key : map.keySet()) {
            answer = key;
        }

        return answer;
    }

    // 정렬 + 배열 사용
    public static String solution2(String[] participant, String[] completion) {
        String answer = "";
        Arrays.sort(participant);
        Arrays.sort(completion);

        for (int i = 0; i < completion.length; i++) {
            if (!participant[i].equals(completion[i])) {
                answer = participant[i];
                break;
            }
        }
        if (answer.isEmpty()) {
            answer = participant[completion.length];
        }
        return answer;
    }
}