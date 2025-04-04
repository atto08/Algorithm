package programmers.lv1;

import java.util.Arrays;
/*
예산 - lv1
소요 시간 - 6분

풀이 설명:
- 부서 별 신청금액 정렬
- 오름차순으로 신청금액 지원하면 최대 지원가능 경우 수 산출 가능
-> 그리디 알고리즘 사용
 */
public class Program_12982 {
    public static int solution(int[] d, int budget) {
        int result = 0;

        Arrays.sort(d);

        for (int money : d) {
            if (budget - money >= 0) {
                budget -= money;
                result++;
            } else {
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 3, 2, 5, 4}, 9));
        System.out.println(solution(new int[]{2, 2, 3, 3}, 10));
    }
}
