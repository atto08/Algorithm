package programmers.lv3;

/*
네트워크 - lv3
소요시간 - 8분

0 ~ (n-1)번 컴퓨터를 방문처리
- 방문 안했다면 count++
- 방문 했다면 continue;

p.s
- 재풀이 문제. 이전 풀이와 거의 동일함.
- 문제의 핵심은 0 ~ (n-1)번을 순차적으로 돌면서
- i(탐색중인)번째 노드와 연결되어있는 노드는 전부 DFS로 방문처리 하는 것이 핵심
- 그리고 순차적으로 방문이 이미 안되어있는 곳을 확인하게 되면 그곳은 다른 네트워크로 인지하고 count++
*/
public class Program_43162_2 {
    public static void main(String[] args) {
        int[][] com1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] com2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        System.out.println(solution(3, com1));
        System.out.println(solution(3, com2));
    }

    static boolean[] visited;

    public static int solution(int n, int[][] computers) {
        int result = 0;

        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            dfs(i, n, computers);
            result++;
        }

        return result;
    }

    private static void dfs(int node, int n, int[][] computers) {
        visited[node] = true;
        for (int i = 0; i < n; i++) {
            if (i == node) continue;

            if (computers[node][i] > 0 && !visited[i]) {
                dfs(i, n, computers);
            }
        }
    }
}
