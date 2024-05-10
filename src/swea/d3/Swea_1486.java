package swea.d3;

import java.io.*;
import java.util.StringTokenizer;
/*
장훈이의 높은 선반 - D3
소요 시간: 44분
원인: 점원 높이의 합 S크기의 dp배열을 만들고 sum = S로 시작해서 B보다 작아지기전까지만 백트래킹으로 서칭
>> dp에 저장x dp = true, 저장o 통과(중복 제거)
방식으로 구현.
 */
public class Swea_1486 {
    static int N, B, S;
    static int[] H;
    static boolean[] visited, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            H = new int[N];
            S = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                H[i] = Integer.parseInt(st.nextToken());
                S += H[i];
            }

            dp = new boolean[S + 1];
            dp[S] = true;
            for (int i = 0; i < N; i++) {
                visited = new boolean[N];
                visited[i] = true;
                dfs(i, S);
            }

            int result;
            for (int i = B; i <= S; i++) {
                if (dp[i]) {
                    result = i - B;
                    bw.write("#" + test_case + " " + result + "\n");
                    break;
                }
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void dfs(int node, int sum) {

        int ns = sum - H[node];
        if (ns >= B && !dp[ns]) {
            dp[ns] = true;
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    dfs(i, ns);
                    visited[i] = false;
                }
            }
        }
    }
}
