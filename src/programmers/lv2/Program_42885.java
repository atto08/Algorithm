package programmers.lv2;

import java.util.*;
/*
구명 보트 - lv2
소요 시간 - 46분

문제 설명:
- 구명보트는 최대 2인까지 탑승 가능 & 제한 무게(limit) 존재
- 무인도에 갇힌 사람들을 전부 구출하는데 필요한 최소 구명보트 수를 구하시오
- 1 <= people[].length <= 50,000 && 40 <= people[i], limit <= 240

풀이 설명:
- 정렬
- 덱에서 앞 뒤로 하나씩뽑기(앞은 가장 작은 수 & 뒤는 가장 큰 수)
- 무게에 가벼운 사람 무게 더하기
    - 무게 + 무거운 사람 <= 제한 무게 이면 1증가 & 무게 초기화
    - 무게 + 무거운 사람 > 제한 무게 이면 1증가 & 무게 유지

풀이 문제점: -> 효율성 통과/실패 경우 모두 존재.
- 덱을 사용 -> 불필요한 자료구조 할당과 메소드 호출(자체제작 isEmpty()) 오버헤드 발생 가능성 O

- 아래 투포인터 방식 활용시 덱 사용시 보다 효율성 테스트 속도 평균 2배 개선(20ms -> 9~10ms)
------------------------------------------------------------
import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int count = 0;
        int i = 0, j = people.length - 1;

        while (i <= j) {
            // 가장 가벼운 사람과 가장 무거운 사람이 같이 탈 수 있으면 같이 탈 수 있도록 함.
            if (people[i] + people[j] <= limit) {
                i++;
            }
            // 무거운 사람은 항상 보트를 사용.
            j--;
            count++;
        }

        return count;
    }
}
------------------------------------------------------------
 */
public class Program_42885 {
    static Deque<Integer> deque;
    public int solution(int[] people, int limit) {
        int result = 0;
        Arrays.sort(people);

        deque = new ArrayDeque<>();
        for(int p : people) deque.add(p);

        int weight = 0;
        while(true) {

            if(weight == 0) {
                if(isEmpty()) {
                    weight += deque.pollFirst();
                } else {
                    break;
                }
            }

            if(isEmpty()) {
                int max = deque.pollLast();
                if(weight + max <= limit) weight = 0;
                result++;
            } else {
                if(weight > 0) result++;
                break;
            }
        }

        return result;
    }

    private static boolean isEmpty() {
        return !deque.isEmpty();
    }
}
