package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj_1260 {
    static int N, M, V;
    static StringTokenizer ts, st;
    static int arr[][];
    static boolean visit_D[], visit_B[];
    static Stack<Integer> stack;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ts = new StringTokenizer(br.readLine());
        N = Integer.parseInt(ts.nextToken());
        M = Integer.parseInt(ts.nextToken());
        V = Integer.parseInt(ts.nextToken());

        arr = new int[N + 1][N + 1];
        visit_D = new boolean[N + 1];
        visit_B = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            arr[y][x] = 1;
            arr[x][y] = 1;
        }
        stack = new Stack<>();
        dfs(V);

        System.out.println();

        q = new LinkedList<>();
        bfs();

    }

    static void dfs(int start) {
        visit_D[start] = true;
        stack.push(start);
        System.out.print(stack.pop() + " ");
        for (int i = 1; i <= N; i++) {
            if (arr[start][i] == 1 && !visit_D[i]) {
                dfs(i);
            }
        }
    }

    static void bfs() {
        q.offer(V);
        visit_B[V] = true;

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");
            for (int i = 1; i <= N; i++){
                if (arr[node][i] == 1 && !visit_B[i]){
                    q.offer(i);
                    visit_B[i] = true;
                }
            }
        }
    }
}
