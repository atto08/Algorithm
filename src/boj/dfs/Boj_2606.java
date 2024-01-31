package boj.dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2606 {
    static int arr[][];
    static boolean visit[];
    static int N, M;
    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visit = new boolean[N + 1];
        arr = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            arr[y][x] = 1;
            arr[x][y] = 1;
        }

        dfs(1);

        System.out.println(result);
    }

    static void dfs(int node) {
        visit[node] = true;

        for (int i = 1; i < N + 1; i++) {
            if (arr[node][i] == 1 && !visit[i]) {
                dfs(i);
                result++;
            }
        }
    }

}