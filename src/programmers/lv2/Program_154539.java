package programmers.lv2;

import java.util.Arrays;
import java.util.Stack;

/*
뒤에 있는 큰 수 찾기 - lv2
소요시간 - 1시간 초과

풀이설명:
- 뒷 큰수 = 자신보다 크면서 가장 가까이 있는 수(현위치 보다 뒤에 숫자 중) - 최소 4 ~ 100만개 & 원소

단순 탐색은 시간초과 발생
L * L!

1차시도(인덱스순회) - 58분(94.6)
- 뒤에서부터 최댓값 바꿔가며
    - 최댓값보다 현재위치가 더 크면 result[i] = -1 & maxNum = numbers[i]
    - 작으면 비교하다가 더 큰수 나오면 stop
- 예상한 시간초과가 발생함.
- ex) [1, 1, 1, 1, .... , 999] 이면 L!까지 순회.
- 즉, 인덱스 조회와 크게 다를바가 없다.

2차시도(스택활용) - 풀이참고

p.s
- 오늘은 복잡한 자료구조 사용이 패착.
- 크게 벗어나지는 않았으니 하던대로 잘하기.
*/
public class Program_154539 {
    // 2차시도 - 스택 활용
    public static int[] solutionStack(int[] numbers) {
        int L = numbers.length;
        int[] result = new int[L];

        Stack<Integer> stack = new Stack<>();
        for (int i = L - 1; i >= 0; i--) {
            while (!stack.isEmpty()) {
                if (stack.peek() > numbers[i]) {
                    result[i] = stack.peek();
                    break;
                } else {
                    stack.pop();
                }
            }

            if (stack.isEmpty()) result[i] = -1;
            stack.push(numbers[i]);
        }

        return result;
    }

    // 1차시도 접근방식 - 조건 추가
    public static int[] solutionIndex(int[] numbers) {
        int L = numbers.length;
        int[] result = new int[L];
        int[] nextGreaterIdx = new int[L]; // 다음으로 큰 수가 있는 인덱스 저장
        nextGreaterIdx[L - 1] = -1;
        result[L - 1] = -1;

        for (int i = L - 2; i >= 0; i--) {
            int j = i + 1;

            // 다음 인덱스를 따라가면서 큰 수를 찾음
            while (j != -1 && numbers[j] <= numbers[i]) {
                j = nextGreaterIdx[j];
            }

            nextGreaterIdx[i] = j;
            result[i] = (j == -1) ? -1 : numbers[j];
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solutionStack(new int[]{2, 3, 3, 5})));
        System.out.println(Arrays.toString(solutionStack(new int[]{9, 1, 5, 3, 6, 2})));
        System.out.println(Arrays.toString(solutionStack(new int[]{2, 1, 1, 1, 1, 3})));
        System.out.println(Arrays.toString(solutionIndex(new int[]{2, 3, 3, 5})));
        System.out.println(Arrays.toString(solutionIndex(new int[]{9, 1, 5, 3, 6, 2})));
        System.out.println(Arrays.toString(solutionIndex(new int[]{2, 1, 1, 1, 1, 3})));
    }
}
