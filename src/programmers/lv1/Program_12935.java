package programmers.lv1;

import java.util.*;
/*
제일 작은 수 제거하기 - lv1
소요시간 - 13분

풀이설명:

이전에 풀다가 못풀었던데
-> 인덱스 i, j에 대해 i ≠ j이면 arr[i] ≠ arr[j] 이 조건을 이해못했었나봄.
이조건은 같은 수가 여러개일 수 있고 제일 작은수가 여러개면 전부 삭제되어야 됨.
그래서
- 정렬x
- 작은 수는 전부 제거하면서 배열 순서 유지.
두개를 성립시켜야됨.

p.s
- 오늘 오전에 코테를 봤다. 3문제였는데 문제별 점수도 바로 나오더라.
- 2번은 규칙 못찾아서 아쉽지않은데, 3번은 20분만 .. 싶었다.
- 1번에서 시간을 너무 잡아먹었다.
결론 - 쉬운문제 빨리 풀기 -> 시간 줄이기. & 생각하고 잇는거 구상 빨리하고 코드 작성하기.
다음엔 다 풀어주마.
 */
public class Program_12935 {
    public static int[] solution(int[] arr) {

        if (arr.length == 1) return new int[]{-1};

        int min = Integer.MAX_VALUE;
        for (int j : arr) {
            if (j < min) {
                min = j;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int a : arr) {
            if (min == a) continue;
            list.add(a);
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            result[i] = list.get(i);

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{4, 3, 2, 1})));
        System.out.println(Arrays.toString(solution(new int[]{10})));
    }
}
