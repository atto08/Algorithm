package programmers.lv1;

import java.util.*;
/*
체육복 - lv1
소요시간 - 1시간초과

풀이 설명:
- 체육복을 분실한 인원은 앞뒤 번호 중 여벌 체육복이 있는 학생에게 빌릴 수 있다.
- 앞에 번호가 있으면 앞에 번호한테 빌리기(탐욕법)

1차 시도 - fail(80점)
- 여벌 체육복을 가져온 학생이 체육복을 도난 당한 경우 존재.

2차 시도 - fail(63~70점)
- 여벌 체육복을 가져온 학생중 체육복을 도난 당한 경우를 체육복 없는 학생들 목록에서 제거안함.

3차 시도 - fail(93점)
- lost, reserve 모두 오름차순으로 정렬되어있다고 생각함

4차 시도 - pass

p.s
- 조건을 너무 많이 놓쳤다.
- 시간도 1시간 넘겨버렸다.
- 다른 문제 2문제 못풀고 풀었는데 이랬다.
- 현타오지만 그래도 꾸준하게 진행하자꾸나.
 */
public class Program_42862 {
    public static int solution(int n, int[] lost, int[] reserve) {

        boolean[] hc = new boolean[n + 2];
        Arrays.sort(lost);
        Arrays.sort(reserve);
        for (int r : reserve) hc[r] = true;

        HashSet<Integer> lc = new HashSet<>();
        for (int l : lost) {
            if (hc[l]) {
                hc[l] = false;
                continue;
            }
            lc.add(l);
        }

        for (int l : lost) {
            if (!lc.contains(l)) continue;

            if (hc[l - 1]) {
                hc[l - 1] = false;
                lc.remove(l);
                continue;
            }

            if (hc[l + 1]) {
                hc[l + 1] = false;
                lc.remove(l);
            }
        }
        return n - lc.size();
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{2, 4}, new int[]{1, 3, 5}));
        System.out.println(solution(5, new int[]{2, 4}, new int[]{3}));
        System.out.println(solution(5, new int[]{2, 3, 4}, new int[]{1, 3, 5}));
    }
}
