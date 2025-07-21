package programmers.lv1;

/*
로또의 최고순위와 최저순위
소요시간 - 30분

풀이설명:
- 6 * 6의 시간이면 시간 초과 X
- 그냥 lottos의 원소가 win_nums에 있는지 먼저 확인
- 0이 아닌 경우의 수 중에 당첨번호에 들어가는 수만 세기

*/

import java.util.*;

public class Program_77484 {
    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] result = new int[2];

        int e = 0; // 당첨번호에 있는 숫자갯수.
        Arrays.sort(lottos);

        int nc = -1;
        for (int i = lottos.length - 1; i >= 0; i--) {
            if (lottos[i] == 0) { // 0부터는 안세려고
                nc = i + 1;
                break;
            }

            boolean isTrue = false;
            for (int win : win_nums) {
                if (lottos[i] == win) {
                    isTrue = true;
                    break;
                }
            }

            if (isTrue) e++;
        }

        // 0이 있냐 없냐
        if (nc > 0) {
            result[0] = calculateRank(e + nc);
            result[1] = calculateRank(e);
        } else {
            result[0] = calculateRank(e);
            result[1] = result[0];
        }

        return result;
    }

    private static int calculateRank(int num) {
        if (num == 6) {
            return 1;
        } else if (num == 5) {
            return 2;
        } else if (num == 4) {
            return 3;
        } else if (num == 3) {
            return 4;
        } else if (num == 2) {
            return 5;
        }
        return 6;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{44, 1, 0, 0, 31, 25}, new int[]{31, 10, 45, 1, 6, 19})));
        System.out.println(Arrays.toString(solution(new int[]{0, 0, 0, 0, 0, 0}, new int[]{38, 19, 20, 40, 15, 25})));
        System.out.println(Arrays.toString(solution(new int[]{45, 4, 35, 20, 3, 9}, new int[]{20, 9, 3, 45, 4, 35})));
    }
}