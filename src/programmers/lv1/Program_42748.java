package programmers.lv1;

import java.util.Arrays;

/*
K번째 수 - lv1
소요시간 - 22분

p.s
- arr2 = 문제에서 array의 i번째 부터 j번째 위치에 있는 인덱스 값의 배열을 정렬한 배열
    -> 기존 array와 arr2간의 인덱스 범위를 헷갈려서 생각보다 오래걸렸다.
 */
public class Program_42748 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 5, 2, 6, 3, 7, 4}, new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}})));
    }

    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            int start = commands[i][0], end = commands[i][1];
            int[] arr2 = new int[end - start + 1];
            for (int j = start; j <= end; j++) {
                arr2[j - start] = array[j - 1];
            }
            Arrays.sort(arr2);
            answer[i] = arr2[commands[i][2] - 1];
        }

        return answer;
    }
}
