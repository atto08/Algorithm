package programmers.lv2;

import java.util.HashMap;
/*
할인 행사 - lv2
소요시간 - 30분

문제 설명:
- 금액 지불시 10일동안 매일 한가지 제품을 할인받을 수 있는 자격 획득
- 정현이가 원하는 제품과 수량을 전부 만족하여 할인 먹일 수 있는 경우의 수를 구하시오
- want[] = 할인받고싶은 구매품목
- number[] = 구매품목 수량
- discount[] = 할인행사 품목

풀이 설명:
- discount[]에서 10일씩 체크 가능한 구간번 * 10개씩 체크해야됨
- 무조건 열흘

p.s
시간 복잡도 계산하기.
 */
public class Program_131127 {
    public int solution(String[] want, int[] number, String[] discount) {
        int result = 0;

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < want.length; i++) map.put(want[i], i);

        boolean canDiscount;
        for (int i = 0; i < discount.length - 9; i++) {
            int[] cnt = number.clone();

            for (int j = i; j < i + 10; j++) {
                if (!map.containsKey(discount[j])) continue;

                int idx = map.get(discount[j]);
                cnt[idx]--;
            }

            canDiscount = true;
            for (int j = 0; j < number.length; j++) {
                if (cnt[j] != 0) {
                    canDiscount = false;
                    break;
                }
            }

            if (canDiscount) result++;
        }

        return result;
    }
}
