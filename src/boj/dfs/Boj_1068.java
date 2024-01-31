package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1) ArrayIndexOutOfBoundsException
==> 받는 key 값이 -1일때를 못봄

2) 틀렸습니다.
==> 무조건 0이 루트 노드라고 생각함
루트노드가 두개인 경우가 존재할까 고민.
그렇지 않을거라는 가정하에 key 값이 -1로 들어오는 녀석을 start 포인트라 지정후
깊이 우선 탐색을 start 노드부터 시작하게 수정.
 */
public class Boj_1068 {
    static int N, M, result, start;
    static boolean[] visited;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        result = 0;
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        map = new int[N][N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(st.nextToken());
            if (k != -1) {
                map[k][i] = map[i][k] = 1;
            } else {
                start = i;
            }
        }

        M = Integer.parseInt(br.readLine());

        visited[M] = true;
        dfs(start);
        System.out.println(result);
        br.close();
    }

    public static void dfs(int node) {
        int count = 0;
        if (!visited[node]) {
            visited[node] = true;
            for (int i = 0; i < N; i++) {
                if (map[node][i] == 1 && !visited[i]) {
                    count++;
                    dfs(i);
                }
            }
            if (count == 0) {
                result++;
            }
        }
    }
}
