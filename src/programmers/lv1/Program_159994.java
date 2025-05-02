package programmers.lv1;

/*
카드뭉치 - lv1
소요시간 - 26분

풀이설명:
- 카드뭉치별 현재 인덱스를 히트시에만 올려주기
    - 카드 갯수보다 인덱스 넘어가면 무시
- goal 인덱스(g)를 증가 시키면서 확인
- card1과 card2의 인덱스(i1, i2)를 각각 카운트

p.s
- 카드를 다쓴 인덱스 경우(인덱스가 넘어가는 경우)를 초반에 못잡아서 좀 헤맸다.
- 다행히 빨리 흐름 찾았다.
*/
public class Program_159994 {
    static int i1, i2;

    public static String solution(String[] cards1, String[] cards2, String[] goal) {
        String result = "Yes";

        for (String g : goal) {
            if (i1 < cards1.length) {
                if (g.equals(cards1[i1])) {
                    i1++;
                    continue;
                }
            }

            if (i2 < cards2.length) {
                if (g.equals(cards2[i2])) {
                    i2++;
                    continue;
                }
            }

            result = "No";
            break;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"i", "drink", "water"}, new String[]{"want", "to"}, new String[]{"i", "want", "to", "drink", "water"}));
        System.out.println(solution(new String[]{"i", "water", "drink"}, new String[]{"want", "to"}, new String[]{"i", "want", "to", "drink", "water"}));
    }
}
