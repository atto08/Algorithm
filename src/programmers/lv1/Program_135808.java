package programmers.lv1;

import java.util.Arrays;

/*
과일 장수 - lv1
소요 시간 - 9분

문제 설명:
- 상자의 담긴 사과 중 가장 낮은 점수 = p
- 사과 한 상자의 가격 = p * m
- 과일 장수가 얻을 수 있는 최대 이익을 구하기

풀이 설명:
- 점수가 낮은 사과가 하나라도 포함되어 있으면 해당 상자는 전부 젤 낮은 가격
- 즉, 최대한 낮은 사과끼리 & 높은 사과끼리 뭉쳐야함.
    - 1. 점수 배열 정렬
    - 2. 제일 높은 점수(뒤에서 부터)부터 팔 수 있는 상자묶음까지 계산하기
 */
public class Program_135808 {
    public int solution(int k, int m, int[] score) {
        int result = 0;
        Arrays.sort(score);
        int l = score.length;
        int s = l % m;

        for (int i = s; i < l; i += m) result += score[i] * m;

        return result;
    }

    public static void main(String[] args) {
        Program_135808 pg = new Program_135808();
        System.out.println(pg.solution(3, 4, new int[]{1, 2, 3, 1, 2, 3, 1}));
        System.out.println(pg.solution(4, 3, new int[]{4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2}));
    }
}
