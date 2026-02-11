package programmers.lv2;

import java.util.PriorityQueue;

/*
 더 맵게 - Lv2
 소요시간 - 8분

 1. 자료구조 선택
 - 조건 확인: 매번 '가장 작은' 수 2개를 뽑아 섞어야 함.
 - 자료구조: 최솟값을 효율적으로 추출하는 '우선순위 큐' 선택.
 - 근거: 힙(Heap)은 루트에 최솟값을 배치하여 O(1)로 확인하고, O(log N)으로 재구성하는 구조임.

 2. 구현 및 예외 처리
 - pq.peek()으로 최솟값을 확인하며 반복하되, pq.isEmpty() 체크로 재료가 부족한 상황 방어.
 - 형제 노드 간의 순서는 무관하지만 부모가 자식보다 작다는 '느슨한 정렬' 상태를 이용함.

 3. 효율성 테스트 확인 및 한계
 - 결과: 통과
 - 한계: 테스트 케이스 중 최대 1초 내외 소요되는 케이스 발견.
 - 원인: Java Integer 객체의 오토박싱 부하 및 힙 재구성(Heapify) 비용 발생.

 4. 최적화 방향 모색
 - 더 빠른 방안: 객체 생성을 피하는 '직접 구현한 배열 기반 Min-Heap'이나
 - 정렬된 상태를 활용해 O(N)으로 해결 가능한 '두 개의 큐(Two Queues)' 방식 고려 가능.

 -> 두 개의 큐 사용 방식 사용 결과 -> 1초가 걸리는 테스트 케이스는 0.5초로 감소
    - 하지만, 나머지는 우선순위 큐 사용한 것과 비슷한 속도 측정됨.
 - 원인:
   - 초기 Arrays.sort()의 O(N log N) 비용이 존재함.
   - Java Queue 컬렉션 사용 시 발생하는 오토박싱 부하는 여전함.
 - 결론: 루프 내의 '비교/재배치' 연산이 많은 복잡한 케이스일수록 Two Queues 방식이 유리함.
 */
public class Program_42626 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 3, 9, 10, 12}, 7));
    }

    public static int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.offer(s);
        }

        int result = 0;
        while (pq.peek() < K) {

            int s1 = pq.poll();
            if (pq.isEmpty()) {
                result = -1;
                break;
            }

            int s2 = pq.poll();
            int ns = s1 + s2 * 2;
            pq.offer(ns);
            result++;
        }
        return result;
    }
}
