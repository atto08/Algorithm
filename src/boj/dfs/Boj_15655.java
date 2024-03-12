package boj.dfs;

import java.io.*;
import java.util.*;

public class Boj_15655 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
        int[] arr = new int[M];
        dfs(0, 0, arr);

        bw.flush();
    }

    private static void dfs(int node, int depth, int[] arr) throws IOException {

        if (depth == M) {
            StringBuilder sb = new StringBuilder();
            for (int num : arr) {
                sb.append(num).append(" ");
            }
            bw.write(sb.toString().trim() + "\n");
            return;
        }

        for (int i = node; i < N; i++) {
            arr[depth] = numbers[i];
            dfs(i + 1, depth + 1, arr);
        }
    }
}
