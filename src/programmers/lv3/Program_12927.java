package programmers.lv3;

/*
야근 지수 - lv3
소요시간 - 12분

문제설명:
- N시간 동안 야근 피로도 최소화하기
- 1시간에 1작업량 처리 가능
- 퇴근까지 남은시간 N시간, 작업량 works[]
- 1 <= works[] <= 20,000 / works[i] 50,000이하 자연수 / n 1,000,000이하 자연수

풀이설명:
- 우선순위 큐에 작업량을 넣고 가장 큰 녀석만 계속뽑기
    -> 야근지수는 남은 작업량의 제곱들의 더하기이기 때문에 작업량에서 높은 수가 되는 경우만 제거하면 됨.

p.s
- 포인트를 초반에 이해를 잘한거 같다. 운이 좋았다!
*/

import java.util.*;

public class Program_12927 {
    public static long solution(int n, int[] works) {
        long result = 0;

        PriorityQueue<Integer> workQ = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) workQ.offer(work);
        for (int i = 0; i < n; i++) {
            if (workQ.isEmpty()) break;
            int num = workQ.poll() - 1;
            if (num > 0) workQ.offer(num);
        }

        while (!workQ.isEmpty()) result += Math.pow(workQ.poll(), 2);

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(4, new int[]{4, 3, 3}));
        System.out.println(solution(1, new int[]{2, 1, 2}));
        System.out.println(solution(3, new int[]{1, 1}));
    }
}
