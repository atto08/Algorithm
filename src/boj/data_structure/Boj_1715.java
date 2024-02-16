package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
예시를 GPT한테 물어보지 않기.
잘못된 예시로 쉽게 구현 가능한 문제를 돌아왔음.
 */
public class Boj_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(Long.parseLong(br.readLine()));
        }

        long result = 0;
        while (pq.size() > 1) {
            long A = pq.poll();
            long B = pq.poll();

            result += A + B;
            pq.offer(A + B);
        }

        System.out.println(result);
    }
}
