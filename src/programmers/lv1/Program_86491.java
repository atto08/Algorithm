package programmers.lv1;

import java.util.Arrays;

/*
최소 직사각형 - lv1
소요시간 - 4분

[풀이설명]
- 폭 = 작은 길이, 높이 = 긴 길이 로 고정
- 폭과 높이의 최댓 값 갱신 -> 최소 직사각형

 */
public class Program_86491 {
    public static void main(String[] args) {
        int[][] sizes1 = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        int[][] sizes2 = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}};
        int[][] sizes3 = {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}};
        System.out.println(solution(sizes1));   // 4000
        System.out.println(solution(sizes2));   // 120
        System.out.println(solution(sizes3));   // 133
    }

    public static int solution(int[][] sizes) {

        int w = 0, h = 0;
        for (int[] s : sizes) {
            Arrays.sort(s);
            w = Math.max(w, s[0]);
            h = Math.max(h, s[1]);
        }

        return w * h;
    }
}