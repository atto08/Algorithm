package programmers.lv1;

import java.util.*;

/*
덧칠하기 - lv1
소요시간 - 26분

문제설명:
- 다시 칠해야하는 구역에 페인트를 칠해야하는 최소값 구하기
- 1 <= m <= n <= 100,000

풀이설명:
- 좌 -> 우 방향으로 방문여부 체크 + section의 갯수를 줄여가면서!

p.s
- 음 제한된 메모리와 시간을 알려주지 않아서 잘 모르겠다만,,
- 범위나 제한 메모리/시간이 작은 숫자였다면 실패했을거 같은 접근이라 생각함.
*/
public class Program_161989 {
    public static int solution(int n, int m, int[] section) {
        int result = 0;
        int cnt = section.length;
        boolean[] visited = new boolean[n + 1];

        Arrays.fill(visited, true);
        for (int s : section) visited[s] = false;

        for (int s : section) {
            if (cnt == 0) break;
            if (visited[s]) continue;

            result++;
            for (int i = s; i < s + m; i++) {
                if (i < 1 || i > n) continue;
                if (!visited[i]) {
                    visited[i] = true;
                    cnt--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(8, 4, new int[]{2, 3, 6}));
        System.out.println(solution(5, 4, new int[]{1, 3}));
        System.out.println(solution(4, 1, new int[]{1, 2, 3, 4}));
    }
}
