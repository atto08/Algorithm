package boj.dfs;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_15654 {
    static int N, M;
    static int[] numbers;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
        int[] arr = new int[M];
        dfs(0, arr);
        bw.flush();
    }

    private static void dfs(int depth, int[] arr) throws IOException {
        if (depth == M) {
            StringBuilder sb = new StringBuilder();
            for (int num : arr) {
                sb.append(num).append(" ");
            }
            bw.write(sb.toString().trim() + "\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = numbers[i];
                dfs(depth + 1, arr);
                visited[i] = false;
            }
        }
    }
}
