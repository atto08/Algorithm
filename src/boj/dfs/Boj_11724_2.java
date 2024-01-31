package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이게 속도 측면에서는 훨씬 빨랐다...
public class Boj_11724_2 {
    static StringTokenizer st;
    static int N, M, result;
    static boolean[] visited;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        map = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map[key][val] = 1;
            map[val][key] = 1;
        }

        result = 0;

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                result++;
                dfs(i);
            }
        }

        System.out.println(result);

        br.close();
    }

    public static void dfs(int num) {
        visited[num] = true;

        for (int i = 1; i <= N; i++) {
            if (map[num][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }
}
