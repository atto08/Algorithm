package programmers.lv2;
/*
택배상자 - lv2
소요시간 - 59분

풀이설명:
- 문제 조건은 택배기사가 원하는 순서대로 쌓아야함
    - 즉, 택배기사가 원하는 순서에 맞는 택배가 나올때까지
        - 컨테이너 벨트의 택배는 움직이고(queue.poll)
        - 보조 컨테이너 벨트에는 택배가 적재된다(stack.push)
    - 이 과정에서 컨테이너 벨트의 택배가 도착하면 stack에 우선 적재하고 order의 번호와 비교해야함.

- 컨테이너 벨트 - 큐 & 보조 컨테이너 - 스택 로 구현

p.s
- 처음에 너무 문제 구현방법이 보여서 쉽게 봤고 조건을 구현하는 과정에서 애먹었다.
- 단순 조건문으로 해도되는걸 반복문의 조건문으로 쓰기도 하고 정신없었다. 하하. 그래도 구현했으니까 한잔해.
*/

import java.util.*;

public class Program_131704 {
    public static int solution(int[] order) {
        int result = 0;

        Stack<Integer> stack = new Stack<>(); // 보조 컨테이너
        Queue<Integer> queue = new LinkedList<>(); // 컨테이너 벨트
        for (int i = 1; i <= order.length; i++) queue.offer(i);

        for (int now : order) {
            boolean hit = false;            // 택배순서 일치 확인여부
            if(!stack.isEmpty()) {      // 보조컨테이너 벨트에 택배가 있다면 맨위 택배만 확인
                if (stack.peek() == now) {    // 현재 맨 위 택배와 택배순서가 일치하면 적재
                    result++;
                    stack.pop();
                    hit = true;
                }
            }
            if (hit) continue;              // 히트시 다음 적재해야할 택배순서와 비교하기 위함

            while (!queue.isEmpty()) {      // 컨테이너 벨트 위 택배가 남아있다면
                stack.push(queue.poll());   // 보조컨테이너 벨트에 적재 후 비교
                if (stack.peek() == now) {    // 택배순서가 일치O = 적재 & 일치X = 보조컨테이너 적재
                    result++;
                    stack.pop();
                    hit = true;
                    break;                  // 택배순서 일치할때까지 컨테이너 벨트 위 택배 확인
                }
            }
            if (!hit) break;                // 택배순서 히트 경우X -> 더 이상 적재불가 판단
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{4, 3, 1, 2, 5}));
        System.out.println(solution(new int[]{5, 4, 3, 2, 1}));
    }
}