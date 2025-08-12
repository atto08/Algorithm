package programmers.lv2;

/*
퍼즐 게임 챌린지 - lv2
소요시간 - 50분(30 + 20)

문제설명:
퍼즐난이도 - diff
현재퍼즐소요시간 - time_cur
이전퍼즐소요시간 - time_prev
숙련도 - level
diffs[0] = 1 픽스

풀이설명:
- 구현
- diff <= level -> += times[i];
- diff > level -> (diff - level) * (times[i] + times[i - 1]) + times[i];
1차시도(구현) - 시간초과(15~20)

2차시도(이분탐색) - 성공
mid = left + (right - left) / 2
-> 요거 기억하자. 이거 잘못해서 무한 루프에 걸렸다..
*/

public class Program_340212 {
    static int left, right;

    public static int solution(int[] diffs, int[] times, long limit) {
        left = 1;
        right = 0;
        for (int diff : diffs) {
            if (right < diff) right = diff;
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            solvePuzzle(diffs, times, limit, mid);
        }

        return left;
    }

    // diffs & times의 길이만큼 확인하면서 계산하기.
    // diffs[i] i번째 퍼즐 난이도 & times[i] i번째 퍼즐 소요시간
    private static void solvePuzzle(int[] diffs, int[] times, long limit, int level) {
        long totalTime = 0;
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                totalTime += times[i];
            } else {
                int cnt = diffs[i] - level;
                int time = times[i] + times[i - 1];
                totalTime += (((long) cnt * time) + times[i]);
            }
        }

        if (totalTime > limit) {
            left = level + 1;
        } else {
            right = level - 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 5, 3}, new int[]{2, 4, 7}, 30));
        System.out.println(solution(new int[]{1, 4, 4, 2}, new int[]{6, 3, 8, 2}, 59));
        System.out.println(solution(new int[]{1, 328, 467, 209, 54}, new int[]{2, 7, 1, 4, 3}, 1723));
        System.out.println(solution(new int[]{1, 99999, 100000, 99995}, new int[]{9999, 9001, 9999, 9001}, 3456789012L));
    }
}
