package boj.implementation;


/*
여행가자 - 골4
소요시간 - 40분 초과

문제설명:
- 주어진 여행경로대로 도시를 방문할 수 있는가를 구하기

풀이설명:
- 여행경로에 대해서 배열리스트로 갖고 있기.

1차시도 - 시간초과
- M * N의 시간만큼 방문해서 dfs로 확인해서 시간초과 발생추측(bfs도 같은 결과 예상)

ex)
5
5
0 1 0 1 1
1 0 1 1 0
0 1 0 0 0
1 1 0 0 0
1 0 0 0 0
5 3 2 3 4
answer: YES
*/

import java.io.*;
import java.util.*;

public class Boj_1976 {
    static int N, M;
    static int[] goals;
    static boolean[][] visited;
    static ArrayList<ArrayList<Integer>> islands;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        islands = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            islands.add(new ArrayList<>());

        // 섬 연결
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int c = Integer.parseInt(st.nextToken());
                if (c > 0) {
                    islands.get(i).add(j);
                }
            }
        }

        // 여행 최종 경로
        st = new StringTokenizer(br.readLine());
        goals = new int[M];
        for (int i = 0; i < M; i++) goals[i] = Integer.parseInt(st.nextToken());

        visited = new boolean[M][N + 1];
        for (int m = 0; m < M - 1; m++) {
            int n = goals[m];
            visited[m][n] = true;
            dfs(m, n);
        }

        String result = "NO";
        if (visited[M - 2][goals[M - 1]]) result = "YES";

        System.out.println(result);
    }

    private static void dfs(int m, int n) {

        for (int i : islands.get(n)) {
            if (!visited[m][i]) {
                visited[m][i] = true;
                if (goals[m + 1] == i) {
                    return;
                }
                dfs(m, i);
                visited[m][i] = false;
            }
        }
    }
}