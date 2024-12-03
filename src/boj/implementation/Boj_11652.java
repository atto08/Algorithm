package boj.implementation;

import java.util.HashMap;
import java.util.Scanner;
/*
카드 - 실4
소요 시간 - 40분
원인:
- 해시 맵에서 정렬할 수 있는 방법만 구상하다 시간 소요
-> 해시맵은 숫자의 갯수를 갖는용도로 사용 & 가장 많이 호출된 숫자와 호출된 횟수를 변수로 지정
-> 호출 횟수의 최댓 값은 <= N >> int, 숫자 카드는 -2^62 ~ 2^62 == 카드의 숫자 범위
 */
public class Boj_11652 {
    static Long result;
    static int cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        HashMap<Long, Integer> map = new HashMap<>();
        result = Long.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            Long num = sc.nextLong();

            if (map.containsKey(num)) {
                int count = map.get(num) + 1;
                map.replace(num, count);
                checkout(count, num);
            } else {
                map.put(num, 1);
                checkout(1, num);
            }
        }

        System.out.println(result);
    }

    private static void checkout(int count, Long num) {
        if (count > cnt) {
            result = num;
            cnt = count;
        } else if (count == cnt && result > num) {
            result = num;
        }
    }
}
