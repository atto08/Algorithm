package programmers.lv3;

/*
입국심사 - lv3
소요시간 - 40분 초과

풀이설명:
- left, right, T 시간관련
- 각 시간별 mid분에 처리가능한 인원 count를 세면서 이분탐색 진행
- times[i]는 최대 10억 & n의 최대도 10억임
- 때문에 걸릴 수 있는 시간의 범위는 long타입으로 선언해야함.
- mid별 심사인원 count도 최악의 경우에는 10^23이 발생하기 때문에 long 타입 선언

p.s
- 아직 타겟팅해야하는 목표를 설정하는데 미숙하다.
*/

import java.util.Arrays;

public class Program_43238 {
    public static long solution(int n, int[] times) {
        Arrays.sort(times);

        long left = 0, right = (long) times[times.length - 1] * n;
        long T = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;

            for (int time : times) {
                count += mid / time;
                if (count >= n) break;
            }

            if (count >= n) {
                T = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return T;
    }

    public static void main(String[] args) {
        System.out.println(solution(6, new int[]{7, 10}));
    }
}