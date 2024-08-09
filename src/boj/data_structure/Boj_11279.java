package boj.data_structure;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

/*
최대 힙 - 실2
소요 시간: 8분

단순 우선순위 큐를 이용한 문제
 */
public class Boj_11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x > 0) {
                pq.add(x);
            } else {
                if (pq.isEmpty()) {
                    bw.write("0\n");
                } else {
                    bw.write(pq.poll() + "\n");
                }
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
