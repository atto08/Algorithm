package swea.d3;

import java.io.*;
import java.util.StringTokenizer;

/*
햄버거 다이어트(배낭문제 유형) - D3
소요 시간: 36분
원인: dp로 풀려했으나 점화식을 구현하지 못함
해결: 같은 재료는 여러번 사용하지 않는 조건을 보고 중복을 제거하는 조합을 재귀함수로 구현
아쉬운 점: dp 점화식을 만들지 못한것 > dp로 만드는 방향도 시도해봐야함
 */
public class Swea_5215 {
    static int N, L, max;
    static int[] S, K;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            S = new int[N];
            K = new int[N];
            for (int i = 0; i < N; i++) { // N개의 재료 S[] = 점수 /K[] = 칼로리
                st = new StringTokenizer(br.readLine());
                S[i] = Integer.parseInt(st.nextToken());
                K[i] = Integer.parseInt(st.nextToken());
            }

            max = 0;
            for (int i = 0; i < N; i++) {
                visited = new boolean[N];
                visited[i] = true;
                dfs(i);
            }
            bw.write("#" + test_case + " " + max + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void dfs(int node) {
        int lSum = 0;
        int sSum = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                sSum += S[i];
                lSum += K[i];
            }
        }

        if (lSum <= L && sSum > max) {
            max = sSum;
        }

        for (int i = node + 1; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i);
                visited[i] = false;
            }
        }
    }
}
