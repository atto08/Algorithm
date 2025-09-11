package programmers.lv1;
/*
택배 상자 꺼내기 - lv1
소요시간 - 45분

[문제설명]
- 상자는 'ㄹ' 거울모드로 아래에서 위로 상자번호 오름차순대로 쌓임
- 상자 num번을 꺼내기 위해서는 위에 쌓여있는 상자들을 전부 꺼내야함
- 특정 상자번호 num을 꺼내야할때, 꺼내야하는 총 상자의 갯수 result를 구하기

[제한사항]
- 2 ≤ n ≤ 100
- 1 ≤ w ≤ 10
- 1 ≤ num ≤ n

[풀이설명]
- 'ㄹ'형식으로 쌓이는 상자 번호의 증가 수 규칙 찾기
- 찾은 규칙
(0, w-1) -> p=1 -> (2w - p) or p 더하기
(1, w-2) -> p=3 -> ""
(2, w-3) -> p=5 -> ""
(3, w-4) -> p=7 -> ""
(4, w-5) -> p=9 -> ""

*/

public class Program_389478 {
    public static int solution(int n, int w, int num) {

        int x = -1, y = -1;
        int h = (n / w) + 1;
        int[][] boxes = new int[h][w];
        for (int i = 1; i <= w; i++) {
            boxes[0][i - 1] = i;
            // 뽑아야하는 상자의 위치 기억
            if (num == i) {
                x = 0;
                y = i - 1;
            }
        }

        int p = 1;
        for (int i = 0; i < w; i++) {
            for (int j = 1; j < h; j++) {
                int number = boxes[j - 1][i];

                // (2w - p) 홀수번째 or p(짝수 번째) 더하기
                if (j % 2 != 0) {
                    number += (2 * w) - p;
                } else {
                    number += p;
                }

                // 숫자가 n보다 크면 -1(빈공간) 작으면 택배 쌓기
                if (number > n) {
                    boxes[j][i] = -1;
                } else {
                    boxes[j][i] = number;
                }

                // 뽑아야하는 상자의 위치 기억
                if (boxes[j][i] == num) {
                    x = j;
                    y = i;
                }
            }
            p += 2;
        }

        int result = 0;
        // 뽑으려는 상자의 위치에서 차례로 상자 배출
        for (int i = x; i < h; i++) {
            if (boxes[i][y] > 0) result++;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(22, 6, 8)); // result: 3
        System.out.println(solution(13, 3, 6)); // result: 4
    }
}
