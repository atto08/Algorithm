package programmers.lv3;

/*
순위 - lv3
소요시간 - 71분

문제설명:
- N명중 정확한 순위를 매길수 있는 선수의 수를 구하시오.
- [A,B] -> A가 B를 이긴다. 권투선수 호칭 1 ~ N번 선수

풀이설명:
- 각 노드에서 나를 이긴 선수는 1과 번호 나에게 패배한 선수는 -1과 번호
- 2번기준
- 승리: 5, 패배: 1,3,4

관련만 확인.
+ 선수별 방문여부?

각 노드별 시작 노드는 1과 -1 모두 확인하기
- 방문여부에 따라서
    - dir < 0 -> 음수 경우만 계속 확인
    - dir > 0 -> 양수 경우만 확인
    - 모두 탐색 후 cnt == n이면 순위 확정 가능

p.s
- 예전에 풀어보려 했던 문제같은데, 생각만하다가 구현방법을 모르겠어서 안풀었던거 같다.
- 최근 bfs문제 1~2주정도 풀어보고 시도해봤는데, 자료구조 적절하게 응용을 잘한거 같다.
- 스스로 기특. 더 잘해보자. 낄낄
*/

import java.util.*;

public class Program_49191 {
    static ArrayList<ArrayList<int[]>> list;
    static int cnt;
    static boolean[] visited;

    public static int solution(int n, int[][] results) {
        int result = 0;

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) list.add(new ArrayList<>());

        for (int[] res : results) {
            int w = res[0], l = res[1];
            list.get(w).add(new int[]{-1, l});
            list.get(l).add(new int[]{1, w});
        }

        for (int i = 1; i <= n; i++) {
            // 노드별로 visited 체크하기
            visited = new boolean[n + 1];
            visited[i] = true;
            cnt = 1;

            for (int[] arr : list.get(i)) {
                int dir = arr[0], idx = arr[1];
                if (!visited[idx]) {
                    visited[idx] = true;
                    cnt++;
                    dfs(idx, dir);
                }
            }

            if (cnt == n) result++;
        }

        return result;
    }

    private static void dfs(int idx, int dir) {

        for (int[] arr : list.get(idx)) {
            int dir2 = arr[0], ni = arr[1];
            if (!visited[ni]) {
                if (dir == dir2) {
                    visited[ni] = true;
                    cnt++;
                    dfs(ni, dir2);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}));
    }
}