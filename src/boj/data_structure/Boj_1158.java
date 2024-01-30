package boj.data_structure;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        int[] arr = new int[N];

        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }

        int count = N;
        bw.write("<" + "");
        while (!q.isEmpty()) {
            for (int i = 0; i < K - 1; i++) {
                q.offer(q.poll());
            }
            if (count == 1) {
                bw.write(q.poll() + "");
            } else {
                bw.write(q.poll() + ", ");
            }
            count--;
        }
        bw.write(">" + "");
        br.close();
        bw.flush();
        bw.close();
    }
}
