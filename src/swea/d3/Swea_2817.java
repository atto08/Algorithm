package swea.d3;


import java.io.*;
import java.util.StringTokenizer;

/*
부분 수열의 합 - D3
소요 시간: 25분
이전에 풀어본 순열문제와 유형이 비슷해서 고려해야될 요소를 알고 있었음
>> 하나씩 뽑아서 만들 수 있는 모든 경우의 수 중 겹치는 부분을 생각하면 됨
>> dfs식에서 인덱스 i가 시작되는 부분을 보고 이해하면 쉬운 문제
 */
public class Swea_2817 {
    static int N, K, result;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            A = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            result = 0;
            for (int i = 0; i < N; i++) {
                dfs(i, A[i]);
            }

            bw.write("#" + test_case + " " + result + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static void dfs(int node, int total) {
        if (total == K) {
            result++;
        }

        for (int i = node + 1; i < N; i++) {
            dfs(i, total + A[i]);
        }
    }
}
