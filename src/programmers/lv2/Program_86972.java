package programmers.lv2;

/*
전력망을 둘로 나누기 - lv2
소요시간 - 39분

풀이설명:
- 완전탐색으로 접근.
    1) 모든 연결된 정보를 이중배열리스트로 갖기
    2) wires의 갯수 하나씩 끊어놓고 탐색하기?
    3) 더 적을때만 개수 차이 갱신

p.s
- 80분안에 두문제 풀기 3분초과. 첫번째 문제에서 고민하다가 시간을 예상보다 많이썼다.
- dfs에서 연결여부를 확인하는 조건문을 빼먹었다..
- 얼마나 잘되려고!! 내일은 두 문제 풀어낸다.
*/

import java.util.*;

public class Program_86972 {
    static int result;
    static boolean[] visited;
    static boolean[][] graph;

    public static int solution(int n, int[][] wires) {
        result = Integer.MAX_VALUE;

        graph = new boolean[n + 1][n + 1];
        for (int[] w : wires) {
            graph[w[0]][w[1]] = true;
            graph[w[1]][w[0]] = true;
        }

        visited = new boolean[n + 1];
        for (int[] w : wires) {
            graph[w[0]][w[1]] = false;
            graph[w[1]][w[0]] = false;

            Arrays.fill(visited, false);
            int dis = n, cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    dfs(i, n);
                    for (int j = 1; j <= n; j++) {
                        if (visited[j]) {
                            cnt++;
                            dis--;
                        }
                    }
                    break;
                }
            }
            int dist = Math.abs(dis - cnt);
            if (dist < result) result = dist;

            graph[w[0]][w[1]] = true;
            graph[w[1]][w[0]] = true;
        }

        return result;
    }

    private static void dfs(int idx, int n) {

        for (int i = 1; i <= n; i++) {
            if (visited[i] || i == idx) continue;

            if (graph[idx][i]) {
                visited[i] = true;
                dfs(i, n);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(9, new int[][]{{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}}));
        System.out.println(solution(4, new int[][]{{1, 2}, {2, 3}, {3, 4}}));
        System.out.println(solution(7, new int[][]{{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}}));
    }
}