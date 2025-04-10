package programmers.lv2;
/*
캐시 - lv2
소요시간 - 1시간 초과

문제 설명:
- DB 캐시를 적용할 때, 캐시 크기에 따른 실행시간 측정프로그램을 구현하기
- 0 <= cacheSize <= 30, cities[] <= 100,000
- 각 도시 이름은 공백, 숫자, 특수문자 없는 영문자로만 구성(대소문자 구분X) & 최대 20자

풀이 설명:
1차시도 - 실패(11, 15, 16, 18 ~ 20)
- 1) HashMap -> 존재 여부 containsKey로 체크.
- 2) 우선순위 큐 -> 들어온 순서(인덱스) 체크

2차시도 - 통과
- HashMap으로 존재 여부 체크 & 들어온 순서 체크(반복문으로 가장 먼저 들어온 요소(인덱스) 구하기)

p.s
- 1문제에 1시간 넘게 투자 금지! & 과도한 자료구조 혼용 사용 금지. 아직 부족하다.
- LinkedHashMap
    - 내부적으로 이중 연결 리스트를 사용함
        -> 입력 순서 혹은 접근 순서 유지 가능
        -> LRU 캐시 쉽게 구현 가능
- 즉 -> 반복문 사용 필요 X
*/

import java.util.HashMap;

public class Program_17680 {
    public static int solution(int cacheSize, String[] cities) {
        int result = 0;
        if (cacheSize == 0) {
            return cities.length * 5;
        }

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            if (!map.containsKey(city)) { // 캐시 존재 X
                result += 5; // 5초 추가
                if (map.size() >= cacheSize) { // 현재 캐시가 꽉찬 경우
                    int first = 100001;
                    String target = "";
                    for (String key : map.keySet()) {
                        if (first > map.get(key)) {
                            first = map.get(key);
                            target = key;
                        }
                    }
                    map.remove(target);
                }
                map.put(city, i); // 새로운 캐시 데이터 추가
            } else { // 캐시 존재 O
                result += 1; // 1초 추가
                map.replace(city, i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
        System.out.println(solution(2, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
        System.out.println(solution(5, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
        System.out.println(solution(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}));
        System.out.println(solution(0, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
        System.out.println(solution(3, new String[]{"a", "b", "a", "c", "d", "a"}));
    }
}
