package boj.implementation;

import java.io.*;
import java.util.StringTokenizer;
/*
구간 합 구하기 4 - 실3
소요 시간: 40분
1) 시간초과 (68%)

array에 넣을때 누적합 배열을 생성해서 정보 갖고있기
시간 단축 👉 O(N * M) -> O(N + M)
 */
public class Boj_11659 {
    static int[] array;
    static int[] prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        array = new int[N];
        prefixSum = new int[N + 1]; // 누적합 배열 생성, 크기는 N+1

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
            prefixSum[i + 1] = prefixSum[i] + array[i]; // 누적합 계산
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            bw.write(rangeSum(start, end) + "\n");
        }
        bw.flush();
    }

    private static int rangeSum(int s, int e) {
        return prefixSum[e + 1] - prefixSum[s]; // 구간 합 계산
    }
}
