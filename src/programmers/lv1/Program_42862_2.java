package programmers.lv1;

/*
체육복 - lv1
소요시간 - 12분

[풀이설명]
- 1 ~ n번의 학생
- lost - 도난당한 학생들 번호
- reserve - 여벌이있는 학생들 번호

1) 체육복이 없는 애들을 체크 - 0 , -1, 0 ,,
2) 체육복 여벌이 있는 학생들을 체크
    - 없던 애들 -> 0 / 있던 애들 -> 1
3) 돌면서
    - 0 or 1 max count 후 pass
    - 체육복이 없는 아이(-1)는 앞뒤 번호 아이들을 체크하며 둘 중 먼저 있는 여벌이 남아있는 학생에게 빌림

p.s
- 이전에 greedy 알고리즘을 어려워할때 풀었었나보다.
- 처음 풀었을땐 조건을 많이 놓치고 생각없이 풀었다고 적혀있는데,
- 지금은 문제를 상세히 읽고 조건을 정리해서 빠르게 구현할 수 있게 된거같다.
*/

public class Program_42862_2 {
    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{2, 4}, new int[]{1, 3, 5}));
        System.out.println(solution(5, new int[]{2, 4}, new int[]{3}));
        System.out.println(solution(3, new int[]{3}, new int[]{1}));
    }

    public static int solution(int n, int[] lost, int[] reserve) {
        int[] clothes = new int[n + 2];

        for (int l : lost) clothes[l]--;

        for (int r : reserve) clothes[r]++;

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (clothes[i] >= 0) {
                max++;
            } else {
                if (clothes[i - 1] == 1) {
                    clothes[i - 1]--;
                    clothes[i]++;
                    max++;
                    continue;
                }

                if (clothes[i + 1] == 1) {
                    clothes[i + 1]--;
                    clothes[i]++;
                    max++;
                }
            }
        }

        return max;
    }
}