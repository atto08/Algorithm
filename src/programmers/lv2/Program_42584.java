package programmers.lv2;
/*
주식가격 - lv2
소요시간 - 40분

풀이설명:
- 현재 i번째 초의 주가가 L초사이 확인
    - 주가 하락이 없으면 result[i] 증가
    - prices[i]가 하락한 경우가 있으면 스탑.

p.s
- 이전에 시도했다가 테스트케이스가 싹다 틀렸었음.
- 다시 읽어도 말도안되게 틀리길래 찾아봄
- 문제를 좀 디테일하게 적을 필요가 있는 해설이 부족해 욕하는이가 많은 문제였음.
- 초 단위 주식가격이 떨어진 순간부터 체크할 필요X
    - prices[i] 기준 L까지 가격이 =< 인 경우에만 증가시켜서 다 틀렸음.
- 시험에 이런 문제? 안나오길 빌어야지. 문제좀 제대로 적어줘라
*/

import java.util.Arrays;

public class Program_42584 {
    public static int[] solution(int[] prices) {
        int L = prices.length;
        int[] result = new int[L];
        for (int i = 0; i < L - 1; i++) {
            for (int j = i + 1; j < L; j++) {
                result[i]++;
                if (prices[i] > prices[j]) break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 2, 3, 2, 3})));
    }
}