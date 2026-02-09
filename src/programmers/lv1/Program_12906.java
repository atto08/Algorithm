package programmers.lv1;

import java.util.*;

/*
같은 숫자는 싫어 - lv1
소요시간 - 4분

[문제 풀이 회고 및 성능 최적화]

1. 초기 접근 (Stack 사용)
- 문제 분류가 스택/큐이므로 java.util.Stack을 사용하여 구현했습니다.
- 이론적으로 스택은 삽입/삭제가 O(1)로 빠르지만, Java의 Stack 클래스는
- Vector를 상속받아 구현되어 있어 모든 연산에 'synchronized(동기화)' 키워드가 붙습니다.
- 이로 인해 단일 스레드 환경에서도 불필요한 Lock/Unlock 오버헤드가 발생함을 확인했습니다.

2. 문제점 발견
- Stack 사용 시: 넣을 때(Push)와 뺄 때(Pop) 모두 동기화 비용 발생 + 결과를 역순으로 뒤집는 추가 연산 필요.
- 성능 측정 결과: 평균 0.27ms 소요.

3. 최적화 적용 (ArrayList 사용)
- 동기화 비용이 없는 ArrayList를 사용하여 순서대로 값을 저장했습니다.
- 마지막 값 확인(get)과 삽입(add) 모두 O(1)로 처리하며, Lock 오버헤드를 제거했습니다.

4. 최종 결과
- 평균 속도: 0.27ms -> 0.07ms (약 4배 성능 향상)
- 결론: 알고리즘 문제 풀이 및 단일 스레드 환경에서는 Stack 대신 ArrayList나 ArrayDeque 사용이 유리함을 확인.
 */

public class Program_12906 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 1, 3, 3, 0, 1, 1})));
        System.out.println(Arrays.toString(solution(new int[]{4, 4, 4, 3, 3})));
        System.out.println(Arrays.toString(solution2(new int[]{1, 1, 3, 3, 0, 1, 1})));
        System.out.println(Arrays.toString(solution2(new int[]{4, 4, 4, 3, 3})));
    }

    // 2) 배열리스트 사용
    public static int[] solution2(int[] arr) {
        ArrayList<Integer> tempList = new ArrayList<>();

        for (int num : arr) {
            // 리스트가 비어있거나, 마지막에 넣은 숫자가 현재 숫자와 다르면 추가
            if (tempList.isEmpty() || tempList.get(tempList.size() - 1) != num) {
                tempList.add(num);
            }
        }

        int[] answer = new int[tempList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = tempList.get(i);
        }

        return answer;
    }

    // 1) 스택 사용
    public static int[] solution(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (stack.peek() == arr[i]) continue;
            stack.add(arr[i]);
        }

        int[] answer = new int[stack.size()];
        int n = stack.size();
        while (!stack.isEmpty()) {
            answer[--n] = stack.pop();
        }

        return answer;
    }
}
