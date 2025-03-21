package programmers.lv2;

import java.util.Arrays;

/*
H-Index - lv2
소요 시간 - 1시간

풀이 설명:
발표논문 배열 오름차순 정렬
발표 논문 N개를 N -> N-1 -> N-2 ... 2 -> 1 (내림차순) 확인
h번 이상 인용된 논문이 h편 이상 && 나머지 논문 h번 이하 인용 체크
만족하는 경우 result로 반환 && break

p.s
화이팅.
몰라도 최선을 다합시다.
실패는 성공의 어머니입니다.
 */
public class Program_42747 {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int result = 0;
        int H = citations.length;

        for (int i = 0; i < H; i++) {
            int P = H - i;
//            System.out.println("P:" + P);
            int max = 0;
            for (int j = i; j < H; j++) {
                if (citations[j] >= P) {
                    max++;
                } else {
                    break;
                }
            }

            if (max < P) continue;

            int min = 0;
            for (int j = 0; j < i; j++) {
                if (citations[j] <= P) {
                    min++;
                } else {
                    break;
                }
            }

            if (min + max < P) continue;
//            System.out.println("max: " + max + " / min: " + min);

            if (max + min == H) {
                result = P;
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Program_42747 pg = new Program_42747();
        System.out.println(pg.solution(new int[]{3, 0, 6, 1, 5}));
    }
}
