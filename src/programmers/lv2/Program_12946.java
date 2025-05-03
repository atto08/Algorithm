package programmers.lv2;

import java.util.*;

/*
하노이의 탑 - lv2
소요시간 - 1시간

풀이설명:
- 기둥 3개(1, 2, 3번)
- 시작: 1번기둥에 1~n번의 원판 존재.
- n개의 원판이 3번원판으로 최소 횟수로 옮겨지는 경우
*/
public class Program_12946 {
    public static int[][] solution(int n) {
        List<int[]> list = new ArrayList<>();
        hanoi(n, 1, 3, 2, list);
        int[][] result = new int[list.size()][2];

        for (int i = 0; i < list.size(); i++) {
            result[i][0] = list.get(i)[0];
            result[i][1] = list.get(i)[1];
        }
        return result;
    }

    private static void hanoi(int n, int start, int goal, int support, List<int[]> list) {
        if (n == 1) {
            list.add(new int[]{start, goal});
            return;
        }
        hanoi(n - 1, start, support, goal, list);
        list.add(new int[]{start, goal});
        hanoi(n - 1, support, goal, start, list);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(2)));
        System.out.println(Arrays.toString(solution(5)));
    }
}