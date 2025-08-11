package programmers.lv2;

/*
이모티콘 할인행사 - lv2
소요시간 - 80분

문제설명:
- 1순위 > 플러스 가입자를 늘리는것
- 2순위 > 판매액 늘리는것
- 할인율 10, 20, 30, 40%

 users[비율, 가격]
 - 비율 == 구매하는 조건, 가격 == 이모티콘 플러스가입하는 조건

 result[0] == 플러스 가입자 수
 result[1] == 이모티콘 총 판매금액

풀이설명:
- 완전탐색
- (4^m의 재귀호출) * (n * m)
- n은 최대 100, m은 7
10192 * (700) -> 시간초과는 괜찮을 수도

- dfs로 이모티콘[m]에 따라서 할인율 10,20,30,40이 되는 경우를 탐색
- 플러스 이모티콘에 가장 많이 가입 + 판매금액이 가장 높은 경우
-> 가입인원, 판매금액 출력

p.s
- 이모티콘별로 할인율의 조합을 만드는 dfs는 생각보다 빨리 잘 만든거같다.
- 우선적으로 할인율 조합을 먼저 찾아낸 것은 잘했다고 생각. - 단계별 접근!!
- 하지만, 이모티콘 금액에 생각없이 할인율을 한번에 처리하려다가 정수형에서 헛수고를 하는바람에 * 0(할인율)이 적용되어 판매금액이 0이 나오고 있었다.
- 타입을 제대로 구분하자. 정말 기초적인 부분에서 실수한거다. 더 빨리 풀 수 있다. 정신차려라.
*/

import java.util.Arrays;

public class Program_150368 {
    static int[] result;

    public static int[] solution(int[][] users, int[] emoticons) {

        result = new int[2];
        int[] discounts = new int[emoticons.length];
        dfs(discounts, 0, users, emoticons);

        return result;
    }

    private static void dfs(int[] discounts, int idx, int[][] users, int[] emoticons) {

        if (idx == emoticons.length) {
            calculate(discounts, users, emoticons);
            return;
        }

        for (int i = 1; i <= 4; i++) {
            discounts[idx] = i * 10;
            dfs(discounts, idx + 1, users, emoticons);
        }
    }

    private static void calculate(int[] discounts, int[][] users, int[] emoticons) {

        int[] prices = new int[users.length];

        // 이모티콘 구매여부 판단
        for (int u = 0; u < users.length; u++) {
            int hopeDiscount = users[u][0];
            for (int e = 0; e < emoticons.length; e++) {
                // 이모티콘의 할인율이 희망 이상의 할인을 받으면
                if (hopeDiscount <= discounts[e]) {
                    int discountsPrice = (emoticons[e] * 100) - (emoticons[e] * discounts[e]);
                    prices[u] += discountsPrice / 100;
                }
            }
        }

        // 플러스 가입 여부 or 이모티콘 판매금액 구분
        int joinCnt = 0, sellPrice = 0;
        for (int u = 0; u < users.length; u++) {
            if (prices[u] >= users[u][1]) {
                joinCnt++;
            } else {
                sellPrice += prices[u];
            }
        }

        // 갱신
        if (result[0] < joinCnt) {
            result[0] = joinCnt;
            result[1] = sellPrice;
        } else if (result[0] == joinCnt) {
            if (result[1] < sellPrice) {
                result[1] = sellPrice;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000, 9000})));
        System.out.println(Arrays.toString(solution(new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}}, new int[]{1300, 1500, 1600, 4900})));
    }
}