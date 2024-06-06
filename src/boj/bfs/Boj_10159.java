package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
저울 - 골4
소요 시간: 55분

1) 통과
이유: 문제를 읽고 문제에서 요구하는 부분을 확실하게 이해했고 그부분을 그래프로 표현할 수 있었다.
>> 요즘 문제 풀때 문제의 의도를 파악하지 못하고 단순히 구현하려는 경향이 생겼음
KeyPoint #문제의 의도를 파악하고 규칙을 찾거나, 구현을 하거나 확실하게 결정할 수 있는 선구안이 생길 수 있도록 문제풀기.
 */
public class Boj_10159 {
    static int N, M;
    static int[][] graph;
    static int[] answers;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        answers = new int[N + 1];
        graph = new int[N + 1][N + 1];
        Arrays.fill(answers, N - 1);

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[k][v] = 1;
            graph[v][k] = 2;
        }

        for (int i = 1; i <= N; i++) {
            bfs(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answers[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int node) {

        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N + 1];
        visited[node] = true;

        for (int i = 1; i <= N; i++) {
            if (graph[node][i] > 0) {
                visited[i] = true;
                q.offer(new int[]{i, graph[node][i]});
            }
        }


        while (!q.isEmpty()) {
            int[] now = q.poll();

            int n = now[0], dir = now[1];
            answers[n]--;
            for (int i = 1; i <= N; i++) {
                if (graph[n][i] == dir && !visited[i]) {
                    visited[i] = true;
                    q.offer(new int[]{i, dir});
                }
            }
        }
    }
}
