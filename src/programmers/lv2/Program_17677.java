package programmers.lv2;

/*
뉴스 클러스터링 - lv2
소요시간 - 70분

자카드 유사도 -> J(A, B) = 교집합/합집합. A와 B 모두 공집합 경우 J(A, B) = 1
- 소수 버리기
- 대소문자 구분 X
- 입력 문자열 str1, str2를 2글자씩 끊어서 다중집합 원소로 만듦
- 기타 공백 or 숫자, 특수 문자 있는 경우 해당 원소 버림.

- 1) A,B 집합 원소로 만들기
- 2) A,B 원소 비교하며 교집합 합집합 만들기.
- 3) 교집합 / 합집합 한 값 * 65536 => (int)
- a ~ z의 char 숫자형 범위 -> 97 ~ 122

1차 시도 - 통과
- List로 교집합과 합집합을 만들어서 사용했음.
- 근데 내가 작성한 방식을 보니 double 타입으로 교집합 & 합집합 사용 가능해보임
-> 주어진 테스트 케이스에서는 list사용 vs double 사용의 메모리 및 속도차이가 크게 나지 X
-> 하지만 double사용이 str1 & str2의 범위가 더 커진다면 더 효율적일 거라 생각함.

p.s
한시간 넘긴 했는데. 그래도 평소보다 생각많이 하기보다는 우선 구현해본 것에 박수.
하지만, 실제 코테에서는 예시 케이스에 대한 결과만 보여주기 때문에 시간복잡도와 메모리 효율을 생각해야됨.
우선시 해야하는건 맞지만 안될거 같다해서 머뭇거리지만 말자.
계산되면 빠르게 계산 ㄱㄱ & 구현
*/

import java.util.*;

public class Program_17677 {
    public static int solution(String str1, String str2) {
        int answer = 65536;

        HashMap<String, Integer> A = insertMap(str1.toLowerCase());
        HashMap<String, Integer> B = insertMap(str2.toLowerCase());

        if (A.isEmpty() && B.isEmpty()) return answer;

        double min = 0, max = 0;
        for (String ak : A.keySet()) {
            if (B.containsKey(ak)) {
                int lenMin = Math.min(A.get(ak), B.get(ak));
                int lenMax = Math.max(A.get(ak), B.get(ak));
                for (int i = 0; i < lenMax; i++) {
                    if (i < lenMin) min++;
                    max++;
                }
            } else {
                int len = A.get(ak);
                for (int i = 0; i < len; i++) max++;
            }
        }

        for (String bk : B.keySet()) {
            if (!A.containsKey(bk)) {
                for (int i = 0; i < B.get(bk); i++) max++;
            }
        }

        double div = min / max;
        return (int) (div * (double) answer);
    }

    private static HashMap<String, Integer> insertMap(String str) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length() - 1; i++) {
            StringBuilder sb = new StringBuilder().append(str.substring(i, i + 2));

            if (sb.charAt(0) > 122 || sb.charAt(0) < 97 || sb.charAt(1) > 122 || sb.charAt(1) < 97) continue;

            if (!map.containsKey(sb.toString())) {
                map.put(sb.toString(), 1);
            } else {
                map.put(sb.toString(), map.get(sb.toString()) + 1);
            }
        }

        return map;
    }

    public static void main(String[] args) {
        System.out.println(solution("FRANCE", "FRENCH"));
        System.out.println(solution("handshake", "shake hands"));
        System.out.println(solution("aa1+aa2", "AAAA12"));
        System.out.println(solution("E=M*C^2", "e=m*c^2"));
    }
}
