package programmers.lv2;

import java.util.Arrays;
/*
n^2 배열 자르기 - lv2
소요 시간 - 40분

문제 설명:
- n^2 크기의 2차원 배열. 1 ~ n 행을 잘라 이어 붙인 새로운 1차원 배열을 만들어 arr[left]~ arr[right]를 리턴
- 2차원 배열은 1행 1열 부터 i행 i열 까지 영역 내의 모든 빈 칸을 숫자 i로 채움

제한 사항 -
- 1 ≤ n ≤ 10^7
- 0 ≤ left ≤ right < n^2
- right - left < 105

풀이 설명:
1차 시도 - 메모리 초과
2차원 배열 사용으로 인한 메모리 초과로 예상됨.

2차 시도 - 통과
1차원 배열 사용으로 전환 & 규칙 찾아 i행 i열 값 넣기
- 인덱스 left ~ right 까지의 배열을 찾으면 됨
    - i = 0 ~ (right - left) 까지 인덱스 확인
    - 각 인덱스는 실제로 left ~ right 이기 때문에 left + i ~ 가 됨

 */
public class Program_87390 {

    public int[] solution(int n, long left, long right) {
        int size = (int) (right - left) + 1;
        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            long idx = left + i;
            int row = (int) (idx / n);
            int col = (int) (idx % n);
            result[i] = Math.max(row, col) + 1;
        }

        return result;
    }

    public static void main(String[] args) {
        Program_87390 pg = new Program_87390();
        System.out.println(Arrays.toString(pg.solution(3, 2, 5)));
        System.out.println(Arrays.toString(pg.solution(4, 7, 14)));
    }
}
