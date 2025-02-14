package programmers.lv3;

/*
네트워크 - lv3(개념정리 및 재풀이)
소요시간 - 20분
분류 - DFS

문제 설명:
- 컴퓨터 개수: n, 컴퓨터 간 연결된 네트워크 정보 : computers
- 간접적으로 연결된 네트워크는 모두 같은 네트워크 상에 존재
- 네트워크의 개수를 구하기

풀이 방법:
- 0 ~ n-1번의 컴퓨터에 접근시도 & 방문처리
    - 방문하지 X 컴퓨터 == 다른 네트워크에 존재
    - 즉, 처음 방문한 컴퓨터를 방문처리시에 네트워크 갯수 증가 & dfs를 통해 연결된 컴퓨터를 방문처리
- 결과적으로 모두 연결되어 있다면 네트워크는 1개
- 그렇지 않다면 0 ~ n-1을 시도하는 횟수 만큼 네트워크 존재
*/
public class Program_43162 {
    static boolean[] visited;

    public int solution(int n, int[][] computers) {

        int result = 0;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, computers);
                result++;
            }
        }
        return result;
    }

    private static void dfs(int node, int[][] computers) {
        visited[node] = true;

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && computers[node][i] == 1) {
                dfs(i, computers);
            }
        }
    }

    public static void main(String[] args) {
        Program_43162 pg = new Program_43162();
        int[][] com1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] com2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        System.out.println(pg.solution(3, com1));
        System.out.println(pg.solution(3, com2));
    }
}